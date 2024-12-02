public class Film {
    private int id_film; // Changed from String to int
    private String judul;
    private String sutradara;
    private float rating;
    private double harga;

    // Constructor without id_film (for new films)
    public Film(String judul, String sutradara, float rating, double harga) {
        this.judul = judul;
        this.sutradara = sutradara;
        this.rating = rating;
        this.harga = harga;
    }

    // Constructor with id_film (for existing films)
    public Film(int id_film, String judul, String sutradara, float rating, double harga) {
        this.id_film = id_film;
        this.judul = judul;
        this.sutradara = sutradara;
        this.rating = rating;
        this.harga = harga;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSutradara() {
        return sutradara;
    }

    public void setSutradara(String sutradara) {
        this.sutradara = sutradara;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id_film=" + id_film +
                ", judul='" + judul + '\'' +
                ", sutradara='" + sutradara + '\'' +
                ", rating=" + rating +
                ", harga=" + harga +
                '}';
    }
}
