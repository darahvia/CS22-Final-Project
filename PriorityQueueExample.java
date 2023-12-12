import java.util.PriorityQueue;

// Task class representing a generic task
class Task implements Comparable<Task> {
    private String name;
    private int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Task other) {
        // Compare tasks based on priority (lower priority value is higher priority)
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Create a priority queue of tasks
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();

        // Add tasks with different priorities
        taskQueue.add(new Task("Task A", 3));
        taskQueue.add(new Task("Task B", 1));
        taskQueue.add(new Task("Task C", 2));

        // Process tasks based on priority
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll(); // Retrieves and removes the highest priority task
            System.out.println("Processing Task: " + task);
        }
    }
}
