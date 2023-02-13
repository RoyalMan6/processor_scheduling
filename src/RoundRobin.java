public class RoundRobin {
	Process[] pArray;
	int pArray_length;
	int i, quantum, count = 0, temp, sq = 0, rem_bt[];
	float awt = 0, atat = 0;

	public RoundRobin(Process[] p, int l, int q) {
		pArray = p;
		pArray_length = l;
		quantum = q;
		rem_bt = new int[l];
	}

	public void RoundRobinAlgorithm() {

		for (i = 0; i < pArray_length; i++) {
			rem_bt[i] = pArray[i].getBrustTime();
		}

		while (true) {
			for (i = 0, count = 0; i < pArray_length; i++) {
				temp = quantum;
				if (rem_bt[i] == 0) {
					count++;
					continue;
				}
				if (rem_bt[i] > quantum)
					rem_bt[i] = rem_bt[i] - quantum;
				else if (rem_bt[i] >= 0) {
					temp = rem_bt[i];
					rem_bt[i] = 0;
				}
				sq = sq + temp;
				pArray[i].setTurnaroundTime(sq);
			}
			if (pArray_length == count)
				break;
		}

		// pArray[0].setCompleteTime(pArray[0].getArrivalTime() +
		// pArray[0].getBrustTime());

		for (int i = 0; i < pArray_length; i++) {
			// pArray[i].setCompleteTime(pArray[i].getBrustTime() + pArray[i -
			// 1].getCompleteTime());
			pArray[i].setCompleteTime(pArray[i].getTurnaroundTime());

		}

		System.out.println("Round Robin Algorithm:");
		System.out.format("%20s%20s%20s%20s%20s%20s%20s\n", "ProcessId", "BurstTime", "ArrivalTime", "Priority",
				"TurnAroundTime", "WaitingTime", "CompletionTime");

		for (i = 0; i < pArray_length; i++) {
			pArray[i].setWaitingTime(pArray[i].getTurnaroundTime() - pArray[i].getBrustTime());
			awt = awt + pArray[i].getWaitingTime();
			atat = atat + pArray[i].getTurnaroundTime();
			System.out.format("%20s%20d%20d%20d%20d%20d%20d\n", "Job"+pArray[i].getPid(), pArray[i].getBrustTime(),
					0, pArray[i].getPriority(), pArray[i].getTurnaroundTime(),
					pArray[i].getWaitingTime(), pArray[i].getCompleteTime());

		}
		awt = awt / pArray_length;
		atat = atat / pArray_length;
		System.out.println("Average waiting time = " + (float) awt);
		System.out.println("Average turnaround time and completion time = " + (float) atat);
	}


	public void gentt1() {
		System.out.print('\n');
		System.out.println("\nRoundRobin GanttChart:");
		int k = 0;
		int rp = pArray_length;
		for (int j = 0; j < pArray_length; j++) {
			rem_bt[j] = pArray[j].getBrustTime();
		}
		while (rp != 0) {
			if (rem_bt[k] > quantum) {
				rem_bt[k] = rem_bt[k] - quantum;

				int space = quantum % 2 == 0 ? quantum / 2 : (quantum / 2) + 1;
				System.out.print("-");
				for (int j = 0; j < space; ++j) {
					System.out.print("-");
				}
				for (int j = 0; j < pArray[k].getPid().length()+3; j++) {
					System.out.print("-");
				}
				for (int j = 0; j < space; ++j) {
					System.out.print("-");
				}
			} else if (rem_bt[k] <= quantum && rem_bt[k] > 0) {
				int space = rem_bt[k] % 2 == 0 ? rem_bt[k] / 2 : (rem_bt[k] / 2) + 1;
				System.out.print("-");
				for (int j = 0; j < space; ++j) {
					System.out.print("-");
				}
				for (int j = 0; j < pArray[k].getPid().length()+3; j++) {
					System.out.print("-");
				}
				for (int j = 0; j < space; ++j) {
					System.out.print("-");
				}
				rem_bt[k] = 0;
				rp--;
			}
			k++;
			if (k == pArray_length)
				k = 0;
		}
		System.out.print('-');
	}

	public void gentt2() {
		System.out.println();
		int k = 0;
		int rp = pArray_length;
		for (int j = 0; j < pArray_length; j++) {
			rem_bt[j] = pArray[j].getBrustTime();
		}
		while (rp != 0) {
			if (rem_bt[k] > quantum) {
				rem_bt[k] = rem_bt[k] - quantum;

				int space = quantum % 2 == 0 ? quantum / 2 : (quantum / 2) + 1;
				System.out.print("|");
				for (int j = 0; j < space; ++j) {
					System.out.print(" ");
				}
				System.out.print("Job"+pArray[k].getPid());
				for (int j = 0; j < space; ++j) {
					System.out.print(" ");
				}
			} else if (rem_bt[k] <= quantum && rem_bt[k] > 0) {
				int space = rem_bt[k] % 2 == 0 ? rem_bt[k] / 2 : (rem_bt[k] / 2) + 1;
				System.out.print("|");
				for (int j = 0; j < space; ++j) {
					System.out.print(" ");
				}
				System.out.print("Job"+pArray[k].getPid());
				for (int j = 0; j < space; ++j) {
					System.out.print(" ");
				}
				rem_bt[k] = 0;
				rp--;
			}
			k++;
			if (k == pArray_length)
				k = 0;
		}
	}

	public void gentt3() {
		int k = 0;
		int rp = pArray_length;
		int num = 0;
		for (int j = 0; j < pArray_length; j++) {
			rem_bt[j] = pArray[j].getBrustTime();
		}

		System.out.print("\n");
		System.out.print("0");
		while (rp != 0) {
			if (rem_bt[k] > quantum) {
				num = num + quantum;
				rem_bt[k] = rem_bt[k] - quantum;

				int space = quantum % 2 == 0 ? quantum / 2 : (quantum / 2) + 1;
				for (int j = 0; j < space; ++j) {
					System.out.print(" ");
				}
				for (int j = 0; j < 4; j++) {
					System.out.print(" ");
				}
				if (num > 9) {
					for (int j = 0; j < space - 1; ++j) {
						System.out.print(" ");
					}
				} else
					for (int j = 0; j < space; ++j) {
						System.out.print(" ");
					}

				System.out.print(num);
			} else if (rem_bt[k] <= quantum && rem_bt[k] > 0) {
				int space = rem_bt[k] % 2 == 0 ? rem_bt[k] / 2 : (rem_bt[k] / 2) + 1;
				num = num + rem_bt[k];
				for (int j = 0; j < space; ++j) {
					System.out.print(" ");
				}
				for (int j = 0; j < pArray[k].getPid().length()+3; j++) {
					System.out.print(" ");
				}
				if (num > 9) {
					for (int j = 0; j < space - 1; ++j) {
						System.out.print(" ");
					}
				} else
					for (int j = 0; j < space; ++j) {
						System.out.print(" ");
					}
				System.out.print(num);
				rem_bt[k] = 0;
				rp--;
			}
			k++;
			if (k == pArray_length)
				k = 0;
		}
		System.out.println("");
	}

	public void gentt4() {
		System.out.print("|");
		System.out.print('\n');
		int k = 0;
		int rp = pArray_length;
		for (int j = 0; j < pArray_length; j++) {
			rem_bt[j] = pArray[j].getBrustTime();
		}
		while (rp != 0) {
			if (rem_bt[k] > quantum) {
				rem_bt[k] = rem_bt[k] - quantum;

				int space = quantum % 2 == 0 ? quantum / 2 : (quantum / 2) + 1;
				System.out.print("-");
				for (int j = 0; j < space; ++j) {
					System.out.print("-");
				}
				for(int j=0; j < pArray[k].getPid().length()+3; j++) {
					System.out.print("-");
				}
				for (int j = 0; j < space; ++j) {
					System.out.print("-");
				}
			} else if (rem_bt[k] <= quantum && rem_bt[k] > 0) {
				int space = rem_bt[k] % 2 == 0 ? rem_bt[k] / 2 : (rem_bt[k] / 2) + 1;
				System.out.print("-");
				for (int j = 0; j < space; ++j) {
					System.out.print("-");
				}
				for(int j=0; j < pArray[k].getPid().length()+3; j++) {
					System.out.print("-");
				}
//				if(count == 5) {
//					System.out.print("-----");
//				}
//				else {
//					System.out.print("----");
//				}
				for (int j = 0; j < space; ++j) {
					System.out.print("-");
				}
				rem_bt[k] = 0;
				rp--;
			}
			k++;
			if (k == pArray_length)
				k = 0;
		}
		System.out.print('-');
	}

}
