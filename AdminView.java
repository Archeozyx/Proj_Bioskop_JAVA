import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class AdminView extends JFrame {
    private Admin admin;
    private DAO dao;
    private JTable tblFilms;
    private FilmTableModel filmTableModel;

    public AdminView(Admin admin) {
        this.admin = admin;
        dao = new DAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Admin Dashboard");
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

        JButton btnAdd = new JButton("Add Film");
        JButton btnEdit = new JButton("Edit Film");
        JButton btnDelete = new JButton("Delete Film");

        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnEdit);
        buttonsPanel.add(btnDelete);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FilmForm(AdminView.this, null).setVisible(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblFilms.getSelectedRow();
                if (selectedRow != -1) {
                    Film film = filmTableModel.getFilmAt(selectedRow);
                    new FilmForm(AdminView.this, film).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a film to edit.");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblFilms.getSelectedRow();
                if (selectedRow != -1) {
                    Film film = filmTableModel.getFilmAt(selectedRow);
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this film?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            dao.deleteFilm(film.getId_film());
                            loadFilms();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a film to delete.");
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
