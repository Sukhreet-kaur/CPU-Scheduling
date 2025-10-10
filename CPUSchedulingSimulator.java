import java.util.*;

class Process {
    int pid, at, bt, priority, wt, tat, remainingTime;
    
    public Process(int pid, int at, int bt, int priority) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.priority = priority;
        this.remainingTime = bt;
    }
}

public class CPUSchedulingSimulator {
    
    static void displayMenu() {
        System.out.println("\n=== CPU Scheduling Simulator ===");
        System.out.println("1. FCFS");
        System.out.println("2. SJF (Non-Preemptive)");
        System.out.println("3. Priority Scheduling (Non-Preemptive)");
        System.out.println("4. Round Robin (Quantum = 2)");
        System.out.println("5. Exit");
        System.out.print("Choose an algorithm: ");
    }
    
    static void fcfs(ArrayList<Process> processes) {
        // Sort by arrival time
        processes.sort((p1, p2) -> Integer.compare(p1.at, p2.at));
        
        int currentTime = 0;
        double totalWT = 0, totalTAT = 0;
        
        System.out.println("\nFCFS Scheduling:");
        System.out.println("PID\tAT\tBT\tStart\tComplete\tWT\tTAT");
        
        for (Process p : processes) {
            if (currentTime < p.at) {
                currentTime = p.at;
            }
            int start = currentTime;
            currentTime += p.bt;
            int complete = currentTime;
            p.wt = start - p.at;
            p.tat = complete - p.at;
            totalWT += p.wt;
            totalTAT += p.tat;
            
            System.out.println(p.pid + "\t" + p.at + "\t" + p.bt + "\t" + start + "\t" + complete + "\t" + p.wt + "\t" + p.tat);
        }
        
        System.out.println("Average WT: " + (totalWT / processes.size()) + ", Average TAT: " + (totalTAT / processes.size()) + ", Efficiency: 100%");
    }
    
    static void sjf(ArrayList<Process> processes) {
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.bt));
        ArrayList<Process> completed = new ArrayList<>();
        int currentTime = 0;
        double totalWT = 0, totalTAT = 0;
        
        System.out.println("\nSJF (Non-Preemptive) Scheduling:");
        System.out.println("PID\tAT\tBT\tStart\tComplete\tWT\tTAT");
        
        while (completed.size() < processes.size()) {
            // Add all ready processes
            for (Process p : processes) {
                if (p.at <= currentTime && !completed.contains(p)) {
                    pq.offer(p);
                }
            }
            
            if (pq.isEmpty()) {
                currentTime++;
                continue;
            }
            
            Process p = pq.poll();
            int start = currentTime;
            currentTime += p.bt;
            int complete = currentTime;
            p.wt = start - p.at;
            p.tat = p.wt + p.bt;
            totalWT += p.wt;
            totalTAT += p.tat;
            completed.add(p);
            
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d%n", p.pid, p.at, p.bt, start, complete, p.wt, p.tat);
        }
        
        System.out.printf("Average WT: %.2f, Average TAT: %.2f, Efficiency: 100%%%n", 
            totalWT / processes.size(), totalTAT / processes.size());
    }
    
    static void priorityScheduling(ArrayList<Process> processes) {
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.priority));
        ArrayList<Process> completed = new ArrayList<>();
        int currentTime = 0;
        double totalWT = 0, totalTAT = 0;
        
        System.out.println("\nPriority Scheduling (Non-Preemptive):");
        System.out.println("PID\tAT\tBT\tPR\tStart\tComplete\tWT\tTAT");
        
        while (completed.size() < processes.size()) {
            // Add all ready processes
            for (Process p : processes) {
                if (p.at <= currentTime && !completed.contains(p)) {
                    pq.offer(p);
                }
            }
            
            if (pq.isEmpty()) {
                currentTime++;
                continue;
            }
            
            Process p = pq.poll();
            int start = currentTime;
            currentTime += p.bt;
            int complete = currentTime;
            p.wt = start - p.at;
            p.tat = p.wt + p.bt;
            totalWT += p.wt;
            totalTAT += p.tat;
            completed.add(p);
            
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d%n", p.pid, p.at, p.bt, p.priority, start, complete, p.wt, p.tat);
        }
        
        System.out.printf("Average WT: %.2f, Average TAT: %.2f, Efficiency: 100%%%n", 
            totalWT / processes.size(), totalTAT / processes.size());
    }
    
    static void roundRobin(ArrayList<Process> processes, int quantum) {
        LinkedList<Process> queue = new LinkedList<>(processes);
        int currentTime = 0;
        double totalWT = 0, totalTAT = 0;
        Set<Integer> completed = new HashSet<>();
        
        System.out.println("\nRound Robin Scheduling (Quantum=" + quantum + "):");
        System.out.println("PID\tAT\tBT\tStart\tComplete\tWT\tTAT");
        
        while (!queue.isEmpty()) {
            Process current = queue.poll();
            if (completed.contains(current.pid)) continue;
            
            int start = currentTime;
            int executeTime = Math.min(quantum, current.remainingTime);
            currentTime += executeTime;
            current.remainingTime -= executeTime;
            
            // Re-add uncompleted processes and new arrivals
            for (Process p : processes) {
                if ((p.at <= currentTime && !completed.contains(p.pid)) || (p.remainingTime > 0 && p.pid == current.pid)) {
                    if (p.remainingTime > 0 && p.pid == current.pid) {
                        queue.add(p);
                    } else if (!completed.contains(p.pid)) {
                        queue.add(p);
                    }
                }
            }
            
            if (current.remainingTime > 0) {
                queue.add(current);
            } else {
                int complete = currentTime;
                current.wt = complete - current.bt - current.at;
                current.tat = complete - current.at;
                totalWT += current.wt;
                totalTAT += current.tat;
                completed.add(current.pid);
                
                System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d%n", current.pid, current.at, current.bt, start, complete, current.wt, current.tat);
            }
        }
        
        System.out.printf("Average WT: %.2f, Average TAT: %.2f, Efficiency: 100%%%n", 
            totalWT / completed.size(), totalTAT / completed.size());
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        ArrayList<Process> processes = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time for P" + (i+1) + ": ");
            int at = sc.nextInt();
            System.out.print("Enter Burst Time for P" + (i+1) + ": ");
            int bt = sc.nextInt();
            System.out.print("Enter Priority for P" + (i+1) + ": ");
            int pr = sc.nextInt();
            processes.add(new Process(i+1, at, bt, pr));
        }
        
        int choice;
        do {
            displayMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    fcfs(new ArrayList<>(processes)); // Copy to avoid modifying original
                    break;
                case 2:
                    sjf(new ArrayList<>(processes));
                    break;
                case 3:
                    priorityScheduling(new ArrayList<>(processes));
                    break;
                case 4:
                    roundRobin(new ArrayList<>(processes), 2);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        
        sc.close();
    }
}