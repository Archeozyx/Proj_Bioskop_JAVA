import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BuyTicketForm extends JDialog {
    private Admin user;
    private Film film;
    private JComboBox<Integer> comboNoKursi; // Changed to JComboBox
    private JTextField txtJumlahTiket;
    private DAO dao;
    private UserView userView;

    public BuyTicketForm(UserView userView, Admin user, Film film) {
        this.user = user;
        this.film = film;
        this.dao = new DAO();
        this.userView = userView;
        initComponents();
    }

    private void initComponents() {
        setTitle("Buy Ticket");
        setSize(400, 300);
        setLocationRelativeTo(userView);
        setModal(true);
        setLayout(null);

        JLabel lblFilm = new JLabel("Film: " + film.getJudul());
        lblFilm.setBounds(30, 30, 300, 25);
        add(lblFilm);

        JLabel lblNoKursi = new JLabel("No Kursi:");
        lblNoKursi.setBounds(30, 70, 100, 25);
        add(lblNoKursi);

        // Fetch available seats
        List<Integer> availableSeats = getAvailableSeats();

        comboNoKursi = new JComboBox<>(availableSeats.toArray(new Integer[0]));
        comboNoKursi.setBounds(140, 70, 200, 25);
        comboNoKursi.setSelectedIndex(-1); // No selection by default
        add(comboNoKursi);

        JLabel lblJumlahTiket = new JLabel("Jumlah Tiket:");
        lblJumlahTiket.setBounds(30, 110, 100, 25);
        add(lblJumlahTiket);

        txtJumlahTiket = new JTextField("1");
        txtJumlahTiket.setBounds(140, 110, 200, 25);
        add(txtJumlahTiket);

        JButton btnBuy = new JButton("Buy");
        btnBuy.setBounds(140, 160, 80, 25);
        add(btnBuy);

        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (comboNoKursi.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Please select a seat number.");
                        return;
                    }
                    int noKursi = (Integer) comboNoKursi.getSelectedItem();
                    int jumlahTiket = Integer.parseInt(txtJumlahTiket.getText());
                    double totalHarga = jumlahTiket * film.getHarga();
                    LocalDateTime tanggalTransaksi = LocalDateTime.now();

                    Transaksi transaksi = new Transaksi(0, film.getId_film(), user.getId(), String.valueOf(noKursi), jumlahTiket, totalHarga, tanggalTransaksi);
                    dao.createTransaksi(transaksi);
                    JOptionPane.showMessageDialog(null, "Ticket Purchased Successfully!");
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to Purchase Ticket! " + ex.getMessage());
                }
            }
        });
    }

    // Method to fetch available seats (1-50) excluding those already taken
    private List<Integer> getAvailableSeats() {
        List<Integer> seats = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            seats.add(i);
        }
        try {
            List<String> takenSeats = dao.getTakenSeatsForFilm(film.getId_film());
            for (String seat : takenSeats) {
                seats.remove(Integer.valueOf(seat));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return seats;
    }
}
