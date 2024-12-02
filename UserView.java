import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UserView extends JFrame {
    private Admin user;
    private DAO dao;
    private JTable tblFilms;
    private FilmTableModel filmTableModel;

    public UserView(Admin user) {
        this.user = user;
        dao = new DAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("User Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem logoutItem = new JMenuItem("Logout");
        menu.add(logoutItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        logoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginForm().setVisible(true);
                dispose();
            }
        });

        // Main Panel
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // Film Table
        filmTableModel = new FilmTableModel();
        tblFilms = new JTable(filmTableModel);
        JScrollPane scrollPane = new JScrollPane(tblFilms);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Load Films
        loadFilms();

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        JButton btnBuy = new JButton("Buy Ticket");

        buttonsPanel.add(btnBuy);

        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblFilms.getSelectedRow();
                if (selectedRow != -1) {
                    Film film = filmTableModel.getFilmAt(selectedRow);
                    new BuyTicketForm(UserView.this, user, film).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a film to buy ticket.");
                }
            }
        });
    }

    public void loadFilms() {
        try {
            List<Film> films = dao.readAllFilms();
            filmTableModel.setFilms(films);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
