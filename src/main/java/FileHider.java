package service;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import service.SendOTPService;
import service.GenerateOTP;
import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

public class FileHider extends JFrame {

    private JPanel loginPanel;
    private JTextField useremailField;

    private JPasswordField otpfield;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton otpverify;
    private JButton getotp;

    private JPanel filePanel;
    private JTextField filePathField;
    private JButton hideButton;

    private Connection connection;
    private String user;
    private String genOTP1;
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

        useremailField = new JTextField();
        passwordField = new JPasswordField();
        otpfield=new JPasswordField();
        otpverify=new JButton();
        getotp=new JButton();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        otpverify=new JButton("Verify");
        getotp=new JButton("Get OTP");
        loginPanel.add(new JLabel("Useremail:"));
        loginPanel.add(useremailField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("OTP:"));
        loginPanel.add(otpfield);
        loginPanel.add(otpverify);
        loginPanel.add(getotp);
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
        getotp.addActionListener(e -> otp());
        otpverify.addActionListener(e -> verifyotp());
    }

    private void login() {
        // TODO: Implement login logic
        // e.g. Check if the user exists in the database
    }

    private void register() {
        String useremail = useremailField.getText();
        System.out.println(useremail);
        String password=passwordField.getText();

    }
    private void otp() {
        GenerateOTP gen = new GenerateOTP();
        genOTP1 = GenerateOTP.getOTP();
        System.out.println(genOTP1);
        String email = useremailField.getText();
        SendOTPService sen = new SendOTPService();
        SendOTPService.sendOTP(email, genOTP1);
    }
    private void verifyotp(){
        String userEnteredOTP = otpfield.getText();
        if (genOTP1.equals(userEnteredOTP)) {
            System.out.println("OTP Valid");
        } else {
            System.out.println("OTP Invalid");
        }

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

        FileHider in = new FileHider();

    }
}
