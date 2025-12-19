package smamusix.model;

import java.time.LocalDate;

public class Student {
    private String nis;
    private String nama;
    private String kelas;
    private String jurusan;
    private LocalDate tanggalDaftar;

    public Student(String nis, String nama, String kelas, String jurusan, LocalDate tanggalDaftar) {
        this.nis = nis;
        this.nama = nama;
        this.kelas = kelas;
        this.jurusan = jurusan;
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getNis() { return nis; }
    public String getNama() { return nama; }
    public String getKelas() { return kelas; }
    public String getJurusan() { return jurusan; }
    public LocalDate getTanggalDaftar() { return tanggalDaftar; }
}
