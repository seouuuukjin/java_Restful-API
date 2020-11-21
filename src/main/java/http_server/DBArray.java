package http_server;

import java.time.LocalDate;
import java.util.List;

public class DBArray{
    //data structure for Bank. List<DBArray> will be whole DataBase.
    public int code;
    public String name;
    public LocalDate birth;
    public String gender;
    public List<Acnt> acnt = null;

    public DBArray (int code, String name, LocalDate birth, String gender, List<Acnt> tmp_Arr){
        this.code = code;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.acnt = tmp_Arr;
    }

    //constructor for /register
    public DBArray (int code, String name, LocalDate birth, String gender){
        this.code = code;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
    }

}
