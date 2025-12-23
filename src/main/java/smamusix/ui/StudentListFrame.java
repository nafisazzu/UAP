package smamusix.ui;

import smamusix.model.Student;
import smamusix.service.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentListFrame extends JFrame {

    private StudentService service;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;

    public StudentListFrame() {
        service = new StudentService();

        setTitle("Data Siswa");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       
        String[] columns = {"NIS", "Nama", "Kelas", "Jurusan", "Tanggal Daftar"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        refreshTable(service.getAll());

        JScrollPane scrollPane = new JScrollPane(table);

        
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtSearch = new JTextField();
        JButton btnSearch = new JButton("Cari Nama");
        JButton btnSort = new JButton("Sort Nama");

        topPanel.add(btnSearch, BorderLayout.WEST);
        topPanel.add(txtSearch, BorderLayout.CENTER);
        topPanel.add(btnSort, BorderLayout.EAST);

        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnTambah = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        JButton btnKembali = new JButton("⬅ Kembali");

        bottomPanel.add(btnTambah);
        bottomPanel.add(btnEdit);
        bottomPanel.add(btnHapus);
        bottomPanel.add(btnKembali);

        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

      

       
        btnTambah.addActionListener(e -> {
            new StudentFromFrame(null).setVisible(true);
            dispose();
        });

        
        btnEdit.addActionListener(e -> edit());

        
        btnHapus.addActionListener(e -> delete());

        
        btnSearch.addActionListener(e -> search());

        
        btnSort.addActionListener(e -> {
            service.sortByName();
            refreshTable(service.getAll());
        });

        
        btnKembali.addActionListener(e -> {
            new DashboardFrame().setVisible(true);
            dispose();
        });
    }

    
    private void refreshTable(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                    s.getNis(),
                    s.getNama(),
                    s.getKelas(),
                    s.getJurusan(),
                    s.getTanggalDaftar()
            });
        }
    }

    private void search() {
        String keyword = txtSearch.getText().toLowerCase();
        List<Student> filtered = service.getAll().stream()
                .filter(s -> s.getNama().toLowerCase().contains(keyword))
                .toList();
        refreshTable(filtered);
    }

    private void edit() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu");
            return;
        }

        Student student = service.getAll().get(row);
        new StudentFromFrame(student).setVisible(true);
        dispose();
    }

    private void delete() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu");
            return;
        }

        String nis = table.getValueAt(row, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            service.delete(nis);
            refreshTable(service.getAll());
        }
    }
}
