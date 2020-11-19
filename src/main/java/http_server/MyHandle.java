package http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.URI;

public interface MyHandle extends HttpHandler {
    //boolean match(URI uri);

    String getPath();
        //return httpExchange.getRequestURI().getPath();
    @Override
    void handle(HttpExchange httpExchange) throws IOException;

}
