# processor_scheduling

aimed to write a program that simulates the scheduling of the processor 
in the operating system. CPU scheduling is a process that allows one process to use 
the CPU while the execution of another process is in the waiting state. This may 
happen due to the unavailability of resources; it aims to make the processor efficient 
and make maximum use of its "CPU utilization". CPU utilization refers to a 
computer’s usage of processing resources, Ideally, the CPU would be busy 100% of 
the time, to waste 0 CPU cycles.
There are important states for the processor:
- new: the process is being created
- running: Instructions are being executed
- waiting: The process is waiting for some event to occur
- terminated: The process has finished execution
CPU scheduling decisions take place under one of the four circumstances:
• When a process switches from the running state to the waiting state, such 
as for an I/O request or invocation of the wait() system call.
• When a process switches from the running state to the ready state, for 
example in response to an interrupt.
• When a process switches from the waiting state to the ready state, say at 
the completion of I/O or a return from wait().
• When a process terminates.
The challenge is to make the overall system as "efficient" and "fair" as possible, here 
we will explain three CPU scheduling algorithms

Shortest Remaining Time First (SRTF): This Algorithm is the preemptive 
version of SJF scheduling. In SRTF, the execution of the process can be stopped 
after a certain amount of time. At the arrival of every process, the short-term 
scheduler schedules the process with the least remaining burst time among the list of 
available processes and the running process.
Priority (non-preemptive): Uniform in the Non-Preemptive Priority scheduling, The 
Processes are scheduled according to the priority number assigned to them. Once 
the process gets scheduled, it will run till its completion. Generally, the lower the 
priority number, the higher the priority of the process. The people might get confused 
with the priority numbers, hence in the GATE, there clearly mention which one is the 
highest priority and which one is the lowest one.
Round-robin (RR): This is the preemptive version of first come first serve 
scheduling. The Algorithm focuses on Time Sharing. In this algorithm, every 
process gets executed in a cyclic way. A certain time slice is defined in the system which is called time quantum. Each process present in the ready 
queue is assigned the CPU for that time quantum, if the execution of the 
process is completed during that time, then the process will terminate else the 
process will go back to the ready queue and waits for the next turn to 
complete the execution.
