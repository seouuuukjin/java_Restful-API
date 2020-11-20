package http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MyHttpServer {
    static  class AccountHandler extends MyAbstractHandle{
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


    static  class UserHandler extends MyAbstractHandle {
        @Override
        public String getPath(){
            return "/user";
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Parsing(httpExchange);
            //System.out.println(path + "[[[[" + param_query);
            System.out.println("UserHandler Acctivated");
            StringBuilder sb = new StringBuilder();



            byte[] readBytes = httpExchange.getRequestBody().readAllBytes();
            String read = new String(readBytes, StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request Body: " + read);

            sb.append("Request Method: " + httpExchange.getRequestMethod() + "\n");
            sb.append("Request Body: " + read + "\n");

            httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
            OutputStream os = httpExchange.getResponseBody();

            os.write(sb.toString().getBytes());
            os.flush();
        }

    }

    static  class RootHandler extends MyAbstractHandle {
        @Override
        public String getPath(){
            return "/";
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println(httpExchange.getRequestURI() + "@@" + httpExchange.getRequestURI().getQuery());
            System.out.println("Root Handler Activated");
            URI uri = httpExchange.getRequestURI();
            String param_qurey = uri.getQuery();
            String path = uri.getPath();
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
        
        List<MyHandle> handlers =  List.of(new AccountHandler(), new UserHandler());
        DBArray element
        List<DBArray> arr = SingletonDB.getInstance();
        arr.add()
        myServer MyLittleBank = new myServer(handlers, server);
        MyLittleBank.start();

    }
}
