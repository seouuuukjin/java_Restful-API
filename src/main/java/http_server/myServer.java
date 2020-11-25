package http_server;

import com.sun.net.httpserver.HttpServer;

import java.util.List;

public class myServer {
    HttpServer BankServer;

    public myServer(List<MyHandle> handles, HttpServer server) {
        BankServer = server;
        for(MyHandle handler : handles){
            System.out.println(handler.getPath());
            BankServer.createContext(handler.getPath(), handler);
        }

    }
    public void start(){
        this.BankServer.start();
        System.out.println("Server starts on localhost:8080");
    }
}
