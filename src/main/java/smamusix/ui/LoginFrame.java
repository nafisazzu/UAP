package smamusix.ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private final String USERNAME = "admin";
    private final String PASSWORD = "12345";

    public LoginFrame() {
        setTitle("Login - SIMS");
        setSize(720, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== WARNA EARTH TONE (PRO) =====
        Color leftBg   = new Color(96, 64, 54);
        Color rightBg  = new Color(250, 248, 245);
        Color textDark = new Color(62, 39, 35);
        Color buttonBg = new Color(141, 110, 99);

        // ===== PANEL KIRI (BRANDING) =====
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(280, 0));
        leftPanel.setBackground(leftBg);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

        JLabel lblApp = new JLabel("SIMS");
        lblApp.setForeground(Color.WHITE);
        lblApp.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblApp.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblDesc = new JLabel("<html>Student Information<br>Management Siswa</html>");
        lblDesc.setForeground(new Color(220, 210, 205));
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblFooter = new JLabel("© 2025 Informatics");
        lblFooter.setForeground(new Color(200, 190, 185));
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(lblApp);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(lblDesc);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(lblFooter);

        // ===== PANEL KANAN (LOGIN) =====
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(rightBg);

        JPanel form = new JPanel();
        form.setBackground(rightBg);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel lblLogin = new JLabel("Welcome Back");
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblLogin.setForeground(textDark);
        lblLogin.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSub = new JLabel("Please login to your account");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(new Color(120, 90, 80));
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(textDark);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtUsername = new JTextField();
        txtUsername.setMaximumSize(new Dimension(260, 35));

        JLabel lblPass = new JLabel("Password");
        lblPass.setForeground(textDark);
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(new Dimension(260, 35));

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(buttonBg);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setOpaque(true);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setMaximumSize(new Dimension(260, 40));
        btnLogin.setAlignmentX(Component.LEFT_ALIGNMENT);

        form.add(lblLogin);
        form.add(Box.createVerticalStrut(5));
        form.add(lblSub);
        form.add(Box.createVerticalStrut(25));
        form.add(lblUser);
        form.add(Box.createVerticalStrut(5));
        form.add(txtUsername);
        form.add(Box.createVerticalStrut(15));
        form.add(lblPass);
        form.add(Box.createVerticalStrut(5));
        form.add(txtPassword);
        form.add(Box.createVerticalStrut(25));
        form.add(btnLogin);

        rightPanel.add(form);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

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
