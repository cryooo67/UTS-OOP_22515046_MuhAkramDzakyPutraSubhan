/**
 * Mekanik bengkel. Mekanik yang sedang melayani tidak boleh menerima servis lain.
 */
public class Mekanik {
    private String idMekanik;
    private String nama;
    private String spesialisasi;
    private boolean sibuk;
    private int pengalaman;

    /** Konstruktor default. */
    public Mekanik() {
        this.idMekanik = "-";
        this.nama = "-";
        this.spesialisasi = "-";
        this.pengalaman = 0;
        this.sibuk = false;
    }

    /** Konstruktor berparameter; status awal tersedia. */
    public Mekanik(String idMekanik, String nama, String spesialisasi, int pengalaman) {
        setIdMekanik(idMekanik);
        setNama(nama);
        setSpesialisasi(spesialisasi);
        setPengalaman(pengalaman);
        this.sibuk = false;
    }

    public String getIdMekanik() { return idMekanik; }
    public String getNama() { return nama; }
    public String getSpesialisasi() { return spesialisasi; }
    public int getPengalaman() { return pengalaman; }
    public boolean isSibuk() { return sibuk; }

    public void setIdMekanik(String idMekanik) {
        if (idMekanik == null || idMekanik.trim().isEmpty())
            throw new IllegalArgumentException("ID mekanik tidak boleh kosong.");
        this.idMekanik = idMekanik.trim();
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty())
            throw new IllegalArgumentException("Nama mekanik tidak boleh kosong.");
        this.nama = nama.trim();
    }

    public void setSpesialisasi(String spesialisasi) {
        if (spesialisasi == null || spesialisasi.trim().isEmpty())
            throw new IllegalArgumentException("Spesialisasi tidak boleh kosong.");
        this.spesialisasi = spesialisasi.trim();
    }

    public void setPengalaman(int pengalaman) {
        if (pengalaman < 0)
            throw new IllegalArgumentException("Pengalaman tidak boleh negatif.");
        this.pengalaman = pengalaman;
    }

    /** Mulai menangani servis. Mengembalikan false bila mekanik sudah sibuk. */
    public boolean mulaiServis() {
        if (sibuk) return false;
        sibuk = true;
        return true;
    }

    /** Menandai mekanik selesai bekerja dan kembali tersedia. */
    public void selesaiServis() {
        sibuk = false;
    }

    /** True jika mekanik tersedia. */
    public boolean isTersedia() {
        return !sibuk;
    }

    /** Menampilkan data dan status mekanik. */
    public void tampilkanInfo() {
        System.out.println("ID Mekanik  : " + idMekanik);
        System.out.println("Nama        : " + nama);
        System.out.println("Spesialisasi: " + spesialisasi);
        System.out.println("Pengalaman  : " + pengalaman + " tahun");
        System.out.println("Status      : " + (sibuk ? "Sedang melayani" : "Tersedia"));
    }
}
