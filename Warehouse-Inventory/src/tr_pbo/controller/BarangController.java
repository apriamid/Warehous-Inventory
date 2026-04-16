package tr_pbo.controller;

import tr_pbo.model.Barang;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BarangController {

    Statement stat;
    ResultSet res;
    String sql;
    DefaultTableModel dtm = new DefaultTableModel();
    Barang br = new Barang();

    public BarangController() {
        Koneksi db = new Koneksi();
        db.config();
        stat = db.stm;
    }

    public DefaultTableModel createTable() {
        dtm.addColumn("Kode Barang");
        dtm.addColumn("Nama Barang");
        dtm.addColumn("Kategori");
        dtm.addColumn("Varian");

        return this.dtm;
    }

    public void tampilkanBarang() {
        try {
            dtm.getDataVector().removeAllElements();
            dtm.fireTableDataChanged();

            //SQL Query
            this.sql = "SELECT * FROM tb_barang";

            res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[4];
                obj[0] = res.getString("kode_barang");
                obj[1] = res.getString("nama_barang");
                obj[2] = res.getString("kategori");
                obj[3] = res.getString("varian");
                dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Query Gagal");
        }
    }

    //INSERT
    public boolean tambahBarang(String a, String b, String c) {
        try {
            br.setNama_barang(a);
            br.setKategori(b);
            br.setVarian(c);
            
            this.sql = "INSERT INTO tb_barang(nama_barang, kategori, varian) VALUES " 
                        + "('" + br.getNama_barang()+"','" + br.getKategori()+ "','" + br.getVarian()+ "');"; 
            this.stat.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //UPDATE
    public boolean ubahBarang(int a, String b, String c, String d) {
        br.setKode_barang(a);
        br.setNama_barang(b);
        br.setKategori(c);
        br.setVarian(d);
        try {
            //Query
            this.sql = "UPDATE tb_barang SET nama_barang = '" + br.getNama_barang() + "',"
                    + "kategori = '" + br.getKategori() + "'," + "varian = '" + br.getVarian()+ "' WHERE kode_barang = " + br.getKode_barang();
            //Menjalankan query
            this.stat.executeUpdate(sql);
            return true;
        } catch (Exception f) {
            return false;
        }
    }

    //DELETE
    public boolean hapusBarang(int a) {
        br.setKode_barang(a);

        try {
            //Query
            this.sql = "DELETE FROM tb_barang WHERE kode_barang = " + br.getKode_barang();

            //Menjalankan query
            this.stat.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean notifikasi(){
        this.sql = "SELECT kode_barang, stok FROM tb_transaksi;";
        try {
            res = stat.executeQuery(sql);
            
            while (res.next()) {
                int stok = res.getInt("stok");
                int kodeBarang = res.getInt("kode_barang");
                if (stok < 50) {
                    JOptionPane.showMessageDialog(null, "Kode Barang: " + kodeBarang + " memiliki stok rendah: " + stok);
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    
    public DefaultTableModel createTableTransaksi() {
        dtm.addColumn("Id Transaksi");
        dtm.addColumn("Kode Barang");
        dtm.addColumn("Stok");
        dtm.addColumn("Harga");
        dtm.addColumn("Status Transaksi");
        dtm.addColumn("Tanggal Transaksi");

        return this.dtm;
    }
    

    public void tampilkanTransaksi() {
        try {
            dtm.getDataVector().removeAllElements();
            dtm.fireTableDataChanged();

            //SQL Query
            this.sql = "SELECT * FROM tb_transaksi";

            res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[6];
                obj[0] = res.getString("id_transaksi");
                obj[1] = res.getString("kode_barang");
                obj[2] = res.getString("stok");
                obj[3] = res.getString("status_transaksi");
                obj[4] = res.getString("tanggal_transaksi");
                obj[5] = res.getString("harga");
                dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Query Gagal");
        }
    }
    
    //INSERT TRANSAKSI
    public boolean tambahTransaksi(int a, int b, double c, String d, LocalDate e) {
        try {
            br.setKode_barang(a);
            br.setStok(b);
            br.setHarga_barang(c);
            br.setStatus_transaksi(d);

            this.sql = "INSERT INTO tb_transaksi(kode_barang, stok, harga, status_transaksi, tanggal_transaksi) VALUES "
                     + "(" + br.getKode_barang() + "," + br.getStok() + "," + br.getHarga_barang() + ",'" 
                     + br.getStatus_transaksi() + "','" + java.sql.Date.valueOf(e) + "');";
            this.stat.executeUpdate(sql);

            return true;
        } catch (Exception z) {
            return false;
        }
    }

    
    //UPDATE TRANSAKSI
    public boolean ubahTransaksi(int a, int b, int c, double d, String e, LocalDate f) {
        br.setId_transaksi(a);
        br.setKode_barang(b);
        br.setStok(c);
        br.setHarga_barang(d);
        br.setStatus_transaksi(e);

        try {
            this.sql = "UPDATE tb_transaksi SET stok = " + br.getStok() + ", harga = " + br.getHarga_barang() + ", status_transaksi = '"
                    + br.getStatus_transaksi() + "', tanggal_transaksi = '" + java.sql.Date.valueOf(f) + "' WHERE id_transaksi = "
                    + br.getId_transaksi() + " AND kode_barang = " + br.getKode_barang();
            // Menjalankan query
            this.stat.executeUpdate(sql);
            return false;
        } catch (Exception z) {
            return false;
        }
    }
    
    public boolean hapusTransaksi(int a) {
        br.setId_transaksi(a);

        try {
            this.sql = "DELETE FROM tb_transaksi WHERE id_transaksi = " + br.getId_transaksi();
            this.stat.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    public DefaultTableModel laporanTransaksi() {
//            //    // Reset model tabel
//            //    "SELECT stok FROM tb_barang WHERE tb_barang.kode_barang = tb_transaksi.kode_barang";
//            //    SUM(CASE WHEN tb_transaksi.status_transaksi = 'Masuk' THEN tb_transaksi.jumlah_barang ELSE 0 END)
//            //    SUM(CASE WHEN tb_transaksi.status_transaksi = 'Keluar' THEN tb_transaksi.jumlah_barang ELSE 0 END)
//            //    int stok akhir stok_awal + total_masuk - total_keluar
//
//    DefaultTableModel dtmLaporan = new DefaultTableModel();
//    dtmLaporan.addColumn("Kode Barang");
//    dtmLaporan.addColumn("Stok Masuk");
//    dtmLaporan.addColumn("Stok Keluar");
//    dtmLaporan.addColumn("Stok Akhir");
//
//    try {
//        this.sql = "SELECT tb_transaksi.kode_barang, " +
//                   "SUM(CASE WHEN tb_transaksi.status_transaksi = 'Masuk' THEN tb_transaksi.jumlah_barang ELSE 0 END) AS total_masuk, " +
//                   "SUM(CASE WHEN tb_transaksi.status_transaksi = 'Keluar' THEN tb_transaksi.jumlah_barang ELSE 0 END) AS total_keluar, " +
//                   "(COALESCE(SUM(CASE WHEN tb_transaksi.status_transaksi = 'Masuk' THEN tb_transaksi.jumlah_barang ELSE 0 END), 0) - " +
//                   "COALESCE(SUM(CASE WHEN tb_transaksi.status_transaksi = 'Keluar' THEN tb_transaksi.jumlah_barang ELSE 0 END), 0)) AS stok_akhir " +
//                   "FROM tb_transaksi " +
//                   "GROUP BY tb_transaksi.kode_barang " +
//                   "ORDER BY tb_transaksi.kode_barang";
//
//        res = stat.executeQuery(sql);
//        while (res.next()) {
//            Object[] row = new Object[4];
//            row[0] = res.getInt("kode_barang");
//            row[1] = res.getInt("total_masuk");
//            row[2] = res.getInt("total_keluar"); 
//            row[3] = res.getInt("stok_akhir");
//            dtmLaporan.addRow(row);
//        }
//    } catch (Exception e) {
//        System.out.println("Error dalam menampilkan laporan transaksi: " + e.getMessage());
//    }
//
//    return dtmLaporan;
//}


    
    
}