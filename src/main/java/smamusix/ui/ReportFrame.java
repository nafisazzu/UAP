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


    public ReportFrame() {
        service = new StudentService();


        setTitle("Laporan Data Siswa");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));


        JScrollPane scrollPane = new JScrollPane(reportArea);


        JButton btnBack = new JButton("Kembali ke Dashboard");


        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnBack, BorderLayout.SOUTH);


        add(panel);


        generateReport(reportArea);


        btnBack.addActionListener(e -> {
            new DashboardFrame().setVisible(true);
            dispose();
        });
    }


    private void generateReport(JTextArea area) {
        List<Student> students = service.getAll();
        area.append("=== LAPORAN DATA SISWA ===\n\n");
        area.append("Total Siswa : " + students.size() + "\n\n");


        Map<String, Integer> perKelas = new HashMap<>();
        for (Student s : students) {
            perKelas.put(s.getKelas(), perKelas.getOrDefault(s.getKelas(), 0) + 1);
        }


        area.append("Jumlah Siswa per Kelas:\n");
        for (String kelas : perKelas.keySet()) {
            area.append("- " + kelas + " : " + perKelas.get(kelas) + " siswa\n");
        }


        area.append("\n--- Detail Siswa ---\n");
        for (Student s : students) {
            area.append(s.getNis() + " | " + s.getNama() + " | " + s.getKelas() + " | " + s.getJurusan() + "\n");
        }
    }
}