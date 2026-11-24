/**
 * Kendaraan pelanggan yang dapat didaftarkan dan dicari berdasarkan nomor plat.
 */
public class Kendaraan {
    private static final int TAHUN_MIN = 1900;
    private static final int TAHUN_MAX = 2100;

    private String noPlat;
    private String merk;
    private String tipe;
    private int tahun;
    private String namaPemilik;

    /** Konstruktor default: mengisi nilai awal yang aman. */
    public Kendaraan() {
        this.noPlat = "-";
        this.merk = "-";
        this.tipe = "-";
        this.tahun = TAHUN_MIN;
        this.namaPemilik = "-";
    }

    /** Konstruktor berparameter; memakai setter agar validasi ikut berjalan. */
    public Kendaraan(String noPlat, String merk, String tipe, int tahun, String namaPemilik) {
        setNoPlat(noPlat);
        setMerk(merk);
        setTipe(tipe);
        setTahun(tahun);
        setNamaPemilik(namaPemilik);
    }

    public String getNoPlat() { return noPlat; }
    public String getMerk() { return merk; }
    public String getTipe() { return tipe; }
    public int getTahun() { return tahun; }
    public String getNamaPemilik() { return namaPemilik; }

    public void setNoPlat(String noPlat) {
        if (noPlat == null || noPlat.trim().isEmpty())
            throw new IllegalArgumentException("Nomor plat tidak boleh kosong.");
        this.noPlat = noPlat.trim();
    }

    public void setMerk(String merk) {
        if (merk == null || merk.trim().isEmpty())
            throw new IllegalArgumentException("Merk tidak boleh kosong.");
        this.merk = merk.trim();
    }

    public void setTipe(String tipe) {
        if (tipe == null || tipe.trim().isEmpty())
            throw new IllegalArgumentException("Tipe tidak boleh kosong.");
        this.tipe = tipe.trim();
    }

    public void setTahun(int tahun) {
        if (tahun < TAHUN_MIN || tahun > TAHUN_MAX)
            throw new IllegalArgumentException("Tahun harus antara " + TAHUN_MIN + " dan " + TAHUN_MAX + ".");
        this.tahun = tahun;
    }

    public void setNamaPemilik(String namaPemilik) {
        if (namaPemilik == null || namaPemilik.trim().isEmpty())
            throw new IllegalArgumentException("Nama pemilik tidak boleh kosong.");
        this.namaPemilik = namaPemilik.trim();
    }

    /** Menampilkan informasi kendaraan. */
    public void tampilkanInfo() {
        System.out.println("No. Plat    : " + noPlat);
        System.out.println("Merk        : " + merk);
        System.out.println("Tipe        : " + tipe);
        System.out.println("Tahun       : " + tahun);
        System.out.println("Nama Pemilik: " + namaPemilik);
    }

    /** Mengubah merk dan tipe kendaraan. */
    public void ubahData(String merk, String tipe) {
        setMerk(merk);
        setTipe(tipe);
    }

    /** Memastikan data kendaraan sah (plat tidak kosong). */
    public boolean cekKendaraan() {
        return noPlat != null && !noPlat.trim().isEmpty();
    }

    /** Membandingkan plat tanpa membedakan huruf besar/kecil. */
    public boolean cocokPlat(String plat) {
        return noPlat.equalsIgnoreCase(plat.trim());
    }
}
