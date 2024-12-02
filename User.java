public class User {
    private int id;
    private String nama;
    private String pw;
    private String privilege;  // "user" or "admin"

    public User(int id, String nama, String pw, String privilege) {
        this.id = id;
        this.nama = nama;
        this.pw = pw;
        this.privilege = privilege;
    }

    // Getters and setters
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

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", privilege='" + privilege + '\'' +
                '}';
    }
}
