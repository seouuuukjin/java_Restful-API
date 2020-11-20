package http_server;

import java.util.ArrayList;
import java.util.List;

public class SingletonDB {
    private static List<DBArray> dbArray = null;
    private SingletonDB(){}

    public static List<DBArray> getInstance(){
        if(dbArray == null){
            dbArray = new ArrayList<DBArray>();
        }
        return dbArray;
    }
}
