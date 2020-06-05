public class RoundRobin {

    // constants
    private static final int DEFAULT_QUANTUM =  3;
    private static final String TASK_HANDLED = "All tasks are already handled.";

    // instance variables
    private DoublyLinkedList<Task> waitlist, finished;
    private int quantum, burstTime, waitTime;

    public RoundRobin(Task[] toHandle) {
        waitlist = new DoublyLinkedList<>();
        finished = new DoublyLinkedList<>();

        for (Task n : toHandle) {
            waitlist.add(n);
        }

        quantum = DEFAULT_QUANTUM;
        burstTime = 0;
        waitTime = 0;

    }

    public RoundRobin(int quantum, Task[] toHandle) {
        if (quantum < 1 || toHandle == null || toHandle.length == 0) {
            throw new IllegalArgumentException();
        }
        waitlist = new DoublyLinkedList<>();
        finished = new DoublyLinkedList<>();

        this.quantum = quantum;

        for (Task n : toHandle) {
            waitlist.add(n);
        }
        burstTime = 0;
        waitTime = 0;

    }

    public String handleAllTasks() {
        if (waitlist.isEmpty()) {
            return TASK_HANDLED;
        }
        while (!waitlist.isEmpty()) {
            Task temp = waitlist.remove(0);
            for (int n = 0; n < quantum; n++) {
                if (temp.handleTask()) {
                    waitTime = waitTime + waitlist.size();
                    burstTime++;
                    continue;
                } else {
                    break;
                }
            }

            if (temp.isFinished()) {
                finished.add(temp);
            } else {
                waitlist.add(temp);
            }
        }
        String finishedString = "All tasks are handled within " + burstTime
                + " units of burst time and " + waitTime + " units of wait time. The tasks are finished in this order:"
                + "\n";
        int sizeFinished = finished.size();
        for (int n = 0; n < sizeFinished; n++) {
            Task tempString = finished.remove(0);
            if (finished.isEmpty()) {
                finishedString += tempString.toString();
            } else {
                finishedString += tempString.toString() + " -> ";
            }

        }

        return finishedString;
    }

    /**
     * Main method for testing.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Task[] test1 = {new Task("A",  3), new Task("B", 4), new Task("C", 4),
                new Task("D", 12), new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(test1);     // Quantum: 3, ToHandle: test1
        System.out.println(rr1.handleAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8), new Task("C", 6),
                new Task("D", 4), new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(4, test2);  // Quantum: 4, ToHandle: test2
        System.out.println(rr2.handleAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5), new Task("C", 3), new Task("D", 1),
                new Task("E", 2), new Task("F", 4), new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(test3);     // Quantum: 3, ToHandle: test3
        System.out.println(rr3.handleAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.handleAllTasks());   // TASK_HANDLED
        System.out.println();
    }
}