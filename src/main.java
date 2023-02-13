import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {
	public static final String delimiter = ",";

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String path = "";
		String line = "";
		String splitBy = ",";
		int counter = 0;
		int quantum = 0; // for round robin
		// boolean variables to choose wanted algorithm/s by the user
		boolean SRTF, Priority, RR;
		int userChoise = 0;

		while (true) {
			SRTF = false;
			Priority = false;
			RR = false;
			System.out.println("Enter the path: "); // path format: C:\\Users\\badii\\Desktop\\testdata1 for priortiy (1).txt
			path = input.nextLine();
			System.out.println("Choose an algorithm: \n1- SRTF\n2- Priority\n3- Round Roubin\n4- Exit");
			userChoise = input.nextInt();
			input.nextLine();
			if (userChoise == 1)
				SRTF = true;
			else if (userChoise == 2)
				Priority = true;
			else if (userChoise == 3)
				RR = true;
			else if (userChoise == 4)
				break;
			else
				System.out.println("Wrong input!");
			try {
				// change the path to test the code
				File f = new File(path);
				Scanner scan = new Scanner(f);

				// count number of processes in the .txt file
				int numOfProcesses = 0;
				while (scan.hasNextLine()) {
					scan.nextLine();
					numOfProcesses++;
				}

				// close and start Scanner again so we can read the file again from the begining
				scan.close();
				scan = new Scanner(f);

				// Choose between algorithms
				if (SRTF) {
					Process[] SRTFArray = new Process[numOfProcesses];
					
					// fill the arrays from the .txt file data
					// parsing a CSV file into BufferedReader class constructor
					BufferedReader br = new BufferedReader(new FileReader(path));
					while ((line = br.readLine()) != null)
					// returns a Boolean value
					{
						String[] jobs = line.split(splitBy);
						// use comma as separator
						SRTFArray[counter] = new Process(jobs[0], Integer.parseInt(jobs[1]), Integer.parseInt(jobs[2]), 0);
						counter++;
					}
					counter = 0;

					SRTF s = new SRTF(SRTFArray, numOfProcesses);
					s.findavgTime(SRTFArray, SRTFArray.length);
					s.SRT(SRTFArray, SRTFArray.length);

				} else if (Priority) {
					numOfProcesses /= 2; // because every 2 lines in the prioriy .txt file is belong to one process

					Process[] priorityArray = new Process[numOfProcesses];

					// fill the arrays from the .txt file data
					while (scan.hasNext()) {
						priorityArray[counter] = new Process(scan.next(), 0, Integer.parseInt(scan.next()),
								Integer.parseInt(scan.next()));
						counter++;
					}
					counter = 0;

					NP_Priority p = new NP_Priority(priorityArray, numOfProcesses);
					p.priorityNonPreemptiveAlgorithm();
					p.gentt();
					p.gentt1();
					p.gentt2();
					p.gentt3();

				} else if (RR) {
					Process[] RRArray = new Process[numOfProcesses];
					
					// fill the arrays from the .txt file data
					// parsing a CSV file into BufferedReader class constructor
					BufferedReader br = new BufferedReader(new FileReader(path));
					while ((line = br.readLine()) != null)
					// returns a Boolean value
					{
						String[] jobs = line.split(splitBy);
						// use comma as separator
						RRArray[counter] = new Process(jobs[0], Integer.parseInt(jobs[1]), Integer.parseInt(jobs[2]), 0);
						counter++;
					}
					counter = 0;
					
					System.out.println("Enter the quantum: ");
					quantum = input.nextInt();
					input.nextLine();
					RoundRobin r = new RoundRobin(RRArray, numOfProcesses, quantum); 
					r.RoundRobinAlgorithm();
					r.gentt1();
					r.gentt2();
					r.gentt4();
					r.gentt3();
				}
				scan.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error: Please check the file path.");
			} catch (Exception e) {
				System.out.println("Error: Please check the .txt file format.");
				System.out.println(e);
			}
		}

		System.out.println("");
		System.out.println("Program finished");
	}

}
