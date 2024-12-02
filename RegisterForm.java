import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class RegisterForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chkBeAdmin; // Added checkbox
    private DAO dao;

    public RegisterForm() {
        dao = new DAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Register Form");
        setSize(400, 260); // Increased height to accommodate checkbox
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblTitle = new JLabel("Register New User");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(110, 20, 200, 30);
        panel.add(lblTitle);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 70, 100, 25);
        panel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 70, 180, 25);
        panel.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 110, 100, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 110, 180, 25);
        panel.add(txtPassword);

        // Added checkbox
        chkBeAdmin = new JCheckBox("Be Admin (Debug)");
        chkBeAdmin.setBounds(150, 140, 180, 25);
        panel.add(chkBeAdmin);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(150, 180, 100, 25);
        panel.add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = txtUsername.getText();
                    String password = String.valueOf(txtPassword.getPassword());
                    String privilege = chkBeAdmin.isSelected() ? "admin" : "user"; // Set privilege based on checkbox

                    Admin admin = new Admin(0, username, password, privilege);
                    dao.createAdmin(admin);
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    new LoginForm().setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Registration Failed!");
                }
            }
        });
    }
}
