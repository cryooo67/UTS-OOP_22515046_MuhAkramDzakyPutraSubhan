/*NAMA : MUH AKRAM DZAKY PUTRA SUBHAN 
  NIM : 22515046
  KELAS : B 
  MATKUL : PEMROGRAMAN BERORIENTASI OBJEK */


/*
 * SISTEM MANAJEMEN BENGKEL (versi perbaikan)
 * Topik : Bengkel | ADT : Queue (FIFO)
 * Aturan bisnis:
 *  1. Satu mekanik hanya boleh menangani satu servis aktif.
 *  2. Biaya servis = biaya jasa + biaya suku cadang.
 * Servis baru masuk ke antrean (Queue) dan diproses dari bagian depan.
 */

import java.util.Scanner;

/**
 * Menyediakan antarmuka menu konsol dan titik awal program.
 */
public class MainMenu {
    /** Kapasitas maksimum antrean servis. */
    private static final int KAPASITAS_ANTREAN = 5;

    private static final Scanner SCANNER = new Scanner(System.in);

    // ---------- Helper input: semua input divalidasi agar program tidak crash ----------

    /** Membaca teks yang tidak boleh kosong. */
    private static String bacaTeks(String label) {
        while (true) {
            System.out.print(label);
            String s = SCANNER.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input tidak boleh kosong!");
        }
    }

    /** Membaca bilangan bulat; mengulang bila input bukan angka. */
    private static int bacaInt(String label) {
        while (true) {
            System.out.print(label);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }
    }

    /** Membaca bilangan desimal; mengulang bila input bukan angka. */
    private static double bacaDouble(String label) {
        while (true) {
            System.out.print(label);
            try {
                return Double.parseDouble(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public static void main(String[] args) {
        Bengkel bengkel = new Bengkel("Bengkel Gacor", "Jl. Raya No. 123", "08123456789", KAPASITAS_ANTREAN);
        QueueServis antrean = new QueueServis(bengkel.getKapasitasAntrian());

        int pilihan;
        do {
            System.out.println("\n================================");
            System.out.println("     SISTEM MANAJEMEN BENGKEL");
            System.out.println("================================");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Semua Kendaraan");
            System.out.println("3. Tambah Mekanik");
            System.out.println("4. Tampilkan Semua Mekanik");
            System.out.println("5. Buat Transaksi Servis");
            System.out.println("6. Cari Kendaraan");
            System.out.println("7. Operasi ADT Queue");
            System.out.println("8. Proses Servis");
            System.out.println("9. Selesaikan Servis");
            System.out.println("10. Laporan Servis");
            System.out.println("0. Keluar");
            System.out.println("================================");
            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    System.out.println("\n=== TAMBAH KENDARAAN ===");
                    String plat = bacaTeks("Nomor Plat: ");
                    String merk = bacaTeks("Merk: ");
                    String tipe = bacaTeks("Tipe: ");
                    int tahun = bacaInt("Tahun: ");
                    String pemilik = bacaTeks("Nama Pemilik: ");
                    try {
                        // Validasi tahun dilakukan di setter melalui konstruktor.
                        bengkel.tambahKendaraan(new Kendaraan(plat, merk, tipe, tahun, pemilik));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Gagal: " + e.getMessage());
                    }
                    break;

                case 2:
                    bengkel.tampilkanSemuaKendaraan();
                    break;

                case 3:
                    System.out.println("\n=== TAMBAH MEKANIK ===");
                    String id = bacaTeks("ID Mekanik: ");
                    String nama = bacaTeks("Nama Mekanik: ");
                    String spesialisasi = bacaTeks("Spesialisasi: ");
                    int pengalaman = bacaInt("Pengalaman (tahun): ");
                    try {
                        bengkel.tambahMekanik(new Mekanik(id, nama, spesialisasi, pengalaman));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Gagal: " + e.getMessage());
                    }
                    break;

                case 4:
                    bengkel.tampilkanSemuaMekanik();
                    break;

                case 5:
                    System.out.println("\n=== TRANSAKSI SERVIS ===");
                    String idServis = bacaTeks("ID Servis: ");
                    String noPlat = bacaTeks("Nomor Plat Kendaraan: ");
                    Kendaraan k = bengkel.cariKendaraanByPlat(noPlat);
                    if (k == null) {
                        System.out.println("Kendaraan tidak ditemukan!");
                        break;
                    }
                    double jasa = bacaDouble("Biaya Jasa: ");
                    double suku = bacaDouble("Biaya Suku Cadang: ");
                    try {
                        antrean.enqueue(new Servis(idServis, k, jasa, suku));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Gagal: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("\n=== CARI KENDARAAN ===");
                    bengkel.cariKendaraan(bacaTeks("Masukkan Nomor Plat: "));
                    break;

                case 7:
                    menuQueue(antrean);
                    break;

                case 8:
                    bengkel.prosesServis(antrean);
                    break;

                case 9:
                    bengkel.selesaikanServis(bacaTeks("Masukkan ID Servis: "));
                    break;

                case 10:
                    bengkel.laporan(antrean);
                    break;

                case 0:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);

        SCANNER.close();
    }

    /** Submenu untuk mencoba operasi ADT Queue. */
    private static void menuQueue(QueueServis antrean) {
        int m;
        do {
            System.out.println("\n=== OPERASI ADT QUEUE ===");
            System.out.println("1. Lihat Antrean");
            System.out.println("2. Lihat Antrean Terdepan (front)");
            System.out.println("3. Jumlah Antrean (size)");
            System.out.println("4. Cek Antrean Kosong (isEmpty)");
            System.out.println("5. Cek Antrean Penuh (isFull)");
            System.out.println("6. Kembali");
            m = bacaInt("Pilih: ");
            switch (m) {
                case 1: antrean.tampilkanAntrean(); break;
                case 2:
                    Servis depan = antrean.front();
                    if (depan == null) {
                        System.out.println("Antrean kosong!");
                    } else {
                        System.out.println("Servis terdepan: " + depan.getIdServis());
                        System.out.println("Kendaraan: " + depan.getKendaraan().getMerk());
                    }
                    break;
                case 3: System.out.println("Jumlah antrean: " + antrean.size()); break;
                case 4: System.out.println(antrean.isEmpty() ? "Antrean masih kosong." : "Antrean tidak kosong."); break;
                case 5: System.out.println(antrean.isFull() ? "Antrean sudah penuh." : "Antrean belum penuh."); break;
                case 6: System.out.println("Kembali ke menu utama."); break;
                default: System.out.println("Pilihan tidak valid!");
            }
        } while (m != 6);
    }
}
