package http_server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public abstract class MyAbstractHandle implements MyHandle {
    public String path, param_query;
    abstract public String getPath();
    @Override
    abstract public void handle(HttpExchange httpExchange) throws IOException;

    public void Parsing(HttpExchange httpExchange) throws IOException{
        path = httpExchange.getRequestURI().getPath();
        param_query = httpExchange.getRequestURI().getQuery();
    }

}
