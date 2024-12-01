package proj_bioskop;

public class Pelanggan extends Admin {
    private int umur;

    public Pelanggan(int id, String nama, String pw, int umur) {
        super(id, nama, pw);
        this.umur = umur;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
    
   @Override
    public String toString() {
        return "Pelanggan{" +
                "id=" + getId() +
                ", nama='" + getNama() + '\'' +
                ", umur=" + umur +
                '}';
    }
   
}
