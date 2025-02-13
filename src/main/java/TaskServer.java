package main.java;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TaskServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/tasks", exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = Database.getTasks().toString();
                exchange.sendResponseHeaders(200, response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
            }
        });
        server.start();
        System.out.println("Server started on port 8080");
    }
}
