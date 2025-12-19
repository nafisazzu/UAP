package smamusix.ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    // akun default (sederhana & aman untuk UAP)
    private final String USERNAME = "admin";
    private final String PASSWORD = "12345";

    public LoginFrame() {
        setTitle("Login - SIMS");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== WARNA CUSTOM =====
        Color bgColor = new Color(33, 150, 243); // biru
        Color panelColor = Color.WHITE;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bgColor);

        JLabel lblTitle = new JLabel("STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(panelColor);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(33, 150, 243));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        formPanel.add(new JLabel("Username:"));
        formPanel.add(txtUsername);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(txtPassword);
        formPanel.add(new JLabel(""));
        formPanel.add(btnLogin);

        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());

        if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
            JOptionPane.showMessageDialog(this, "Login berhasil");
            new DashboardFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah");
        }
    }
}
