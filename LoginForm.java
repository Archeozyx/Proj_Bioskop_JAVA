import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private DAO dao;

    public LoginForm() {
        dao = new DAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Login Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblTitle = new JLabel("Welcome to Bioskop App");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(80, 20, 250, 30);
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

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 150, 80, 25);
        panel.add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(250, 150, 80, 25);
        panel.add(btnRegister);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = txtUsername.getText();
                    String password = String.valueOf(txtPassword.getPassword());

                    Admin admin = dao.authenticateAdmin(username, password);
                    if (admin != null) {
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        if (admin.getPrivilege().equalsIgnoreCase("admin")) {
                            new AdminView(admin).setVisible(true);
                        } else {
                            new UserView(admin).setVisible(true);
                        }
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterForm().setVisible(true);
                dispose();
            }
        });
    }
}
