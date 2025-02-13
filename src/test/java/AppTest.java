package test.java;

import main.java.*;

public class AppTest {
    public static void main(String[] args) {
        // Create a proxy for TaskService
        TaskService service = (TaskService) LoggingProxy.createProxy(new TaskServiceImpl());

        // Add tasks
        service.addTask(new Task(1, "Learn Java", false));
        service.addTask(new Task(2, "Build a REST API", true));

        // Retrieve and print tasks
        System.out.println("Tasks in database:");
        service.getAllTasks().forEach(System.out::println);

        // Clean old tasks (batch processing)
        BatchProcessor.cleanOldTasks();

        // Retrieve and print tasks after cleanup
        System.out.println("Tasks after cleanup:");
        service.getAllTasks().forEach(System.out::println);
    }
}
