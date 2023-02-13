public class NP_Priority {
	Process[] pArray;
	int pArray_length;

	public NP_Priority(Process[] p, int l) {
		pArray = p;
		pArray_length = l;
	}

	void sortAccordingArrivalTimeAndPriority() {
		int temp;
		String stemp;
		for (int i = 0; i < pArray_length; i++) {

			for (int j = 0; j < pArray_length - i - 1; j++) {
				if (pArray[j].getArrivalTime() > pArray[j + 1].getArrivalTime()) {
					// swapping arrival time
					temp = pArray[j].getArrivalTime();
					pArray[j].setArrivalTime(pArray[j + 1].getArrivalTime());
					pArray[j + 1].setArrivalTime(temp);

					// swapping burst time
					temp = pArray[j].getBrustTime();
					pArray[j].setBrustTime(pArray[j + 1].getBrustTime());
					pArray[j + 1].setBrustTime(temp);

					// swapping priority
					temp = pArray[j].getPriority();
					pArray[j].setPriority(pArray[j + 1].getPriority());
					pArray[j + 1].setPriority(temp);

					// swapping process identity
					stemp = pArray[j].getPid();
					pArray[j].setPid(pArray[j + 1].getPid());
					pArray[j + 1].setPid(stemp);

				}
				// sorting according to priority when arrival timings are same
				if (pArray[j].getArrivalTime() == pArray[j + 1].getArrivalTime()) {
					if (pArray[j].getPriority() > pArray[j + 1].getPriority()) {
						// swapping arrival time
						temp = pArray[j].getArrivalTime();
						pArray[j].setArrivalTime(pArray[j + 1].getArrivalTime());
						pArray[j + 1].setArrivalTime(temp);

						// swapping burst time
						temp = pArray[j].getBrustTime();
						pArray[j].setBrustTime(pArray[j + 1].getBrustTime());
						pArray[j + 1].setBrustTime(temp);

						// swapping priority
						temp = pArray[j].getPriority();
						pArray[j].setPriority(pArray[j + 1].getPriority());
						pArray[j + 1].setPriority(temp);

						// swapping process identity
						stemp = pArray[j].getPid();
						pArray[j].setPid(pArray[j + 1].getPid());
						pArray[j + 1].setPid(stemp);

					}
				}
			}

		}
	}

	void priorityNonPreemptiveAlgorithm() {

		sortAccordingArrivalTimeAndPriority();

		// calculating waiting & turn-around time for each process
		pArray[0].setCompleteTime(pArray[0].getArrivalTime() + pArray[0].getBrustTime());
		pArray[0].setTurnaroundTime(pArray[0].getCompleteTime() - pArray[0].getArrivalTime());
		pArray[0].setWaitingTime(pArray[0].getTurnaroundTime() - pArray[0].getBrustTime());

		for (int i = 1; i < pArray_length; i++) {
			pArray[i].setCompleteTime(pArray[i].getBrustTime() + pArray[i - 1].getCompleteTime());
			pArray[i].setTurnaroundTime(pArray[i].getCompleteTime() - pArray[i].getArrivalTime());
			pArray[i].setWaitingTime(pArray[i].getTurnaroundTime() - pArray[i].getBrustTime());
		}
		float sum = 0;
		for (int n = 0; n < pArray_length; n++) {
			sum += pArray[n].getWaitingTime();
		}
		float averageWaitingTime = sum / pArray_length;

		sum = 0;
		for (int n = 0; n < pArray_length; n++) {
			sum += pArray[n].getTurnaroundTime();
		}
		float averageTurnAroundTime = sum / pArray_length;

		// print on console the order of processes along with their finish time & turn
		// around time
		System.out.println("Priority Scheduling Algorithm:");
		System.out.format("%20s%20s%20s%20s%20s%20s%20s\n", "ProcessId", "BurstTime", "ArrivalTime", "Priority",
				"TurnAroundTime", "WaitingTime", "CompletionTime");
		for (int i = 0; i < pArray_length; i++) {
			System.out.format("%20s%20d%20d%20d%20d%20d%20d\n", pArray[i].getPid(), pArray[i].getBrustTime(),
					0, pArray[i].getPriority(), pArray[i].getTurnaroundTime(),
					pArray[i].getWaitingTime(), pArray[i].getCompleteTime());
		}
		System.out.println("Average waiting time = " + (float) averageWaitingTime);
		System.out.println("Average turnaround time and completion time = " + (float) averageTurnAroundTime);
	}

	public void gentt() {
		System.out.println();
		System.out.println("Priority Scheduling GanttChart:");
		for (int i = 0; i < pArray_length; i++) {
			int space = pArray[i].getBrustTime() % 2 == 0 ? pArray[i].getBrustTime() / 2
					: (pArray[i].getBrustTime() / 2) + 1;
			System.out.print('-');
			for (int j = 0; j < space * 2 + pArray[i].getPid().length(); j++) {
				System.out.print("-");
			}
		}
		System.out.print("-");
	}

	public void gentt1() {
		System.out.println();
		for (int i = 0; i < pArray_length; i++) {
			int space = pArray[i].getBrustTime() % 2 == 0 ? pArray[i].getBrustTime() / 2
					: (pArray[i].getBrustTime() / 2) + 1;
			System.out.print('|');
			for (int j = 0; j < space; j++) {
				System.out.print(" ");
			}
			System.out.print(pArray[i].getPid());
			for (int j = 0; j < space; j++) {
				System.out.print(" ");
			}
		}
		System.out.print("|");
	}

	public void gentt2() {
		System.out.println();
		for (int i = 0; i < pArray_length; i++) {
			int space = pArray[i].getBrustTime() % 2 == 0 ? pArray[i].getBrustTime() / 2
					: (pArray[i].getBrustTime() / 2) + 1;
			System.out.print('-');
			for (int j = 0; j < space * 2 + pArray[i].getPid().length(); j++) {
				System.out.print("-");
			}
		}
		System.out.print("-");
	}

	public void gentt3() {
		System.out.println();
		System.out.print("0");
		for (int i = 0; i < pArray_length; i++) {
			int space = pArray[i].getBrustTime() % 2 == 0 ? pArray[i].getBrustTime() / 2
					: (pArray[i].getBrustTime() / 2) + 1;
			for (int j = 0; j < space; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < pArray[i].getPid().length(); j++) {
				System.out.print(" ");
			}
			if (pArray[i].getCompleteTime() > 9) {
				for (int j = 0; j < space - 1; ++j) {
					System.out.print(" ");
				}
			} else
				for (int j = 0; j < space; ++j) {
					System.out.print(" ");
				}
			System.out.print(pArray[i].getCompleteTime());
		}
		System.out.println('\n');
	}
}
