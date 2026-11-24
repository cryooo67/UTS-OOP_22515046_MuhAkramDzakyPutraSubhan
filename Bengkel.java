import java.util.ArrayList;

/**
 * Mengelola kendaraan, mekanik, dan riwayat servis serta proses operasional bengkel.
 */
public class Bengkel {
    private String namaBengkel;
    private String alamat;
    private String noTelp;
    private int kapasitasAntrian;

    private ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
    private ArrayList<Mekanik> daftarMekanik = new ArrayList<>();
    private ArrayList<Servis> riwayatServis = new ArrayList<>();

    /** Konstruktor default. */
    public Bengkel() {
        this("Bengkel", "-", "-", 5);
    }

    /** Konstruktor berparameter. */
    public Bengkel(String namaBengkel, String alamat, String noTelp, int kapasitasAntrian) {
        setNamaBengkel(namaBengkel);
        this.alamat = alamat;
        this.noTelp = noTelp;
        setKapasitasAntrian(kapasitasAntrian);
    }

    public String getNamaBengkel() { return namaBengkel; }
    public String getAlamat() { return alamat; }
    public String getNoTelp() { return noTelp; }
    public int getKapasitasAntrian() { return kapasitasAntrian; }

    public void setNamaBengkel(String namaBengkel) {
        if (namaBengkel == null || namaBengkel.trim().isEmpty())
            throw new IllegalArgumentException("Nama bengkel tidak boleh kosong.");
        this.namaBengkel = namaBengkel.trim();
    }

    public void setKapasitasAntrian(int kapasitasAntrian) {
        if (kapasitasAntrian <= 0)
            throw new IllegalArgumentException("Kapasitas antrean harus lebih dari 0.");
        this.kapasitasAntrian = kapasitasAntrian;
    }

    /** Mencari kendaraan berdasarkan plat; null jika tidak ada. */
    public Kendaraan cariKendaraanByPlat(String plat) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.cocokPlat(plat)) return k;
        }
        return null;
    }

    /** Mendaftarkan kendaraan; plat yang sama tidak boleh didaftarkan dua kali. */
    public void tambahKendaraan(Kendaraan k) {
        if (cariKendaraanByPlat(k.getNoPlat()) != null) {
            System.out.println("Plat " + k.getNoPlat() + " sudah terdaftar!");
            return;
        }
        daftarKendaraan.add(k);
        System.out.println("Kendaraan berhasil ditambahkan");
    }

    /** Mendaftarkan mekanik; ID yang sama tidak boleh dua kali. */
    public void tambahMekanik(Mekanik m) {
        for (Mekanik x : daftarMekanik) {
            if (x.getIdMekanik().equalsIgnoreCase(m.getIdMekanik())) {
                System.out.println("ID mekanik " + m.getIdMekanik() + " sudah terdaftar!");
                return;
            }
        }
        daftarMekanik.add(m);
        System.out.println("Mekanik berhasil ditambahkan");
    }

    public void tampilkanSemuaKendaraan() {
        System.out.println("\n=== DAFTAR KENDARAAN ===");
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada kendaraan.");
            return;
        }
        for (Kendaraan k : daftarKendaraan) {
            k.tampilkanInfo();
            System.out.println("------------------------");
        }
    }

    public void tampilkanSemuaMekanik() {
        System.out.println("\n=== DAFTAR MEKANIK ===");
        if (daftarMekanik.isEmpty()) {
            System.out.println("Belum ada mekanik.");
            return;
        }
        for (Mekanik m : daftarMekanik) {
            m.tampilkanInfo();
            System.out.println("------------------------");
        }
    }

    /** Mencari mekanik pertama yang tersedia. */
    public Mekanik cariMekanikTersedia() {
        for (Mekanik m : daftarMekanik) {
            if (m.isTersedia()) return m;
        }
        return null;
    }

    /**
     * Memproses servis terdepan: pastikan antrean tidak kosong dan ada mekanik tersedia,
     * lalu dequeue (FIFO), tetapkan mekanik, ubah status, dan catat ke riwayat.
     */
    public void prosesServis(QueueServis antrean) {
        if (antrean.isEmpty()) {
            System.out.println("Tidak ada servis yang menunggu");
            return;
        }
        Mekanik m = cariMekanikTersedia();
        if (m == null) {
            System.out.println("Tidak ada mekanik yang tersedia");
            return;
        }
        Servis s = antrean.dequeue();
        if (s != null) {
            s.setMekanik(m);
            s.ubahStatus(Servis.STATUS_DIPROSES);
            m.mulaiServis();
            riwayatServis.add(s);
            System.out.println("Servis " + s.getIdServis() + " (" + s.getKendaraan().getMerk() + ", "
                    + s.getKendaraan().getNoPlat() + ") sedang diproses oleh mekanik " + m.getNama());
        }
    }

    /** Menyelesaikan servis berdasarkan ID dan membebaskan mekanik. */
    public void selesaikanServis(String id) {
        for (Servis s : riwayatServis) {
            if (s.getIdServis().equalsIgnoreCase(id) && Servis.STATUS_DIPROSES.equals(s.getStatus())) {
                s.ubahStatus(Servis.STATUS_SELESAI);
                if (s.getMekanik() != null) s.getMekanik().selesaiServis();
                System.out.println("Servis " + s.getIdServis() + " telah selesai");
                System.out.println("Total biaya: Rp" + s.hitungTotalBiaya());
                return;
            }
        }
        System.out.println("Servis dengan ID " + id + " tidak ditemukan atau belum diproses");
    }

    /** Mencari dan menampilkan kendaraan berdasarkan plat. */
    public void cariKendaraan(String plat) {
        Kendaraan k = cariKendaraanByPlat(plat);
        if (k == null) {
            System.out.println("Kendaraan dengan plat " + plat + " tidak ditemukan");
        } else {
            k.tampilkanInfo();
        }
    }

    /** Laporan: rincian servis dan ringkasan jumlah data serta total biaya. */
    public void laporan(QueueServis antrean) {
        System.out.println("\n=== LAPORAN BENGKEL: " + namaBengkel + " ===");
        System.out.println("No. Telepon: " + noTelp);
        if (riwayatServis.isEmpty()) {
            System.out.println("Belum ada servis yang diproses");
        }
        double totalSelesai = 0;
        int selesai = 0;
        for (Servis s : riwayatServis) {
            s.tampilkanDetail();
            System.out.println("------------------------");
            if (Servis.STATUS_SELESAI.equals(s.getStatus())) {
                totalSelesai += s.hitungTotalBiaya();
                selesai++;
            }
        }
        System.out.println("--- RINGKASAN ---");
        System.out.println("Jumlah kendaraan terdaftar : " + daftarKendaraan.size());
        System.out.println("Jumlah mekanik             : " + daftarMekanik.size());
        System.out.println("Servis menunggu di antrean : " + antrean.size());
        System.out.println("Servis tercatat            : " + riwayatServis.size());
        System.out.println("Servis selesai             : " + selesai);
        System.out.println("Total pendapatan (selesai) : Rp" + totalSelesai);
    }
}
