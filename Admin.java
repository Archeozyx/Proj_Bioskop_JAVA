public class Admin {
    private int id;
    private String nama;
    private String pw;
    private String privilege; // "user" or "admin"

    public Admin(int id, String nama, String pw, String privilege) {
        this.id = id;
        this.nama = nama;
        this.pw = pw;
        this.privilege = privilege;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPw() {
        return pw;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", privilege='" + privilege + '\'' +
                '}';
    }
}
