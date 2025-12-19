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

    public StudentFromFrame(Student student) {
        service = new StudentService();

        setTitle(student == null ? "Tambah Siswa" : "Edit Siswa");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtNis = new JTextField();
        txtNama = new JTextField();
        txtKelas = new JTextField();
        cbJurusan = new JComboBox<>(new String[]{"RPL", "TKJ", "MM", "BD"});

        if (student != null) {
            txtNis.setText(student.getNis());
            txtNis.setEnabled(false);
            txtNama.setText(student.getNama());
            txtKelas.setText(student.getKelas());
            cbJurusan.setSelectedItem(student.getJurusan());
        }

        JButton btnSimpan = new JButton("Simpan");
        JButton btnBatal = new JButton("Batal");

        panel.add(new JLabel("NIS"));
        panel.add(txtNis);
        panel.add(new JLabel("Nama"));
        panel.add(txtNama);
        panel.add(new JLabel("Kelas"));
        panel.add(txtKelas);
        panel.add(new JLabel("Jurusan"));
        panel.add(cbJurusan);
        panel.add(btnSimpan);
        panel.add(btnBatal);

        add(panel);

        btnSimpan.addActionListener(e -> simpan(student));
        btnBatal.addActionListener(e -> {
            new StudentListFrame().setVisible(true);
            dispose();
        });
    }

    private void simpan(Student student) {
        try {
            String nis = txtNis.getText();
            String nama = txtNama.getText();
            String kelas = txtKelas.getText();
            String jurusan = cbJurusan.getSelectedItem().toString();

            if (nis.isEmpty() || nama.isEmpty() || kelas.isEmpty()) {
                throw new Exception("Data tidak boleh kosong");
            }

            Integer.parseInt(nis);

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

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            new StudentListFrame().setVisible(true);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "NIS harus angka");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
