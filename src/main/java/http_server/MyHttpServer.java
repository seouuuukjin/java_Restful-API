package http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

public class MyHttpServer {

    static  class hand implements myHandle {
        @Override
        public String getPath(HttpExchange httpExchange);

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

        }

    }
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        List<myHandle> handlers = new ArrayList<>();
        handlers.add((httpExchange) -> {httpExchange.getRequestURI().getPath()}, )
        myServer(handlers, server);
        server.setExecutor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        System.out.println("Server starts on localhost:8080");
        server.start();
    }

}
