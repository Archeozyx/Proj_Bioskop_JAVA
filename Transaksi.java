import java.time.LocalDateTime;

public class Transaksi {
    private int id_transaksi;
    private int id_film; // Changed to int
    private int id_admin;
    private String no_kursi;
    private int jumlah_tiket;
    private double total_harga;
    private LocalDateTime tanggal_transaksi;

    public Transaksi(int id_transaksi, int id_film, int id_admin, String no_kursi, int jumlah_tiket, double total_harga, LocalDateTime tanggal_transaksi) {
        this.id_transaksi = id_transaksi;
        this.id_film = id_film;
        this.id_admin = id_admin;
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

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
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
                "id_transaksi=" + id_transaksi +
                ", id_film=" + id_film +
                ", id_admin=" + id_admin +
                ", no_kursi='" + no_kursi + '\'' +
                ", jumlah_tiket=" + jumlah_tiket +
                ", total_harga=" + total_harga +
                ", tanggal_transaksi=" + tanggal_transaksi +
                '}';
    }
}
