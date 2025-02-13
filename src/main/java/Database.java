package main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    // Use a named in-memory database
    private static final String DB_URL = "jdbc:h2:mem:taskdb;DB_CLOSE_DELAY=-1";

    static {
        try {
            // Load the H2 driver
            Class.forName("org.h2.Driver");

            // Create the tasks table
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS tasks (id INT PRIMARY KEY, description VARCHAR(255), completed BOOLEAN)");
                System.out.println("Table 'tasks' created successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("H2 driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to create table 'tasks'!");
            e.printStackTrace();
        }
    }

    public static void saveTask(Task task) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO tasks (id, description, completed) VALUES (?, ?, ?)")) {
            stmt.setInt(1, task.id());
            stmt.setString(2, task.description());
            stmt.setBoolean(3, task.completed());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to save task!");
            e.printStackTrace();
        }
    }

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tasks")) {
            while (rs.next()) {
                tasks.add(new Task(rs.getInt("id"), rs.getString("description"), rs.getBoolean("completed")));
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve tasks!");
            e.printStackTrace();
        }
        return tasks;
    }

    public static void deleteTask(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM tasks WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to delete task!");
            e.printStackTrace();
        }
    }
}
