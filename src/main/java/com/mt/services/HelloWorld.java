// package com.mt;   <-- agar aapki file mein package hai toh wahi line yahan rakho

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {
        String msg = "Hello Welcome to Maven Build Tool !! Today Date is: " + new Date();
        System.out.println(msg);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            String response = "<h2>" + msg + "</h2>";
            byte[] bytes = response.getBytes();
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });
        server.start();
        System.out.println("Server started on port 8080");
    }
}
