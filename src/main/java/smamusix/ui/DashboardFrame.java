package smamusix.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        setTitle("SIMS - Student Management System");
        setSize(750, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== ROOT PANEL =====
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(248, 244, 239)); // cream

        // ===== HEADER (GRADIENT EFFECT) =====
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(141, 110, 99),
                        0, getHeight(), new Color(109, 76, 65)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(0, 95));
        header.setLayout(new BorderLayout());

        JLabel title = new JLabel("Student Management System");
        title.setForeground(new Color(255, 248, 225));
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 25, 5, 10));

        JLabel subtitle = new JLabel("Dashboard Administrator");
        subtitle.setForeground(new Color(239, 235, 233));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setBorder(BorderFactory.createEmptyBorder(0, 27, 15, 10));

        JPanel titleBox = new JPanel(new GridLayout(2, 1));
        titleBox.setOpaque(false);
        titleBox.add(title);
        titleBox.add(subtitle);

        header.add(titleBox, BorderLayout.WEST);

        // ===== CARD MENU =====
        JPanel cardPanel = new JPanel(new GridLayout(2, 2, 25, 25));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        cardPanel.setBackground(new Color(248, 244, 239));

        cardPanel.add(createCard("📋", "Data Siswa", "Kelola & lihat data siswa", () -> {
            new StudentListFrame().setVisible(true);
            dispose();
        }));

        cardPanel.add(createCard("➕", "Tambah Siswa", "Input data siswa baru", () -> {
            new StudentFromFrame(null).setVisible(true);
            dispose();
        }));

        cardPanel.add(createCard("📊", "Laporan", "Ringkasan & statistik siswa", () -> {
            new ReportFrame().setVisible(true);
            dispose();
        }));

        cardPanel.add(createCard("🚪", "Keluar", "Tutup aplikasi", () -> {
            System.exit(0);
        }));

        // ===== ADD =====
        root.add(header, BorderLayout.NORTH);
        root.add(cardPanel, BorderLayout.CENTER);
        add(root);
    }

    // ===== CARD COMPONENT =====
    private JPanel createCard(String icon, String title, String desc, Runnable action) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 253, 248));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 200, 190)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        lblIcon.setForeground(new Color(121, 85, 72));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(93, 64, 55));

        JLabel lblDesc = new JLabel(desc);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(141, 110, 99));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(lblTitle);
        textPanel.add(lblDesc);

        card.add(lblIcon, BorderLayout.WEST);
        card.add(textPanel, BorderLayout.CENTER);

        // hover effect
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(243, 234, 224));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(255, 253, 248));
            }

            public void mouseClicked(java.awt.event.MouseEvent e) {
                action.run();
            }
        });

        return card;
    }
}
