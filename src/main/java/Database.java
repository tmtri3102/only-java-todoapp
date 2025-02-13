package main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test")) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE tasks (id INT PRIMARY KEY, description VARCHAR(255), completed BOOLEAN)");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void saveTask(Task task) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test")) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tasks (id, description, completed) VALUES (?, ?, ?)");
            stmt.setInt(1, task.id());
            stmt.setString(2, task.description());
            stmt.setBoolean(3, task.completed());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");
            while (rs.next()) {
                tasks.add(new Task(rs.getInt("id"), rs.getString("description"), rs.getBoolean("completed")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void deleteTask(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test")) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tasks WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
