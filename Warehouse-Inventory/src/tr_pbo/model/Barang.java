package tr_pbo.model;
import java.time.LocalDate;

public class Barang {
    private int kode_barang;
    private String nama_barang;
    private String kategori;
    private String varian;
    private int stok;
    private double harga_barang;
    private int id_transaksi;
    private String status_transaksi;
    private LocalDate tanggal_transaksi;

    public int getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(int kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(double harga_barang) {
        this.harga_barang = harga_barang;
    }


    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getStatus_transaksi() {
        return status_transaksi;
    }

    public void setStatus_transaksi(String status_transaksi) {
        this.status_transaksi = status_transaksi;
    }

    public LocalDate getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(LocalDate tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public String getVarian() {
        return varian;
    }

    public void setVarian(String varian) {
        this.varian = varian;
    }
}
