package tr_pbo.controller;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class Koneksi {
    public static Connection con;
    public static Statement stm;
    
    public void config(){
        try {
            String url = "jdbc:mysql://localhost/db_inventaris";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(url, user, pass);
            
            stm = (Statement) con.createStatement();
            System.out.println("Koneksi Berhasil...");
        } catch (Exception e) {
            System.out.println("Koneksi Gagal...");
        }
    }
}
