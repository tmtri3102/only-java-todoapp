package main.java;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    @Override
    public void addTask(Task task) {
        Database.saveTask(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return Database.getTasks();
    }
}
