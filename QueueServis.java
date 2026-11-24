/**
 * ADT Queue (FIFO) berbasis array untuk servis yang menunggu.
 * Elemen depan selalu berada pada indeks 0.
 */
public class QueueServis {
    private static final int KAPASITAS_DEFAULT = 5;
    private Servis[] data;
    private int jumlah;
    private int kapasitas;

    /** Kapasitas wajib lebih dari 0. */
    public QueueServis(int kapasitas) {
        if (kapasitas <= 0)
            throw new IllegalArgumentException("Kapasitas antrean harus lebih dari 0.");
        this.kapasitas = kapasitas;
        this.data = new Servis[kapasitas];
        this.jumlah = 0;
    }

    /** Prasyarat: servis tidak null dan antrean belum penuh. Efek: servis masuk di belakang. */
    public boolean enqueue(Servis servis) {
        if (servis == null) {
            System.out.println("Data servis tidak boleh null.");
            return false;
        }
        if (isFull()) {
            System.out.println("Antrean penuh");
            return false;
        }
        data[jumlah] = servis;
        jumlah++;
        System.out.println("Servis berhasil masuk antrean");
        return true;
    }

    /** Prasyarat: antrean tidak kosong. Mengembalikan null jika kosong. O(n) karena penggeseran. */
    public Servis dequeue() {
        if (isEmpty()) {
            System.out.println("Antrean kosong");
            return null;
        }
        Servis depan = data[0];
        for (int i = 0; i < jumlah - 1; i++) {
            data[i] = data[i + 1];
        }
        jumlah--;
        data[jumlah] = null;
        return depan;
    }

    /** Melihat elemen depan tanpa menghapus; null jika kosong. */
    public Servis front() {
        return isEmpty() ? null : data[0];
    }

    public boolean isEmpty() { return jumlah == 0; }

    public boolean isFull() { return jumlah >= kapasitas; }

    public int size() { return jumlah; }

    /** Menampilkan seluruh servis yang menunggu. */
    public void tampilkanAntrean() {
        if (isEmpty()) {
            System.out.println("Antrean kosong");
            return;
        }
        System.out.println("\n=== DAFTAR ANTREAN ===");
        for (int i = 0; i < jumlah; i++) {
            Servis s = data[i];
            System.out.println((i + 1) + ". " + s.getIdServis() + " - " + s.getKendaraan().getMerk()
                    + " - " + s.getKendaraan().getNoPlat() + " - " + s.getStatus());
        }
        System.out.println("Jumlah antrean: " + size());
    }
}
