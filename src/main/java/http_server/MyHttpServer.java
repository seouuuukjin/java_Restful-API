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

    static  class AccountHandler implements myHandle {
        @Override
        public String getPath(){
            return "/accounts"
;        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println("Account Handler Activated");
            byte[] readBytes = httpExchange.getRequestBody().readAllBytes();
            String read = new String(readBytes, StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request Body: " + read);
            httpExchange.sendResponseHeaders(200, readBytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(readBytes);
            os.flush();
        }

    }
    static  class UserHandler implements myHandle {
        @Override
        public String getPath(){
            return "/user";
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println("UserHandler Acctivated");
            byte[] readBytes = httpExchange.getRequestBody().readAllBytes();
            String read = new String(readBytes, StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request Body: " + read);
            httpExchange.sendResponseHeaders(200, readBytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(readBytes);
            os.flush();
        }

    }
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        
        List<myHandle> handlers =  List.of(new AccountHandler(), new UserHandler());
        new myServer(handlers, server);

        //myServer.start();
    }

}
