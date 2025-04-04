import java.util.Scanner;

class Process {
    int processID;
    int burstTime;
    int priority;
    Process next;

    Process(int processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobin {
    private Process head = null;
    private Process tail = null;

    public void addProcess(int processID, int burstTime, int priority) {
        Process newProcess = new Process(processID, burstTime, priority);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            tail.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
    }

    public void removeProcess(int processID) {
        if (head == null) return;
        Process current = head, prev = null;
        do {
            if (current.processID == processID) {
                if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = current.next;
                    if (current == tail) tail = prev;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void simulateRoundRobin(int timeQuantum) {
        if (head == null) return;
        int totalProcesses = 0, totalWaitingTime = 0, totalTurnAroundTime = 0;
        Process current = head;
        do {
            totalProcesses++;
            current = current.next;
        } while (current != head);

        current = head;
        int[] waitingTime = new int[totalProcesses];
        int[] turnAroundTime = new int[totalProcesses];
        int index = 0;

        while (head != null) {
            if (current.burstTime > 0) {
                int executionTime = Math.min(current.burstTime, timeQuantum);
                current.burstTime -= executionTime;

                for (int i = 0; i < totalProcesses; i++) {
                    if (waitingTime[i] != -1 && i != index) {
                        waitingTime[i] += executionTime;
                    }
                }

                if (current.burstTime == 0) {
                    turnAroundTime[index] = waitingTime[index] + executionTime;
                    totalWaitingTime += waitingTime[index];
                    totalTurnAroundTime += turnAroundTime[index];
                    removeProcess(current.processID);
                    waitingTime[index] = -1;
                }
            }
            current = current.next;
            index = (index + 1) % totalProcesses;
        }

        System.out.println("Average Waiting Time: " + (double) totalWaitingTime / totalProcesses);
        System.out.println("Average Turnaround Time: " + (double) totalTurnAroundTime / totalProcesses);
    }

    public void displayProcesses() {
        if (head == null) return;
        Process current = head;
        do {
            System.out.println("Process ID: " + current.processID + ", Burst Time: " + current.burstTime + ", Priority: " + current.priority);
            current = current.next;
        } while (current != head);
    }

    public static void main(String[] args) {
        RoundRobin scheduler = new RoundRobin();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Process");
            System.out.println("2. Display Processes");
            System.out.println("3. Simulate Round Robin");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID: ");
                    int processID = scanner.nextInt();
                    System.out.print("Enter Burst Time: ");
                    int burstTime = scanner.nextInt();
                    System.out.print("Enter Priority: ");
                    int priority = scanner.nextInt();
                    scheduler.addProcess(processID, burstTime, priority);
                    break;
                case 2:
                    scheduler.displayProcesses();
                    break;
                case 3:
                    System.out.print("Enter Time Quantum: ");
                    int timeQuantum = scanner.nextInt();
                    scheduler.simulateRoundRobin(timeQuantum);
                    break;
                case 4:
                    scanner.close();
                    return;
            }
        }
    }
}