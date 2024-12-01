package proj_bioskop;

public class Admin {
    private int id;
    private String nama;
    private String pw;

    public Admin(int id, String nama, String pw) {
        this.id = id;
        this.nama = nama;
        this.pw = pw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                '}';
    }
    
}
