package http_server;

import java.time.LocalDate;
import java.util.List;

public class DBArray extends Acnt{
    //data structure for Bank. List<DBArray> will be whole DataBase.
    public int code;
    public String name;
    public LocalDate birth;
    public String gender;
    List<Acnt> acnt;
    public DBArray (int code, String name, LocalDate birth, String gender, List<Acnt> tmp_Arr){
        this.code = code;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.acnt = tmp_Arr;
    }
}
