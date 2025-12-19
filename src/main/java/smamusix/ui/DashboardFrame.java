package smamusix.ui;


import javax.swing.*;
import java.awt.*;


public class DashboardFrame extends JFrame {


    public DashboardFrame() {
        setTitle("SIMS - Student Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));


        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));


        JPanel menuPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        menuPanel.setBackground(new Color(245, 247, 250));


        JButton btnData = new JButton("Data Siswa");
        JButton btnTambah = new JButton("Tambah Siswa");
        JButton btnLaporan = new JButton("Laporan");
        JButton btnKeluar = new JButton("Keluar");


        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        btnData.setFont(btnFont);
        btnTambah.setFont(btnFont);
        btnLaporan.setFont(btnFont);
        btnKeluar.setFont(btnFont);


        menuPanel.add(btnData);
        menuPanel.add(btnTambah);
        menuPanel.add(btnLaporan);
        menuPanel.add(btnKeluar);


        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);


        add(mainPanel);


// Action
        btnData.addActionListener(e -> {
            new StudentListFrame().setVisible(true);
            dispose();
        });


        btnTambah.addActionListener(e -> {
            new StudentFromFrame(null).setVisible(true);
            dispose();
        });


        btnLaporan.addActionListener(e -> {
            new ReportFrame().setVisible(true);
            dispose();
        });


        btnKeluar.addActionListener(e -> System.exit(0));
    }
}