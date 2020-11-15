package http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.util.List;
import java.util.concurrent.Executors;

public class myServer {
    //HttpServer server;

    public myServer(List<myHandle> handles, HttpServer server) {
        //this.server = server;
        for(myHandle hand : handles){
            System.out.println(hand.getPath());
            server.createContext(hand.getPath(), hand);
        }
        server.setExecutor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        System.out.println("Server starts on localhost:8080");
        server.start();
    }

}
