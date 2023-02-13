
public class SRTF {
	Process[] pArray;
	int pArray_length;

	public SRTF(Process[] pArray, int pArray_length) {
		this.pArray = pArray;
		this.pArray_length = pArray_length;
	}

	static void WaitingTime(Process Jobs[], int n, int waiting_time[]) {
		// remT mean remaining
		int remT[] = new int[n];

		// Copy the burst time into remT[]
		for (int i = 0; i < n; i++)
			remT[i] = Jobs[i].getBrustTime();

		int complete = 0, time = 0, shortest = 0, finish_time, min = 99999;
		boolean check = false;

		// We will continue until all Jobs finish
		while (complete != n) {

			// Find Job with minimum remaining time among the processes that arrives ,
			// Remember it should arrive
			for (int j = 0; j < n; j++) {
				if ((Jobs[j].getArrivalTime() <= time) && (remT[j] < min) && remT[j] > 0) {
					min = remT[j];
					shortest = j;
					check = true;
				}
			}

			if (check == false) {
				time++;
				continue;
			}

			// decrement the remaining time by one
			remT[shortest]--;
			// Update minimum
			min = remT[shortest];
			if (min == 0)
				min = 99999;

			// If Job is completed
			if (remT[shortest] == 0) {

				// Increment complete
				complete++;
				check = false;
				// to Find finish time of current jobs
				finish_time = time + 1;

				// Calculate waiting time
				// P2 FOR EXAMPLE -> 5-4-1 = 0
				waiting_time[shortest] = finish_time - Jobs[shortest].getBrustTime() - Jobs[shortest].getArrivalTime();

				if (waiting_time[shortest] < 0)
					waiting_time[shortest] = 0;
			}
			// Increment time
			time++;
		}
	}

	static void TurnAroundTime(Process Jobs[], int n, int wt[], int tat[]) {
		// Turn Around Time = brust time Time + waiting Time
		for (int i = 0; i < n; i++)
			tat[i] = Jobs[i].getBrustTime() + wt[i];

	}

	static void CompltionTime(Process Jobs[], int n, int tat[], int comp[]) {

		// completion time = arrival time + turn around time
		for (int i = 0; i < n; i++)
			comp[i] = Jobs[i].getArrivalTime() + tat[i];

	}

	void findavgTime(Process Jobs[], int jobsN) {
		int wt[] = new int[jobsN];
		int tat[] = new int[jobsN];
		int comp[] = new int[jobsN];
		int total_waitT = 0, total_turnA = 0;
		int total_comp = 0;

		// to find waiting time for the JOBS
		WaitingTime(Jobs, jobsN, wt);

		// to find turn around time for the JOBS

		TurnAroundTime(Jobs, jobsN, wt, tat);

		// to find completion time for the JOBS
		CompltionTime(Jobs, jobsN, tat, comp);

		System.out.println("Shortest Remaining Time First (SRTF) :");
		System.out.format("%20s%20s%20s%20s%20s%20s%20s\n", "ProcessId", "BurstTime", "ArrivalTime","Priority", " TurnaroundTime",
				" WaitingTime", "CompletionTime");
		// Calculate total waiting time and
		// total turn around time
		for (int i = 0; i < jobsN; i++) {
			total_waitT += wt[i];
			total_turnA += tat[i];
			total_comp += comp[i];
			System.out.format("%20s%20s%20s%20s%20s%20s%20s\n", "Job"+Jobs[i].getPid(), Jobs[i].getBrustTime(),
					Jobs[i].getArrivalTime(),Jobs[i].getPriority(), tat[i], wt[i], comp[i]);

		}

		System.out.println("Average waiting time = " + (float) total_waitT / jobsN);
		System.out.println("Average turnaround time = " + (float) total_turnA / jobsN);
		System.out.println("Average completion time = " + (float) total_comp / jobsN);
	}

	public static int Min(Process JOBS[], int tbt, int r, int n) {
		int j = 0;
		int min = tbt;
		for (int i = n - 1; i >= 0; i--) {
			if (JOBS[i].getBrustTime() < min && JOBS[i].getBrustTime() > 0 && r >= JOBS[i].getArrivalTime()) {
				min = JOBS[i].getBrustTime();
				j = i;
			}
		}
		return j;
	}

	public static int Min2(Process JOBS[], int arr[], int tbt, int r, int n) {
		int j = 0;
		int min = tbt;
		for (int i = n - 1; i >= 0; i--) {
			if (arr[i] < min && arr[i] > 0 && r >= JOBS[i].getArrivalTime()) {
				min = arr[i];
				j = i;
			}
		}
		return j;
	}

	static void SRTFGantt(Process JOBS[], int n) {
		int tbt = 0;
		int tat[] = new int[n];
		// total brust time
		for (int i = 0; i < n; i++) {
			tbt = tbt + JOBS[i].getBrustTime();
		}
		int time[] = new int[tbt];
		int k = 0;
		int q2 = 0;
		System.out.println("Gantt Chart :");
		System.out.print("|");
		System.out.print(" p[" + JOBS[0].getPid() + "]\t|");
		for (int i = 0; i < tbt; i++) {
			int q = Min(JOBS, tbt, i, n);
			if (q != q2) {
				System.out.print(" p[" + JOBS[q].getPid() + "]\t|");
				time[k++] = i;
				JOBS[q].setWaitingTime(i);
				tat[q] = i + JOBS[q].getBrustTime();
			}
			JOBS[q].setBrustTime(JOBS[q].getBrustTime() - 1);
			q2 = q;
		}
		time[k] = tbt;
		System.out.println();
		System.out.print("0\t");
		for (int i = 0; i <= k; i++) {
			System.out.print(time[i] + "\t");
		}
		System.out.println();
	}
	
	static void SRT(Process JOBS[],int n) {
		int tbt = 0;
		  int tat[] = new int[n];
		  for(int i=0; i < tat.length; i++) {
			  tat[i] = JOBS[i].getBrustTime();
		  }
		 
		  //total brust time
		         for (int i = 0; i < n; i++) {
		             tbt = tbt + JOBS[i].getBrustTime();
		         }
		         int time[] = new int[tbt];
		         int comp[] = new int [tbt];
		         String arr[] = new String[tbt];
		         int k = 0;
		         int q2 = 0;
		         int space = 0;
		         System.out.println("SRTF Gantt Chart :");
		         for (int i = 0; i < tbt; i++) {
		        	 int q = Min(JOBS, tbt, i, n);
		        	 if (q != q2) {
		        		 arr[k] = "Job"+ JOBS[q].getPid();
		            	 if(k==0)
		        		  space = i % 2 == 0 ? i / 2 :
		            			 (i / 2) + 1;
		            	 else
		        		  space = (i-(time[k-1]) % 2 == 0)? (i-(time[k-1])) /2 :
		        			 ((i-(time[k-1])) / 2)+1;
		            	 comp[k] = space;
		            	 time[k++] = i;
		                 JOBS[q].setWaitingTime(i);
		             }
		             JOBS[q].setBrustTime(JOBS[q].getBrustTime() - 1); 
		             q2 = q;
		             
		         }
		         time[k] = tbt;
		         comp[k] = (tbt-time[k-1]) %2 ==0? (tbt-time[k-1]) / 2 : ((tbt-time[k-1]) / 2)+1;
		         
		         for(int i = 0; i <= k; i++) {
		        	 System.out.print("-");
		            	 for(int j=0; j < (comp[i] * 2) + 3; j++) {
			            	 System.out.print("-");
			             } 
		           
		         }
		         System.out.println();
		         
		         for (int i = 0; i <= k; i++) {
		        	 System.out.print("|");
		        	for(int j=0; j < comp[i]; j++) {
		        		System.out.print(" ");
		        	}
		        	if(i != k)
		             System.out.print(arr[i]);
		             for(int j=0; j < comp[i]; j++) {
			        		System.out.print(" ");
			        	} 
		         } 
		         System.out.println(); 
		         
		         for(int i = 0; i <= k; i++) {
		        	 System.out.print("-");
		            	 for(int j=0; j < (comp[i] * 2) + 3; j++) {
			            	 System.out.print("-");
			             } 
		           
		         }
		         System.out.println();
		         
		         
		         for(int i = 0; i <= k; i++) {
		        	 System.out.print(time[i]);
		             if(time[i] > 9) {
		            	 for(int j=0; j < (comp[i] * 2) + 3; j++) {
			            	 System.out.print(" ");
			             } 
		             }
		             else
		            	 for(int j=0; j < (comp[i] * 2) + 4; j++) {
			            	 System.out.print(" ");
			             }
		         }
		         System.out.println();
		         
		         
	}
	
}
