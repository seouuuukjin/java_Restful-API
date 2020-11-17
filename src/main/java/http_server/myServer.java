package http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.util.List;
import java.util.concurrent.Executors;

public class myServer {
    //HttpServer server;

    public myServer(List<myHandle> handles, HttpServer server) {
        //this.server = server;
        for(myHandle handler : handles){
            System.out.println(handler.getPath());
            server.createContext(handler.getPath(), handler);
        }
        server.setExecutor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        System.out.println("Server starts on localhost:8080");
        server.start();
    }

}
