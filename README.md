# 🎓 Student Management System (SIMS)

## 📌 Deskripsi Aplikasi

Student Management System (SIMS) adalah aplikasi desktop berbasis **Java Swing** yang digunakan untuk mengelola data siswa.  
Aplikasi ini dibuat untuk memenuhi **Ujian Akhir Praktikum (UAP) Mata Kuliah Pemrograman Lanjut** dan telah mengimplementasikan seluruh ketentuan pada modul 1–6.

Aplikasi mendukung pengelolaan data siswa secara lengkap (**CRUD**), dilengkapi dengan **login**, **navigasi dashboard**, serta **penyimpanan data permanen** menggunakan file **CSV**.

---

## 🛠 Teknologi yang Digunakan

- Java JDK 8+
- Java Swing (GUI)
- IntelliJ IDEA
- File Handling (CSV)
- Git & GitHub

## 📂 Struktur Project

```
StudentManagementApp/
│
├── src/
│   ├── model/        # Class Student
│   ├── service/      # Logic & CRUD
│   ├── util/         # File Handling CSV
│   ├── ui/           # GUI (Dashboard, Form, List, Report)
│   └── Main.java
│
├── data/
│   └── students.csv
│
├── README.md
└── .gitignore
```

---

---

## 🧭 Fitur Aplikasi

### 1️⃣ Login
- Autentikasi menggunakan **username dan password**
- Validasi sebelum masuk ke sistem

### 2️⃣ Dashboard
- Menu utama aplikasi
- Navigasi ke seluruh fitur aplikasi

### 3️⃣ Manajemen Data Siswa (CRUD)

Aplikasi telah menerapkan **CRUD lengkap**, yaitu:

- **Create** → Menambah data siswa
- **Read** → Menampilkan data siswa dalam JTable
- **Update** → Mengedit data siswa
- **Delete** → Menghapus data siswa

Fitur tambahan:
- Pencarian data siswa berdasarkan **nama**
- Sorting data siswa berdasarkan **nama**
- Tombol **Kembali ke Dashboard**

### 4️⃣ Form Tambah / Edit Siswa
- Input data: NIS, Nama, Kelas, Jurusan
- Validasi input
- Exception Handling (data kosong & format salah)

### 5️⃣ Laporan Data
- Menampilkan total jumlah siswa
- Rekap data siswa
- Menampilkan data dari file CSV

---

## 💾 Penyimpanan Data

- Data siswa disimpan menggunakan file **CSV**
- Lokasi file: data/students.csv
- Data bersifat **permanen** dan tidak hilang saat aplikasi ditutup
- Setiap operasi CRUD langsung tersimpan ke file

----

## ⚠️ Exception Handling & Validasi

- Aplikasi menerapkan exception handling untuk mencegah error dan crash program, antara lain:

- Validasi input kosong

- Validasi input angka (NumberFormatException)

- Penanganan error file (IOException) saat membaca/menulis CSV

- Error ditampilkan menggunakan JOptionPane

- Exception ditangani menggunakan mekanisme try-catch.

----
## ▶️ Cara Menjalankan Aplikasi

1. Buka project menggunakan IntelliJ IDEA
2. Pastikan folder `data/` dan file `students.csv` tersedia
3. Jalankan file `Main.java`
4. Login dan gunakan aplikasi

---

## 🧪 Laporan Testing Manual

### Metode Pengujian
Pengujian dilakukan secara **manual tanpa framework**, sesuai ketentuan modul, dengan menjalankan aplikasi dan mencoba seluruh fitur.

### Tabel Hasil Pengujian

| No | Fitur          | Skenario Uji            | Hasil                   | Status |
|----|----------------|-------------------------|-------------------------|--------|
| 1  | Login          | Username & password valid | Masuk ke dashboard      | ✅ |
| 2  | Tambah Siswa   | Input data valid        | Data tersimpan & tampil | ✅ |
| 3  | Tambah Siswa   | NIS bukan angka         | Muncul pesan error      | ✅ |
| 4  | Tambah Siswa   | Field kosong            | Validasi berjalan       | ✅ |
| 5  | Edit Data      | Ubah data siswa         | Data berubah            | ✅ |
| 6  | Tampilkan Data | Aplikasi dibuka ulang   | Data tetap ada          | ✅ |
| 7  | Search         | Cari nama siswa         | Data terfilter          | ✅ |
| 8  | Sorting        | Urutkan nama            | Data terurut            | ✅ |
| 9  | Hapus Data     | Pilih & hapus siswa     | Data terhapus           | ✅ |
| 10 | Laporan        | Buka halaman laporan    | Data sesuai CSV         | ✅ |

---

## 🔍 Code Review

### Temuan
- Struktur package rapi dan konsisten
- Penamaan class dan variabel jelas
- Menggunakan konsep OOP dengan baik

### Perbaikan yang Dilakukan
- Penambahan validasi input
- Penambahan exception handling
- Sinkronisasi path file CSV

---

## ✅ Kesimpulan

Aplikasi **Student Management System (SIMS)** telah memenuhi seluruh ketentuan **UAP Pemrograman Lanjut**, meliputi:

- GUI berbasis Java Swing
- CRUD lengkap
- Login & navigasi
- File Handling CSV
- Exception Handling
- Laporan & testing manual

