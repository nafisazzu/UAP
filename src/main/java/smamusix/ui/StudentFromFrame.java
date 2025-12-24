package smamusix.ui;

import smamusix.model.Student;
import smamusix.service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class StudentFromFrame extends JFrame {

    private JTextField txtNis, txtNama, txtKelas;
    private JComboBox<String> cbJurusan;
    private StudentService service;

    // ===== COLOR THEME =====
    private final Color CREAM = new Color(245, 236, 220);
    private final Color BROWN = new Color(121, 85, 72);
    private final Color DARK_BROWN = new Color(93, 64, 55);

    public StudentFromFrame(Student student) {
        service = new StudentService();

        setTitle(student == null ? "Tambah Siswa" : "Edit Siswa");
        setSize(450, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(CREAM);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== HEADER =====
        JLabel title = new JLabel(
                student == null ? "➕ Form Tambah Siswa" : "✏️ Form Edit Siswa",
                SwingConstants.CENTER
        );
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(DARK_BROWN);
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        // ===== FORM CARD =====
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BROWN, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        txtNis = new JTextField();
        txtNama = new JTextField();
        txtKelas = new JTextField();
        cbJurusan = new JComboBox<>(new String[]{"RPL", "TKJ", "MM", "BD"});

        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        txtNis.setFont(fieldFont);
        txtNama.setFont(fieldFont);
        txtKelas.setFont(fieldFont);
        cbJurusan.setFont(fieldFont);

        if (student != null) {
            txtNis.setText(student.getNis());
            txtNis.setEnabled(false);
            txtNama.setText(student.getNama());
            txtKelas.setText(student.getKelas());
            cbJurusan.setSelectedItem(student.getJurusan());
        }

        formPanel.add(createLabel("NIS"));
        formPanel.add(txtNis);
        formPanel.add(createLabel("Nama"));
        formPanel.add(txtNama);
        formPanel.add(createLabel("Kelas"));
        formPanel.add(txtKelas);
        formPanel.add(createLabel("Jurusan"));
        formPanel.add(cbJurusan);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(CREAM);

        JButton btnSimpan = new JButton("💾 Simpan");
        JButton btnBatal = new JButton("✖ Batal");

        styleButton(btnSimpan);
        styleButton(btnBatal);

        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnBatal);

        // ===== ADD TO MAIN =====
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // ===== ACTION =====
        btnSimpan.addActionListener(e -> simpan(student));
        btnBatal.addActionListener(e -> {
            new StudentListFrame().setVisible(true);
            dispose();
        });
    }

    // ===== SAVE LOGIC =====
    private void simpan(Student student) {
        try {
            String nis = txtNis.getText().trim();
            String nama = txtNama.getText().trim();
            String kelas = txtKelas.getText().trim();
            String jurusan = cbJurusan.getSelectedItem().toString();

            if (nis.isEmpty() || nama.isEmpty() || kelas.isEmpty()) {
                throw new Exception("Semua data wajib diisi");
            }

            Integer.parseInt(nis); // validasi angka

            if (student == null) {
                service.add(new Student(nis, nama, kelas, jurusan, LocalDate.now()));
            } else {
                service.update(new Student(
                        student.getNis(),
                        nama,
                        kelas,
                        jurusan,
                        student.getTanggalDaftar()
                ));
            }

            JOptionPane.showMessageDialog(this, "✅ Data berhasil disimpan");
            new StudentListFrame().setVisible(true);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ NIS harus berupa angka");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ " + e.getMessage());
        }
    }

    // ===== HELPER =====
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(DARK_BROWN);
        return label;
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(BROWN);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(130, 40));
    }
}
