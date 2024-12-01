package proj_bioskop;
import java.time.LocalDateTime;

public class Transaksi {
    private int id_transaksi;
    private String id_film;
    private int id_pelanggan;
    private String no_kursi;
    private int jumlah_tiket;
    private double total_harga;
    LocalDateTime tanggal_transaksi;

    public Transaksi(int id_transaksi, String id_film, int id_pelanggan, String no_kursi, int jumlah_tiket, double total_harga, LocalDateTime tanggal_transaksi) {
        this.id_transaksi = id_transaksi;
        this.id_film = id_film;
        this.id_pelanggan = id_pelanggan;
        this.no_kursi = no_kursi;
        this.jumlah_tiket = jumlah_tiket;
        this.total_harga = total_harga;
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_film() {
        return id_film;
    }

    public void setId_film(String id_film) {
        this.id_film = id_film;
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNo_kursi() {
        return no_kursi;
    }

    public void setNo_kursi(String no_kursi) {
        this.no_kursi = no_kursi;
    }

    public int getJumlah_tiket() {
        return jumlah_tiket;
    }

    public void setJumlah_tiket(int jumlah_tiket) {
        this.jumlah_tiket = jumlah_tiket;
    }

    public double getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(double total_harga) {
        this.total_harga = total_harga;
    }

    public LocalDateTime getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(LocalDateTime tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    } 
    
     @Override
    public String toString() {
        return "Transaksi{" +
                "idTransaksi=" + id_transaksi +
                ", idFilm='" + id_film + '\'' +
                ", idPelanggan=" + id_pelanggan +
                ", noKursi='" + no_kursi + '\'' +
                ", jumlahTiket=" + jumlah_tiket +
                ", totalHarga=" + total_harga +
                ", tanggalTransaksi=" + tanggal_transaksi +
                '}';
    }
    
}
