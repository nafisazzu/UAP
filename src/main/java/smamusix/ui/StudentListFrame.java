package smamusix.ui;

import smamusix.model.Student;
import smamusix.service.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== ROOT =====
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(248, 244, 239)); // cream

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(121, 85, 72)); // coklat
        header.setPreferredSize(new Dimension(0, 70));

        JLabel title = new JLabel("Data Siswa", SwingConstants.CENTER);
        title.setForeground(new Color(255, 248, 225));
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title, BorderLayout.CENTER);

        // ===== SEARCH PANEL =====
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        searchPanel.setBackground(new Color(248, 244, 239));

        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton btnSearch = createButton("🔍 Cari Nama");
        JButton btnSort = createButton("⇅ Sort Nama");

        searchPanel.add(btnSearch, BorderLayout.WEST);
        searchPanel.add(txtSearch, BorderLayout.CENTER);
        searchPanel.add(btnSort, BorderLayout.EAST);

        // ===== TABLE =====
        String[] columns = {"NIS", "Nama", "Kelas", "Jurusan", "Tanggal Daftar"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        refreshTable(service.getAll());

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setSelectionBackground(new Color(215, 204, 200));
        table.setSelectionForeground(Color.BLACK);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBackground(new Color(141, 110, 99));
        tableHeader.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // ===== BOTTOM BUTTON PANEL =====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        bottomPanel.setBackground(new Color(248, 244, 239));

        JButton btnTambah = createButton("➕ Tambah");
        JButton btnEdit = createButton("✏ Edit");
        JButton btnHapus = createButton("🗑 Hapus");
        JButton btnKembali = createButton("⬅ Kembali");

        bottomPanel.add(btnTambah);
        bottomPanel.add(btnEdit);
        bottomPanel.add(btnHapus);
        bottomPanel.add(btnKembali);

        // ===== ADD =====
        root.add(header, BorderLayout.NORTH);
        root.add(searchPanel, BorderLayout.BEFORE_FIRST_LINE);
        root.add(scrollPane, BorderLayout.CENTER);
        root.add(bottomPanel, BorderLayout.SOUTH);

        add(root);

        // ===== ACTION =====
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

    // ===== BUTTON STYLE =====
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(255, 253, 248));
        btn.setForeground(new Color(93, 64, 55));
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160)));
        return btn;
    }

    // ===== METHODS =====
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
