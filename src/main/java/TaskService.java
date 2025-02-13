package main.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public interface TaskService {
    void addTask(Task task);
    List<Task> getAllTasks();
}
