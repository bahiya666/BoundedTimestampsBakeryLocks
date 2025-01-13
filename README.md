Overview
This project implements a multi-threaded application using a custom locking mechanism (Bakery Lock) to simulate the process of marking papers in a multi-threaded environment. The system consists of several classes, each responsible for different tasks:

Paper - Represents an exam paper with random answers and calculates the score based on a predefined set of correct answers.
Marker - A thread that marks the papers concurrently using the Bakery Lock for synchronization.
BackeryLock - A custom implementation of the Bakery Lock algorithm to control access to shared resources in a multi-threaded environment.
BoundedTimestamp - Supports the Bakery Lock by providing timestamps to help with thread ordering.
Main - The entry point of the application where the threads are created and the simulation begins.

Classes
1. Paper
The Paper class represents an exam paper with a set of answers and provides functionality to mark the paper based on a predefined set of correct answers.

Methods:
Constructor (Paper()):
Generates random answers for each question on the paper.
Initializes the marks to zero.
mark():
Marks the paper by comparing the answers to the correct answers (stored in the memo array) and calculates the score.
setNumQuestions(int n):
Sets the number of questions for all Paper objects.
Initializes the memo array with default answers.
Fields:
answers: An array of characters representing the answers on the paper.
mark: The score for the paper.
numQuestions: A static field that defines the number of questions for all papers.
memo: A static array that holds the correct answers.

2. Marker
The Marker class extends Thread and is responsible for marking papers concurrently. Each thread in the system is a Marker that processes a paper and updates the score based on the correct answers.

Methods:
run():
This method is executed by each thread and marks papers one by one, locking and unlocking the queue of papers using the BackeryLock.
Fields:
lock: The custom BackeryLock used to synchronize access to shared resources.
allPapers: A queue that holds the papers that need to be marked.

3. BackeryLock
BackeryLock is a custom implementation of the Bakery Lock algorithm. It is used to synchronize threads in a way that guarantees mutual exclusion based on a "ticket" system with timestamps.

Methods:
lock():
The thread acquires a lock by setting its flag and getting its timestamp, ensuring threads acquire the lock in a fair manner based on their timestamps.
unlock():
The thread releases the lock by resetting its flag.
existsAKWhere(int i):
Checks if any thread with a smaller timestamp is waiting for the lock, ensuring that the system follows the Bakery Lock protocol.
Fields:
flag: A boolean array that tracks whether a thread is in the critical section.
label: An array of BoundedTimestamp objects that serve as the "ticket" or timestamp for each thread.
maximumNumberOfThreads: The maximum number of threads that can request the lock.

4. BoundedTimestamp
The BoundedTimestamp class represents a timestamp used by the BackeryLock algorithm. Each timestamp is associated with a unique thread and ensures that threads are ordered in a fair manner.

Methods:
getNext(BoundedTimestamp[] snapshot, int id):
Calculates the next timestamp for the thread with the given ID.
compare(BoundedTimestamp other):
Compares two timestamps to determine which thread should proceed based on the Bakery Lock algorithm.
Fields:
timestamp: An array of integers representing the logical timestamp.
max: The maximum value the timestamp can hold.

5. Main
The Main class is the entry point of the application, where the simulation is initiated. It creates the necessary threads (Marker), assigns papers to the Queue, and starts the marking process.

Methods:
main(String[] args):
Initializes the Paper objects and the Marker threads.
Starts the threads and processes the marking of all papers.


Compile the Code:
javac *.java
Run the Program:
java Main


Concurrency: This project demonstrates the use of a custom locking mechanism (BackeryLock) to manage concurrency. It ensures that multiple threads can work concurrently while avoiding race conditions.

Thread Management: The Marker threads use the BackeryLock to synchronize marking papers, ensuring that only one thread marks a paper at a time.
Scalability: The number of questions in the Paper class can be adjusted by modifying the setNumQuestions() method, and the number of threads is controlled by the maximumNumberOfThreads field in the BackeryLock class.

Sequential Consistency: The use of the Bakery Lock ensures that the operations of different threads are interleaved in a manner that respects the sequential consistency model. This ensures that the results of concurrent operations are as though they were executed in some sequential order.

