package smamusix.util;

import smamusix.model.Student;
import javax.swing.JOptionPane;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    
    private static final String FILE_NAME = "data/students.csv";

  
    public static List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);

        
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

           
            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                students.add(new Student(
                        data[0],                     
                        data[1],                     
                        data[2],                    
                        data[3],                     
                        LocalDate.parse(data[4])     
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

    
    public static void saveStudents(List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

          
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
