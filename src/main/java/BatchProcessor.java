package main.java;

public class BatchProcessor {
    public static void cleanOldTasks() {
        // Simulate batch processing with JDBC
        Database.getTasks().stream()
                .filter(Task::completed)
                .forEach(task -> Database.deleteTask(task.id()));
    }
}
