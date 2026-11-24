# UTS-OOP_22515046_MuhAkramDzakyPutraSubhan
# Sistem Manajemen Bengkel (Console Java)

Project UTS Pemrograman Berorientasi Objek (OOP) - aplikasi console berbasis Java untuk mengelola operasional bengkel kendaraan mini.

## Identitas

| | |
|---|---|
| Nama | Muh Akram Dzaky Putra Subhan |
| NIM | 22515046 |
| Kelas | B |
| Mata Kuliah | Pemrograman Berorientasi Objek |
| Dosen | Muhammad Avied Bachmid, S.Kom., M.Kom. |
| Program Studi | Sistem dan Teknologi Informasi |
| Universitas | Universitas Muhammadiyah Kendari |
| Topik | Bengkel (digit terakhir NIM = 6) |
| Jenis ADT | Queue / FIFO (digit kedua dari belakang = 4, sisa bagi 3 = 1) |

## Deskripsi

Aplikasi mencatat kendaraan pelanggan dan data mekanik, membuat transaksi servis yang masuk ke antrean (Queue), memproses servis secara FIFO, menghitung biaya, dan menampilkan laporan. Data disimpan di memori selama program berjalan (tanpa database, GUI, atau framework).

## Fitur (Menu)

| No | Menu | Keterangan |
|---|---|---|
| 1 | Tambah Kendaraan | Mendaftarkan kendaraan; plat tidak boleh ganda |
| 2 | Tampilkan Semua Kendaraan | Menampilkan seluruh data kendaraan |
| 3 | Tambah Mekanik | Mendaftarkan mekanik; ID tidak boleh ganda |
| 4 | Tampilkan Semua Mekanik | Menampilkan data dan status mekanik |
| 5 | Buat Transaksi Servis | Membuat servis dan memasukkannya ke antrean |
| 6 | Cari Kendaraan | Mencari kendaraan berdasarkan nomor plat |
| 7 | Operasi ADT Queue | Lihat antrean, front, size, isEmpty, isFull |
| 8 | Proses Servis | Mengambil servis terdepan dan menetapkan mekanik |
| 9 | Selesaikan Servis | Menyelesaikan servis dan membebaskan mekanik |
| 10 | Laporan Servis | Rincian servis, jumlah data, dan total pendapatan |
| 0 | Keluar | Menutup program |

## Aturan Bisnis

1. Satu mekanik hanya menangani **satu servis aktif**. Jika semua mekanik sibuk, servis tidak diproses dan muncul pesan kesalahan.
2. Biaya servis = **biaya jasa + biaya suku cadang**.

Aturan tambahan: plat kendaraan dan ID mekanik tidak boleh ganda, biaya tidak boleh negatif, tahun kendaraan 1900 sampai 2100, dan kapasitas antrean maksimal 5 servis.

## ADT Queue

Class `QueueServis` adalah Queue berbasis array dengan kapasitas tetap.

| Operasi | Keterangan |
|---|---|
| `enqueue(servis)` | Menambah servis di belakang antrean; ditolak jika penuh |
| `dequeue()` | Mengeluarkan servis terdepan; `null` jika kosong |
| `front()` | Melihat servis terdepan tanpa menghapus |
| `isEmpty()` | `true` jika antrean kosong |
| `isFull()` | `true` jika antrean penuh |
| `size()` | Jumlah servis dalam antrean |
| `tampilkanAntrean()` | Menampilkan isi antrean |

## Struktur Class

| File | Peran |
|---|---|
| `Kendaraan.java` | Entitas kendaraan pelanggan |
| `Mekanik.java` | Entitas mekanik (status sibuk/tersedia) |
| `Servis.java` | Entitas transaksi servis (menghubungkan Kendaraan dan Mekanik) |
| `Bengkel.java` | Pengelola data dan proses operasional |
| `QueueServis.java` | ADT Queue untuk antrean servis |
| `MainMenu.java` | Menu console dan titik awal program (`main`) |

Class diagram tersedia di folder `docs`:

![Class Diagram](docs/class-diagram.png)


## Pengujian

Skenario uji beserta screenshot hasil program ada di folder `docs` dan di laporan PDF.

## Tautan

- Laporan: lihat file PDF yang dikumpulkan di Google Classroom
