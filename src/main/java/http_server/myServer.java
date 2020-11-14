package http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.util.List;

public class myServer {
    HttpServer server;

    public myServer(List<myHandle> handles, HttpServer server) {
        this.server = server;
        for(myHandle handle : handles){
            //server.createContext(handle.getPath(httpExchange), handle);
        }
    }

    public void start(){
        this.server.start();
    }
}
