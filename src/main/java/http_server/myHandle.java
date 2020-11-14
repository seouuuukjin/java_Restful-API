package http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public interface myHandle extends HttpHandler {
    //boolean match(URI uri);

    String getPath(HttpExchange httpExchange);
        //return httpExchange.getRequestURI().getPath();
    @Override
    void handle(HttpExchange httpExchange) throws IOException;

}
