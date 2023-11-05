import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

public class FileHider extends JFrame {

    private JPanel loginPanel;
    private JTextField usernameField;

    private JPasswordField otpfield;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton otpverify;

    private JPanel filePanel;
    private JTextField filePathField;
    private JButton hideButton;

    private Connection connection;

    public FileHider() {
        super("File Hider");

        // Connect to database in a new thread
        new Thread(() -> {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filehide?useSSL=false", "root", "root");
                System.out.println("Connection Established");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection Failed");
            }
        }).start();

        // Login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(7, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        otpfield=new JPasswordField();
        otpverify=new JButton();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("OTP:"));
        loginPanel.add(otpfield);
        loginPanel.add(otpverify);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        // File panel
        filePanel = new JPanel();
        filePanel.setLayout(new GridLayout(2, 1));

        filePathField = new JTextField();
        hideButton = new JButton("Hide");

        filePanel.add(new JLabel("File path:"));
        filePanel.add(filePathField);
        filePanel.add(hideButton);

        // Add panels to frame
        add(loginPanel, BorderLayout.NORTH);
        add(filePanel, BorderLayout.CENTER);

        // Set frame properties
        setSize(400, 300);
        setVisible(true);

        // Add action listeners
        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());
        hideButton.addActionListener(e -> hideFile());
    }

    private void login() {
        // TODO: Implement login logic
        // e.g. Check if the user exists in the database
    }

    private void register() {
        // TODO: Implement register logic
        // e.g. Create a new user account in the database
    }

    private void hideFile() {
        // Fork a new thread to hide the file
        new Thread(() -> {
            // TODO: Implement hide file logic
            // e.g. Encrypt the file and store it in the database
        }).start();
    }

    @Override
    public void dispose() {
        // Close the SQL connection in a new thread
        new Thread(() -> {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        super.dispose();
    }

    public static void main(String[] args) {
        new FileHider();
    }
}