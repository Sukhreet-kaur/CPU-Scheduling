**🖥️ CPU Scheduling Algorithms in Java
📌 Overview

This project demonstrates the implementation of core CPU Scheduling Algorithms using Java, focusing on how operating systems manage process execution efficiently.
It is designed to strengthen OS fundamentals, DSA skills, and Java programming, making it ideal for interview preparation and academic projects.

🎯 Objectives

Understand how CPU scheduling works in an Operating System

Implement popular scheduling algorithms in Java

Analyze Waiting Time, Turnaround Time, and Response Time

Compare algorithms based on performance metrics

🧠 Algorithms Implemented

First Come First Serve (FCFS)

Shortest Job First (SJF)

Non-Preemptive

Preemptive (Shortest Remaining Time First)

Priority Scheduling

Preemptive

Non-Preemptive

Round Robin (RR)

⚙️ Features

Menu-driven console program

Dynamic input for:

Process ID

Arrival Time

Burst Time

Priority

Time Quantum (for Round Robin)

Calculation of:

Waiting Time (WT)

Turnaround Time (TAT)

Average WT & TAT

Clean and modular Java code

🛠️ Tech Stack

Language: Java

Concepts Used:

Arrays & Lists

Sorting

Loops & Conditionals

Object-Oriented Programming (OOP)

📂 Project Structure
CPU-Scheduling/
│
├── FCFS.java
├── SJF.java
├── PriorityScheduling.java
├── RoundRobin.java
├── Process.java
└── Main.java

▶️ How to Run

Clone the repository:

git clone https://github.com/your-username/cpu-scheduling-java.git
Navigate to the project directory:
cd cpu-scheduling-java
Compile the program:
javac Main.java

Run the program:

java Main

📊 Sample Output
Process   Arrival   Burst   Waiting   Turnaround
P1        0         5       0         5
P2        1         3       4         7

Average Waiting Time = 2.0
Average Turnaround Time = 6.0

💡 Interview Relevance

This project helps answer interview questions such as:
Difference between preemptive and non-preemptive scheduling
Why Round Robin is used in time-sharing systems
How SJF minimizes average waiting time
Trade-offs between fairness and efficiency

🚀 Future Enhancements

Gantt Chart visualization
GUI using JavaFX or Swing
Comparison graph for algorithms
Multithreading simulation

👩‍💻 Author

Sukhreet Kaur
B-Tech Computer Science | DSA & OS Enthusiast**
