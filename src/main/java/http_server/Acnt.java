package http_server;

public class Acnt {
    //data Structure for Acnt Info. List<Acnt> will be part of DBArray.
    public String acnt_num;
    public int bank_code;
    public String acnt_type;
    public int balance_now;
    public Acnt(String acnt_num, int bank_code, String acnt_type, int balance_now){
        this.acnt_num = acnt_num;
        this.acnt_type = acnt_type;
        this.balance_now = balance_now;
        this.bank_code = bank_code;
    }
}
