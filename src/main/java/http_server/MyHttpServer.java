package http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyHttpServer {
    static  class AccountHandler extends MyAbstractHandle{
        void Print(List<DBArray> arr){
            for(int ii=0; ii<SingletonDB.getInstance().size(); ii++){
                System.out.println("row : "+ ii + "[" + arr.get(ii).code + "]||" + "[" + arr.get(ii).name + "]||" + "[" + arr.get(ii).birth + "]||" + "[" + arr.get(ii).acnt.get(0).acnt_num + "]");
            }
        }

        @Override
        public String getPath(){
            return "/accounts"
;        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Parsing(httpExchange);
            System.out.println("Account Handler Activated");
            String[] Params = param_query.split("&");
            //int i =0;

            for(int t=0; t< Params.length; t++){
                //System.out.println("->" + i);
                int idx = Params[t].indexOf("=");
                Params[t] = Params[t].substring(idx+1);
                //System.out.println(Params[t]);
                //i++;
            }

            if(path.endsWith("/list")){

                if(httpExchange.getRequestMethod().equals("GET")) {
                    if (Params.length == 1) {
                        List<DBArray> tmp = SingletonDB.getInstance();
                        int target = Integer.parseInt(Params[0]);
                        int USER_CODE_SUCCESS = 0;
                        //System.out.println(target);
                        for (DBArray elements : tmp) {
                            if (elements.code == target) {
                                USER_CODE_SUCCESS = 1;

                                StringBuilder sb = new StringBuilder();
                                sb.append("\ncode = " + target);
                                sb.append("\nname = " + elements.name);
                                int idx = 1;
                                for(Acnt acnt : elements.acnt){
                                    sb.append("\nAccount " + "#" + idx +" = " + acnt.acnt_num);
                                    sb.append("\nAccount " + "#" + idx +" Bank_code = " + acnt.bank_code);
                                    idx++;
                                }
                                httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
                                httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                                OutputStream os = httpExchange.getResponseBody();
                                os.write(sb.toString().getBytes());
                                os.flush();

                                break;
                            }
                        }
                        if(USER_CODE_SUCCESS == 0 ) {
                            //wrong USER_CODE Request
                            String response_str = "Wrong User_code. There is not such code";
                            httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                            OutputStream os = httpExchange.getResponseBody();
                            os.write(response_str.getBytes());
                            os.flush();
                        }

                    }
                    else {
                        //page not found
                        String response_str = "Bad Request. Please check the parameter format.";
                        httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response_str.getBytes());
                        os.flush();
                    }
                }
                else{
                    //WRONG HTTP METHOD
                    String response_str = "Bad Request METHOD. Please check the HTTP Method.";
                    httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response_str.getBytes());
                    os.flush();
                }
            }
            else if(path.endsWith("/type")){
                if(httpExchange.getRequestMethod().equals("GET")) {
                    if (Params.length == 2) {
                        List<DBArray> tmp = SingletonDB.getInstance();
                        int USER_CODE_SUCCESS = 0;
                        //System.out.println(target);
                        for (DBArray elements : tmp) {
                            if (elements.code == Integer.parseInt(Params[0])) {
                                USER_CODE_SUCCESS = 1;

                                StringBuilder sb = new StringBuilder();
                                sb.append("\ncode = " + Params[0]);
                                sb.append("\nname = " + elements.name);
                                sb.append("\ntype = " + Params[1]);
                                int idx = 1;
                                for(Acnt acnt : elements.acnt){
                                    if(acnt.acnt_type.equals(Params[1])) {
                                        USER_CODE_SUCCESS = 2;
                                        sb.append("\n" + Params[1] +" Account " + "#" + idx + " = " + acnt.acnt_num);
                                        sb.append("\n" + Params[1] +" Account " + "#" + idx + " Bank_code = " + acnt.bank_code);
                                        idx++;
                                    }
                                }
                                if(USER_CODE_SUCCESS == 1 ) {
                                    //There is no such type Account
                                    sb.append("\n\nno such type in Yours");
                                    httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
                                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                                    OutputStream os = httpExchange.getResponseBody();
                                    os.write(sb.toString().getBytes());
                                    os.flush();
                                }
                                httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
                                httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                                OutputStream os = httpExchange.getResponseBody();
                                os.write(sb.toString().getBytes());
                                os.flush();

                                break;
                            }
                        }
                        if(USER_CODE_SUCCESS == 0 ) {
                            //wrong USER_CODE Request
                            String response_str = "Wrong User_code. There is not such code";
                            httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                            OutputStream os = httpExchange.getResponseBody();
                            os.write(response_str.getBytes());
                            os.flush();
                        }
                    }
                    else {
                        //page not found
                        String response_str = "Bad Request. Please check the parameter format.";
                        httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response_str.getBytes());
                        os.flush();
                    }
                }
                else{
                    //WRONG HTTP METHOD
                    String response_str = "Bad Request METHOD. Please check the HTTP Method.";
                    httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response_str.getBytes());
                    os.flush();
                }
            }
            else if(path.endsWith("/balance")){
                if(httpExchange.getRequestMethod().equals("GET")) {
                    if (Params.length == 1) {
                        List<DBArray> tmp = SingletonDB.getInstance();
                        int USER_CODE_SUCCESS = 0;
                        int sum = 0;
                        //System.out.println(target);
                        for (DBArray elements : tmp) {
                            if (elements.code == Integer.parseInt(Params[0])) {
                                USER_CODE_SUCCESS = 1;
                                StringBuilder sb = new StringBuilder();
                                sb.append("\ncode = " + Params[0]);
                                sb.append("\nname = " + elements.name);
                                sb.append("\nthe number of accounts " + elements.acnt.size());
                                for(Acnt acnt : elements.acnt){
                                    sum =  sum + acnt.balance_now;
                                }
                                sb.append("\ntotal balance = " + sum);
                                httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
                                httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                                OutputStream os = httpExchange.getResponseBody();
                                os.write(sb.toString().getBytes());
                                os.flush();

                                break;
                            }
                        }
                        if(USER_CODE_SUCCESS == 0 ) {
                            //wrong USER_CODE Request
                            String response_str = "Wrong User_code. There is not such code";
                            httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                            OutputStream os = httpExchange.getResponseBody();
                            os.write(response_str.getBytes());
                            os.flush();
                        }

                    }
                    else {
                        //page not found
                        String response_str = "Bad Request. Please check the parameter format.";
                        httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response_str.getBytes());
                        os.flush();
                    }
                }
                else{
                    //WRONG HTTP METHOD
                    String response_str = "Bad Request METHOD. Please check the HTTP Method.";
                    httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response_str.getBytes());
                    os.flush();
                }
            }
            else if(path.endsWith("/acnt_num")){
                if(httpExchange.getRequestMethod().equals("GET")) {
                    if (Params.length == 2) {
                        List<DBArray> tmp = SingletonDB.getInstance();
                        int USER_CODE_SUCCESS = 0;
                        //System.out.println(target);
                        for (DBArray elements : tmp) {
                            if (elements.code == Integer.parseInt(Params[0])) {
                                USER_CODE_SUCCESS = 1;

                                StringBuilder sb = new StringBuilder();
                                sb.append("\ncode = " + Params[0]);
                                sb.append("\nname = " + elements.name);
                                sb.append("\naccount_number = " + Params[1]);
                                for(Acnt acnt : elements.acnt){
                                    if(acnt.acnt_num.equals(Params[1])) {
                                        USER_CODE_SUCCESS = 2;
                                        sb.append("\nbalance = " + acnt.balance_now);
                                        break;
                                    }
                                }
                                if(USER_CODE_SUCCESS == 1 ) {
                                    //There is no such type Account
                                    sb.append("\n\nno such accounts in Yours. Please check acnt_num again");
                                    httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
                                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                                    OutputStream os = httpExchange.getResponseBody();
                                    os.write(sb.toString().getBytes());
                                    os.flush();
                                }
                                httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
                                httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                                OutputStream os = httpExchange.getResponseBody();
                                os.write(sb.toString().getBytes());
                                os.flush();

                                break;
                            }
                        }
                        if(USER_CODE_SUCCESS == 0 ) {
                            //wrong USER_CODE Request
                            String response_str = "Wrong User_code. There is not such code";
                            httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                            OutputStream os = httpExchange.getResponseBody();
                            os.write(response_str.getBytes());
                            os.flush();
                        }

                    }
                    else {
                        //page not found
                        String response_str = "Bad Request. Please check the parameter format.";
                        httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response_str.getBytes());
                        os.flush();
                    }
                }
                else{
                    //WRONG HTTP METHOD
                    String response_str = "Bad Request METHOD. Please check the HTTP Method.";
                    httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response_str.getBytes());
                    os.flush();
                }
            }
            else if(path.endsWith("/close")){

            }
            else if(path.endsWith("/add")){
                if(httpExchange.getRequestMethod().equals("POST")) {
                    if (Params.length == 5) {
                        List<DBArray> DB = SingletonDB.getInstance();
                        int target = Integer.parseInt(Params[0]);
                        int USER_CODE_SUCCESS = 0;
                        System.out.println(target);
                        for (DBArray elements : DB) {
                            System.out.println("howhowhow");
                            if (elements.code == target) {
                                USER_CODE_SUCCESS = 1;
                                System.out.println(USER_CODE_SUCCESS);
                                if(elements.acnt == null) {
                                    System.out.println("~~~~~~~~~~");
                                    List<Acnt> arr = new ArrayList<Acnt>(Arrays.asList(new Acnt(Params[1], Integer.parseInt(Params[2]), Integer.parseInt(Params[3]), Params[4])));
                                    elements.acnt = arr;
                                }
                                else{
                                    System.out.println("/////////////");
                                    Acnt AcntInfoTmp = new Acnt(Params[1], Integer.parseInt(Params[2]), Integer.parseInt(Params[3]), Params[4]);
                                    elements.acnt.add(AcntInfoTmp);
                                }
                                StringBuilder sb = new StringBuilder();
                                sb.append("SUCCESS");
                                sb.append("\ncode = " + target);
                                sb.append("\nadded Account = " + elements.acnt.size());
                                sb.append("\naccount number = " + Params[1]);
                                sb.append("\nbank code = " + Params[2]);
                                sb.append("\nbalance in this account = " + Params[3]);
                                sb.append("\naccount type = " + Params[4]);
                                httpExchange.sendResponseHeaders(201, sb.toString().getBytes().length);
                                httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                                OutputStream os = httpExchange.getResponseBody();
                                os.write(sb.toString().getBytes());
                                os.flush();

                                break;
                            }
                        }
                        if(USER_CODE_SUCCESS == 0 ) {
                            //wrong USER_CODE Request
                            String response_str = "Wrong User_code. There is not such code";
                            httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                            OutputStream os = httpExchange.getResponseBody();
                            os.write(response_str.getBytes());
                            os.flush();
                        }

                    }
                    else {
                        //page not found
                        String response_str = "Bad Request. Please check the parameter format.";
                        httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response_str.getBytes());
                        os.flush();
                    }
                }
                else{
                    //WRONG HTTP METHOD
                    String response_str = "Bad Request METHOD. Please check the HTTP Method.";
                    httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response_str.getBytes());
                    os.flush();
                }
                List<DBArray> tmp = SingletonDB.getInstance();
                Print(tmp);
            }
            else{
                //page not found
                String response_str = "Bad Request. Please check the path.";
                httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                OutputStream os = httpExchange.getResponseBody();
                os.write(response_str.getBytes());
                os.flush();
            }
            //List<DBArray> tmp = SingletonDB.getInstance();
            //System.out.println("[" + tmp.get(0).code + "]||" + "[" + tmp.get(0).name + "]||" + "[" + tmp.get(0).birth + "]||" + "[" + tmp.get(0).acnt.get(0).acnt_num + "]");

            byte[] readBytes = httpExchange.getRequestBody().readAllBytes();
            String read = new String(readBytes, StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request Body: " + read);
            System.out.println("------------------------------------");
            //httpExchange.sendResponseHeaders(200, readBytes.length);
            //OutputStream os = httpExchange.getResponseBody();
            //os.write(readBytes);
            //os.flush();
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
            System.out.println(path + "^^^^" + param_query);
            System.out.println("UserHandler Acctivated");

            String[] Params = param_query.split("&");
            //int i =0;

            for(int t=0; t< Params.length; t++){
                //System.out.println("->" + i);
                int idx = Params[t].indexOf("=");
                Params[t] = Params[t].substring(idx+1);
                //System.out.println(Params[t]);
                //i++;
            }

            if(path.endsWith("/legister")){
                //System.out.println("~~~~~~~~~~legister IN!!!");

                if(httpExchange.getRequestMethod().equals("POST")) {
                    if (Params.length == 3) {
                        //System.out.println("RIGHT LEGISTER IN!!!!!!!!!!!!!!!!");
                        int user_code = (int) (Math.random() * 10000); //making random user code to provide

                        //System.out.println("#####");
                        DBArray element = new DBArray(user_code, Params[0], LocalDate.parse(Params[1], DateTimeFormatter.ISO_DATE), Params[2]);

                        //System.out.println("!@");
                        List<DBArray> arr = SingletonDB.getInstance();
                        //System.out.println("?");
                        arr.add(element);

                        StringBuilder sb = new StringBuilder();
                        sb.append("user_code = " + user_code);
                        httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(sb.toString().getBytes());
                        os.flush();
                    }
                    else {
                        //page not found
                        String response_str = "Bad Request. Please check the parameter format.";
                        httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response_str.getBytes());
                        os.flush();
                    }
                /*
                List<DBArray> tmp = SingletonDB.getInstance();
                for(int ii=0; ii<SingletonDB.getInstance().size(); ii++){
                    System.out.println("[" + tmp.get(ii).code + "]||" + "[" + tmp.get(ii).name + "]||" + "[" + tmp.get(ii).birth + "]");
                }
                */
                }
                else{
                    //WRONG HTTP METHOD
                    String response_str = "Bad Request METHOD. Please check the HTTP Method.";
                    httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response_str.getBytes());
                    os.flush();
                }
            }

            else if(path.endsWith("/close")){
                if(httpExchange.getRequestMethod().equals("DELETE")) {
                    if (Params.length == 1) {
                        //System.out.println("RIGHT CLOSE IN!!!!!!!!!!!!!!!!");
                        List<DBArray> tmp = SingletonDB.getInstance();

                        int USER_CODE_SUCCESS = 0;
                        int target = Integer.parseInt(Params[0]);
                        //System.out.println(target);
                        for(DBArray elements : tmp){
                            if(elements.code == target){
                                tmp.remove(elements);
                                USER_CODE_SUCCESS = 1;
                                break;
                            }
                        }
                        if(USER_CODE_SUCCESS == 0 ){
                            //wrong USER_CODE Request
                            String response_str = "Wrong User_code. There is not such code";
                            httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                            OutputStream os = httpExchange.getResponseBody();
                            os.write(response_str.getBytes());
                            os.flush();
                        }
                        else{
                            String response_str = "USER_Info Delete Success";
                            httpExchange.sendResponseHeaders(200, response_str.getBytes().length);
                            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                            OutputStream os = httpExchange.getResponseBody();
                            os.write(response_str.getBytes());
                            os.flush();
                        }
                    }
                    else{
                        //page not found
                        String response_str = "Bad Request. Please check the parameter format.";
                        httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response_str.getBytes());
                        os.flush();
                    }
                /*
                List<DBArray> tmp = SingletonDB.getInstance();
                for(int ii=0; ii<SingletonDB.getInstance().size(); ii++){
                    System.out.println("[" + tmp.get(ii).code + "]||" + "[" + tmp.get(ii).name + "]||" + "[" + tmp.get(ii).birth + "]");
                }
                */
                }
                else{
                    //WRONG HTTP METHOD
                    String response_str = "Bad Request METHOD. Please check the HTTP Method.";
                    httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response_str.getBytes());
                    os.flush();
                }
            }

            else if(path.endsWith("me")){
                if(httpExchange.getRequestMethod().equals("GET")) {
                    if (Params.length == 1) {
                        List<DBArray> tmp = SingletonDB.getInstance();
                        int target = Integer.parseInt(Params[0]);
                        //System.out.println(target);
                        for(DBArray elements : tmp){
                            if(elements.code == target){
                                StringBuilder sb = new StringBuilder();
                                sb.append("code = " + target);
                                sb.append("\nname = " + elements.name);
                                sb.append("\nbirth = " + elements.birth);
                                sb.append("\ngender = " + elements.gender);
                                sb.append("\nthe num of Accounts = " + elements.acnt.size());
                                httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
                                httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                                OutputStream os = httpExchange.getResponseBody();
                                os.write(sb.toString().getBytes());
                                os.flush();
                                break;
                            }
                        }
                    }
                    else{
                        //page not found
                        String response_str = "Bad Request. Please check the parameter format.";
                        httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response_str.getBytes());
                        os.flush();
                    }
                /*
                List<DBArray> tmp = SingletonDB.getInstance();
                for(int ii=0; ii<SingletonDB.getInstance().size(); ii++){
                    System.out.println("[" + tmp.get(ii).code + "]||" + "[" + tmp.get(ii).name + "]||" + "[" + tmp.get(ii).birth + "]");
                }
                */
                }
                else{
                    //WRONG HTTP METHOD
                    String response_str = "Bad Request METHOD. Please check the HTTP Method.";
                    httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                    httpExchange.getResponseHeaders().set("Content-Type", "text/html");
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response_str.getBytes());
                    os.flush();
                }
            }
            else{
                //page not found
                String response_str = "Bad Request. Please check the path.";
                httpExchange.sendResponseHeaders(400, response_str.getBytes().length);
                OutputStream os = httpExchange.getResponseBody();
                os.write(response_str.getBytes());
                os.flush();
            }

            //StringBuilder sb = new StringBuilder();

            byte[] readBytes = httpExchange.getRequestBody().readAllBytes();
            String read = new String(readBytes, StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request Body: " + read);

            //sb.append("Request Method: " + httpExchange.getRequestMethod() + "\n");
            //sb.append("Request Body: " + read + "\n");

            //httpExchange.sendResponseHeaders(200, sb.toString().getBytes().length);
            //httpExchange.getResponseHeaders().set("Content-Type", "text/html");
            //OutputStream os = httpExchange.getResponseBody();
            //os.write(sb.toString().getBytes());
            //os.flush();
        }

    }

    /*
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
    */

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        
        List<MyHandle> handlers =  List.of(new AccountHandler(), new UserHandler());


        List<Acnt> myAc = new ArrayList<Acnt>(Arrays.asList(new Acnt("1002850738938", 4,10000000, "checking") ) );
        DBArray element =  new DBArray(1, "kevin", LocalDate.now(), "male", myAc);
        List<DBArray> arr = SingletonDB.getInstance();
        arr.add(element);


        myServer MyLittleBank = new myServer(handlers, server);
        MyLittleBank.start();

    }
}
