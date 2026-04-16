package tr_pbo.controller;
import java.sql.ResultSet;
import java.sql.Statement;
import tr_pbo.model.Admin;

public class AdminController {
    Statement stm;
    ResultSet res;
    String sql;
    Admin adm = new Admin();
    
    public AdminController(){
        Koneksi db = new Koneksi();
        db.config();
        stm = db.stm;
    }
    
    public boolean cekLogin(String un, String pw){
        adm.setUsername(un);
        adm.setPassword(pw);
        boolean status = false;
        
        try {
            sql = "SELECT * FROM tb_admin WHERE username = '" + adm.getUsername()+ "'AND password = '" + adm.getPassword()+ "'";
            this.res = stm.executeQuery(sql);
            if (res.next()) status = true;
            else status = false;
        } catch (Exception e) {
            System.out.println("Query Gagal");
        }
        return status;
    }
}
