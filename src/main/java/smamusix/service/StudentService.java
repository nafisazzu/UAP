package smamusix.service;

import smamusix.model.Student;
import smamusix.util.FileUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentService {

    private static final List<Student> students = new ArrayList<>();

    static {
        students.addAll(FileUtil.readStudents());
    }

    public void add(Student student) {
        students.add(student);
        FileUtil.saveStudents(students);
    }

    public List<Student> getAll() {
        return students;
    }

    public void update(Student updated) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getNis().equals(updated.getNis())) {
                students.set(i, updated);
                break;
            }
        }
        FileUtil.saveStudents(students);
    }

    public void delete(String nis) {
        students.removeIf(s -> s.getNis().equals(nis));
        FileUtil.saveStudents(students);
    }

    public void sortByName() {
        students.sort(Comparator.comparing(Student::getNama));
        FileUtil.saveStudents(students);
    }
}
