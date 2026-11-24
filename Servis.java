/**
 * Transaksi servis yang menghubungkan Kendaraan dengan Mekanik.
 */
public class Servis {
    static final String STATUS_MENUNGGU = "Menunggu";
    static final String STATUS_DIPROSES = "Sedang diproses";
    static final String STATUS_SELESAI = "Selesai";

    private String idServis;
    private Kendaraan kendaraan;
    private Mekanik mekanik;
    private double biayaJasa;
    private double biayaSukuCadang;
    private String status;

    /** Konstruktor default. */
    public Servis() {
        this.idServis = "-";
        this.biayaJasa = 0;
        this.biayaSukuCadang = 0;
        this.status = STATUS_MENUNGGU;
    }

    /** Konstruktor berparameter; mekanik belum ditentukan karena servis masih menunggu. */
    public Servis(String idServis, Kendaraan kendaraan, double biayaJasa, double biayaSukuCadang) {
        setIdServis(idServis);
        setKendaraan(kendaraan);
        setBiayaJasa(biayaJasa);
        setBiayaSukuCadang(biayaSukuCadang);
        this.status = STATUS_MENUNGGU;
    }

    public String getIdServis() { return idServis; }
    public Kendaraan getKendaraan() { return kendaraan; }
    public Mekanik getMekanik() { return mekanik; }
    public double getBiayaJasa() { return biayaJasa; }
    public double getBiayaSukuCadang() { return biayaSukuCadang; }
    public String getStatus() { return status; }

    public void setIdServis(String idServis) {
        if (idServis == null || idServis.trim().isEmpty())
            throw new IllegalArgumentException("ID servis tidak boleh kosong.");
        this.idServis = idServis.trim();
    }

    public void setKendaraan(Kendaraan kendaraan) {
        if (kendaraan == null)
            throw new IllegalArgumentException("Kendaraan tidak boleh null.");
        this.kendaraan = kendaraan;
    }

    public void setMekanik(Mekanik mekanik) {
        this.mekanik = mekanik;
    }

    public void setBiayaJasa(double biayaJasa) {
        if (biayaJasa < 0)
            throw new IllegalArgumentException("Biaya jasa dan suku cadang tidak boleh negatif.");
        this.biayaJasa = biayaJasa;
    }

    public void setBiayaSukuCadang(double biayaSukuCadang) {
        if (biayaSukuCadang < 0)
            throw new IllegalArgumentException("Biaya jasa dan suku cadang tidak boleh negatif.");
        this.biayaSukuCadang = biayaSukuCadang;
    }

    public void setStatus(String status) {
        if (!STATUS_MENUNGGU.equals(status) && !STATUS_DIPROSES.equals(status) && !STATUS_SELESAI.equals(status))
            throw new IllegalArgumentException("Status tidak valid.");
        this.status = status;
    }

    /** Total biaya = biaya jasa + biaya suku cadang. */
    public double hitungTotalBiaya() {
        return biayaJasa + biayaSukuCadang;
    }

    /** Memperbarui status transaksi. */
    public void ubahStatus(String statusBaru) {
        setStatus(statusBaru);
    }

    /** Menampilkan rincian transaksi dan total biaya. */
    public void tampilkanDetail() {
        System.out.println("ID Servis        : " + idServis);
        System.out.println("Kendaraan        : " + kendaraan.getMerk());
        System.out.println("Plat             : " + kendaraan.getNoPlat());
        System.out.println("Mekanik          : " + (mekanik == null ? "belum ditentukan" : mekanik.getNama()));
        System.out.println("Biaya Jasa       : Rp" + biayaJasa);
        System.out.println("Biaya Suku Cadang: Rp" + biayaSukuCadang);
        System.out.println("Total Biaya      : Rp" + hitungTotalBiaya());
        System.out.println("Status           : " + status);
    }
}
