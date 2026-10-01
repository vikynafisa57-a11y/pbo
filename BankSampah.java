
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

// ============================================
// PROGRAM SISTEM BANK SAMPAH
// Implementasi Konsep PBO
// ============================================


// ============================================
// ABSTRACT CLASS
// ============================================
abstract class Sampah {

    // ========================================
    // ENCAPSULATION
    // Atribut dibuat private
    // ========================================
    private String nama;
    private double berat;
    private double hargaPerKg;

    // ========================================
    // CONSTRUCTOR
    // ========================================
    public Sampah(String nama, double berat, double hargaPerKg) {
        this.nama = nama;
        setBerat(berat);
        this.hargaPerKg = hargaPerKg;
    }

    // ========================================
    // GETTER
    // ========================================
    public String getNama() {
        return nama;
    }

    public double getBerat() {
        return berat;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    // ========================================
    // SETTER + VALIDASI
    // ========================================
    public void setBerat(double berat) {
        if (berat > 0) {
            this.berat = berat;
        } else {
            System.out.println("Berat sampah harus lebih dari 0 kg.");
            this.berat = 0;
        }
    }

    // ========================================
    // ABSTRACT METHOD
    // Akan diimplementasikan oleh class turunan
    // ========================================
    public abstract String getJenis();

    // ========================================
    // METHOD KONKRET
    // Menghitung nilai sampah
    // ========================================
    public double hitungNilai() {
        return berat * hargaPerKg;
    }
}


// ============================================
// INTERFACE
// ============================================
interface DaurUlang {

    void prosesDaurUlang();
}


// ============================================
// INHERITANCE
// Class turunan pertama
// ============================================
class SampahOrganik extends Sampah {

    // Constructor
    public SampahOrganik(
        String nama,
        double berat,
        double hargaPerKg
    ) {
        super(nama, berat, hargaPerKg);
    }

    // ========================================
    // METHOD OVERRIDING
    // ========================================
    @Override
    public String getJenis() {
        return "Organik";
    }
}


// ============================================
// INHERITANCE
// Class turunan kedua
// Sekaligus IMPLEMENTS INTERFACE
// ============================================
class SampahAnorganik extends Sampah
        implements DaurUlang {

    // Constructor
    public SampahAnorganik(
        String nama,
        double berat,
        double hargaPerKg
    ) {
        super(nama, berat, hargaPerKg);
    }

    // ========================================
    // METHOD OVERRIDING
    // ========================================
    @Override
    public String getJenis() {
        return "Anorganik";
    }

    // ========================================
    // IMPLEMENTASI INTERFACE
    // ========================================
    @Override
    public void prosesDaurUlang() {
        System.out.println(
            "Sampah anorganik diproses untuk didaur ulang."
        );
    }
}


// ============================================
// CLASS NASABAH
// Contoh ENCAPSULATION
// ============================================
class Nasabah {

    private String nama;
    private String nomorNasabah;
    private double saldo;

    // ========================================
    // CONSTRUCTOR
    // ========================================
    public Nasabah(String nama, String nomorNasabah) {
        this.nama = nama;
        this.nomorNasabah = nomorNasabah;
        this.saldo = 0;
    }

    // ========================================
    // GETTER
    // ========================================
    public String getNama() {
        return nama;
    }

    public String getNomorNasabah() {
        return nomorNasabah;
    }

    public double getSaldo() {
        return saldo;
    }

    // ========================================
    // SETTER
    // ========================================
    public void setNama(String nama) {
        this.nama = nama;
    }

    // ========================================
    // METHOD OVERLOADING
    // Menambahkan saldo dengan nilai langsung
    // ========================================
    public void tambahSaldo(double jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
        }
    }

    // ========================================
    // METHOD OVERLOADING
    // Menambahkan saldo berdasarkan berat
    // dan harga per kilogram
    // ========================================
    public void tambahSaldo(
        double berat,
        double hargaPerKg
    ) {
        double nilai = berat * hargaPerKg;

        if (nilai > 0) {
            saldo += nilai;
        }
    }

    // ========================================
    // MENAMPILKAN DATA NASABAH
    // ========================================
    public void tampilkanData() {
        System.out.println(
            "Nama Nasabah     : " + nama
        );

        System.out.println(
            "Nomor Nasabah    : " + nomorNasabah
        );

        System.out.println(
            "Saldo            : Rp" + saldo
        );
    }
}


// ============================================
// CLASS SETORAN
// ============================================
class Setoran {

    private Nasabah nasabah;
    private Sampah sampah;

    // ========================================
    // CONSTRUCTOR
    // ========================================
    public Setoran(
        Nasabah nasabah,
        Sampah sampah
    ) {
        this.nasabah = nasabah;
        this.sampah = sampah;
    }

    // ========================================
    // PROSES SETORAN
    // ========================================
    public void prosesSetoran() {

        // Menghitung nilai sampah
        double nilai = sampah.hitungNilai();

        // Menambahkan nilai ke saldo nasabah
        nasabah.tambahSaldo(nilai);

        System.out.println(
            "\n===== PROSES SETORAN ====="
        );

        System.out.println(
            "Nasabah          : "
            + nasabah.getNama()
        );

        System.out.println(
            "Jenis Sampah     : "
            + sampah.getJenis()
        );

        System.out.println(
            "Nama Sampah      : "
            + sampah.getNama()
        );

        System.out.println(
            "Berat            : "
            + sampah.getBerat()
            + " kg"
        );

        System.out.println(
            "Harga per Kg     : Rp"
            + sampah.getHargaPerKg()
        );

        System.out.println(
            "Nilai Setoran    : Rp"
            + nilai
        );
    }
}


// ============================================
// MAIN CLASS
// ============================================
public class BankSampah {

    // ========================================
    // SIMPAN DATA NASABAH
    // ========================================
    static void simpanDataNasabah(Nasabah nasabah) {
        try {
            FileWriter writer = new FileWriter("data_nasabah.txt");

            writer.write("Nama: " + nasabah.getNama() + "\n");
            writer.write("Nomor Nasabah: " + nasabah.getNomorNasabah() + "\n");
            writer.write("Saldo: " + nasabah.getSaldo() + "\n");

            writer.close();

            System.out.println("Data nasabah berhasil disimpan!");

        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }


    // ========================================
    // NOMOR NASABAH OTOMATIS
    // ========================================
    static int nomorUrut = 1;

    // ========================================
    // METHOD MEMBUAT NOMOR NASABAH
    // ========================================
    public static String generateNomorNasabah() {

        return String.format(
            "BS%03d",
            nomorUrut++
        );
    }

    // ========================================
    // MAIN METHOD
    // ========================================
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ====================================
        // JUDUL PROGRAM
        // ====================================
        System.out.println(
            "=================================="
        );

        System.out.println(
            "       SISTEM BANK SAMPAH"
        );

        System.out.println(
            "=================================="
        );


        // ====================================
        // INPUT DATA NASABAH
        // ====================================
        System.out.print(
            "\nMasukkan nama nasabah: "
        );

        String nama = input.nextLine();

        // Membuat nomor nasabah otomatis
        String nomorNasabah =
            generateNomorNasabah();

        // Membuat object Nasabah
        Nasabah nasabah =
            new Nasabah(
                nama,
                nomorNasabah
            );

        System.out.println(
            "\nNasabah berhasil dibuat!"
        );

        System.out.println(
            "Nama          : "
            + nasabah.getNama()
        );

        System.out.println(
            "Nomor Nasabah : "
            + nasabah.getNomorNasabah()
        );


        // ====================================
        // MENU SETOR SAMPAH
        // ====================================
        String lanjut = "";

        do {

            System.out.println(
                "\n=================================="
            );

            System.out.println(
                "          INPUT SETORAN"
            );

            System.out.println(
                "=================================="
            );

            System.out.println(
                "1. Sampah Organik"
            );

            System.out.println(
                "2. Sampah Anorganik"
            );

            // Input pilihan
            System.out.print(
                "Pilih jenis sampah (1/2): "
            );

            int pilihan = input.nextInt();

            input.nextLine();


            // =================================
            // INPUT NAMA SAMPAH
            // =================================
            System.out.print(
                "Masukkan nama sampah: "
            );

            String namaSampah =
                input.nextLine();


            // =================================
            // INPUT BERAT
            // =================================
            System.out.print(
                "Masukkan berat sampah (kg): "
            );

            double berat =
                input.nextDouble();


            // =================================
            // INPUT HARGA
            // =================================
            System.out.print(
                "Masukkan harga per kg: Rp"
            );

            double hargaPerKg =
                input.nextDouble();


            // =================================
            // POLYMORPHISM
            // =================================
            Sampah sampah;


            // =================================
            // MEMBUAT OBJECT SESUAI PILIHAN
            // =================================
            if (pilihan == 1) {

                sampah =
                    new SampahOrganik(
                        namaSampah,
                        berat,
                        hargaPerKg
                    );

            } else if (pilihan == 2) {

                sampah =
                    new SampahAnorganik(
                        namaSampah,
                        berat,
                        hargaPerKg
                    );

            } else {

                System.out.println(
                    "Pilihan tidak valid!"
                );

                continue;
            }


            // =================================
            // MEMBUAT OBJECT SETORAN
            // =================================
            Setoran setoran =
                new Setoran(
                    nasabah,
                    sampah
                );


            // =================================
            // PROSES SETORAN
            // =================================
            setoran.prosesSetoran();


            // =================================
            // JIKA ANORGANIK
            // PROSES DAUR ULANG
            // =================================
            if (sampah instanceof DaurUlang) {

                DaurUlang daurUlang =
                    (DaurUlang) sampah;

                daurUlang.prosesDaurUlang();
            }


            // =================================
            // TAMPILKAN SALDO TERBARU
            // =================================
            System.out.println(
                "\n===== SALDO TERBARU ====="
            );

            System.out.println(
                "Nama  : "
                + nasabah.getNama()
            );

            System.out.println(
                "Saldo : Rp"
                + nasabah.getSaldo()
            );


            // =================================
            // TANYA SETOR LAGI
            // =================================
            System.out.print(
                "\nApakah ingin setor sampah lagi? (y/n): "
            );

            lanjut = input.next();

        } while (
            lanjut.equalsIgnoreCase("y")
        );


        // ====================================
        // DATA AKHIR NASABAH
        // ====================================
        System.out.println(
            "\n=================================="
        );

        System.out.println(
            "          DATA NASABAH"
        );

        System.out.println(
            "=================================="
        );

       nasabah.tampilkanData();

        simpanDataNasabah(nasabah);


        // ====================================
        // PENUTUP
        // ====================================
        System.out.println(
            "\n=================================="
        );

        System.out.println(
            "     TERIMA KASIH"
        );

        System.out.println(
            "=================================="
        );


        // Menutup Scanner
        input.close();
    }
}

