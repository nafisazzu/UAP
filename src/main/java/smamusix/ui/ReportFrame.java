package smamusix.ui;

import smamusix.model.Student;
import smamusix.service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportFrame extends JFrame {

    private StudentService service;

    // ===== COLOR THEME =====
    private final Color CREAM = new Color(245, 236, 220);
    private final Color BROWN = new Color(121, 85, 72);
    private final Color DARK_BROWN = new Color(93, 64, 55);

    public ReportFrame() {
        service = new StudentService();

        setTitle("Laporan Data Siswa");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(CREAM);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== HEADER =====
        JLabel title = new JLabel("📊 Laporan Data Siswa", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(DARK_BROWN);
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        // ===== REPORT AREA (CARD STYLE) =====
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        reportArea.setBackground(Color.WHITE);
        reportArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(BROWN, 2));

        // ===== FOOTER BUTTON =====
        JButton btnBack = new JButton("⬅ Kembali ke Dashboard");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setBackground(BROWN);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setPreferredSize(new Dimension(200, 40));

        JPanel footer = new JPanel();
        footer.setBackground(CREAM);
        footer.add(btnBack);

        // ===== ADD TO MAIN =====
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        // ===== GENERATE REPORT =====
        generateReport(reportArea);

        btnBack.addActionListener(e -> {
            new DashboardFrame().setVisible(true);
            dispose();
        });
    }

    // ===== REPORT LOGIC =====
    private void generateReport(JTextArea area) {
        List<Student> students = service.getAll();

        area.append("========================================\n");
        area.append("        LAPORAN DATA SISWA\n");
        area.append("========================================\n\n");

        area.append("Total Siswa : " + students.size() + "\n\n");

        Map<String, Integer> perKelas = new HashMap<>();
        for (Student s : students) {
            perKelas.put(s.getKelas(),
                    perKelas.getOrDefault(s.getKelas(), 0) + 1);
        }

        area.append("📌 Jumlah Siswa per Kelas:\n");
        for (String kelas : perKelas.keySet()) {
            area.append("  • " + kelas + " : " + perKelas.get(kelas) + " siswa\n");
        }

        area.append("\n----------------------------------------\n");
        area.append("📋 Detail Data Siswa\n");
        area.append("----------------------------------------\n");

        for (Student s : students) {
            area.append(
                    String.format(
                            "%-8s | %-20s | %-5s | %-10s\n",
                            s.getNis(),
                            s.getNama(),
                            s.getKelas(),
                            s.getJurusan()
                    )
            );
        }
    }
}
