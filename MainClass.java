public class MainClass {

    public static void main(String[] args) {
        // Launch the GUI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
