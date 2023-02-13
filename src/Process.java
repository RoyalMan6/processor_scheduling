public class Process {
	private String pid;
	private int arrivalTime;
	private int turnaroundTime;
	private int brustTime;
	private int waitingTime;
	private int completeTime;
	private int priority;

	// private int remainingTime;
	// just the constructor
	public Process(String pid, int arrivalTime, int brustTime, int priority) {
		this.pid = pid;
		this.arrivalTime = arrivalTime;
		this.brustTime = brustTime;
		this.priority = priority;
	}

	// Setters and getters
	public String getPid() {
		return pid;
	}

	public void setPid(String name) {
		this.pid = pid;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getBrustTime() {
		return brustTime;
	}

	public void setBrustTime(int brustTime) {
		this.brustTime = brustTime;
	}

	public int getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(int completeTime) {
		this.completeTime = completeTime;
	}

	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}