package smamusix.util;

import smamusix.model.Student;
import javax.swing.JOptionPane;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    // SAMAKAN DENGAN FILE DI PROJECT
    private static final String FILE_NAME = "data/students.csv";

    // ===== READ (saat aplikasi dibuka) =====
    public static List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);

        // Jika file belum ada, kembalikan list kosong
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            // skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                students.add(new Student(
                        data[0],                     // NIS
                        data[1],                     // Nama
                        data[2],                     // Kelas
                        data[3],                     // Jurusan
                        LocalDate.parse(data[4])     // Tanggal Daftar
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal membaca file students.csv",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return students;
    }

    // ===== WRITE (setiap CRUD) =====
    public static void saveStudents(List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            // header
            pw.println("NIS,Nama,Kelas,Jurusan,TanggalDaftar");

            for (Student s : students) {
                pw.println(
                        s.getNis() + "," +
                                s.getNama() + "," +
                                s.getKelas() + "," +
                                s.getJurusan() + "," +
                                s.getTanggalDaftar()
                );
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal menyimpan data ke students.csv",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
