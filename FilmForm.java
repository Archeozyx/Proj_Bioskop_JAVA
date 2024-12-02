import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FilmForm extends JDialog {
    private JTextField txtJudul;
    private JTextField txtSutradara;
    private JSlider sliderRating; // Changed to slider
    private JTextField txtHarga;
    private DAO dao;
    private AdminView adminView;
    private Film film;

    public FilmForm(AdminView adminView, Film film) {
        this.dao = new DAO();
        this.adminView = adminView;
        this.film = film;
        initComponents();
    }

    private void initComponents() {
        setTitle(film == null ? "Add New Film" : "Edit Film");
        setSize(400, 300); // Adjusted size
        setLocationRelativeTo(adminView);
        setModal(true);
        setLayout(null);

        // Removed ID Film input field

        JLabel lblJudul = new JLabel("Judul:");
        lblJudul.setBounds(30, 30, 100, 25);
        add(lblJudul);

        txtJudul = new JTextField();
        txtJudul.setBounds(140, 30, 200, 25);
        add(txtJudul);

        JLabel lblSutradara = new JLabel("Sutradara:");
        lblSutradara.setBounds(30, 70, 100, 25);
        add(lblSutradara);

        txtSutradara = new JTextField();
        txtSutradara.setBounds(140, 70, 200, 25);
        add(txtSutradara);

        JLabel lblRating = new JLabel("Rating:");
        lblRating.setBounds(30, 110, 100, 25);
        add(lblRating);

        // Replace txtRating with slider
        sliderRating = new JSlider(0, 50, 25); // 0.0 to 5.0, scaled by 10
        sliderRating.setBounds(140, 110, 200, 45);
        sliderRating.setMajorTickSpacing(10);
        sliderRating.setMinorTickSpacing(1);
        sliderRating.setPaintTicks(true);
        sliderRating.setPaintLabels(true);
        sliderRating.setLabelTable(sliderLabels());
        add(sliderRating);

        JLabel lblHarga = new JLabel("Harga:");
        lblHarga.setBounds(30, 160, 100, 25);
        add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(140, 160, 200, 25);
        add(txtHarga);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(140, 210, 80, 25);
        add(btnSave);

        if (film != null) {
            txtJudul.setText(film.getJudul());
            txtSutradara.setText(film.getSutradara());
            sliderRating.setValue((int)(film.getRating() * 10));
            txtHarga.setText(String.valueOf(film.getHarga()));
        }

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String judul = txtJudul.getText();
                    String sutradara = txtSutradara.getText();
                    float rating = sliderRating.getValue() / 10.0f;
                    double harga = Double.parseDouble(txtHarga.getText());

                    if (FilmForm.this.film == null) {
                        // Creating new film
                        Film newFilm = new Film(judul, sutradara, rating, harga);
                        dao.createFilm(newFilm);
                    } else {
                        // Updating existing film
                        film.setJudul(judul);
                        film.setSutradara(sutradara);
                        film.setRating(rating);
                        film.setHarga(harga);
                        dao.updateFilm(film);
                    }
                    adminView.loadFilms();
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Helper method to create labels for the slider
    private java.util.Hashtable<Integer, JLabel> sliderLabels() {
        java.util.Hashtable<Integer, JLabel> labelTable = new java.util.Hashtable<>();
        labelTable.put(0, new JLabel("0.0"));
        labelTable.put(10, new JLabel("1.0"));
        labelTable.put(20, new JLabel("2.0"));
        labelTable.put(30, new JLabel("3.0"));
        labelTable.put(40, new JLabel("4.0"));
        labelTable.put(50, new JLabel("5.0"));
        return labelTable;
    }
}
