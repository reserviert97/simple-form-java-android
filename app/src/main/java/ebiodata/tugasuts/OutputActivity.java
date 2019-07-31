package ebiodata.tugasuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {
    public static final String MAHASISWA = "mahasiswa";

    TextView nim, nama, tanggalLahir, jenisKelamin, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        nim = findViewById(R.id.isinim);
        nama = findViewById(R.id.hasilNama);
        tanggalLahir = findViewById(R.id.hasilTanggalLahir);
        jenisKelamin = findViewById(R.id.jenisk);
        alamat = findViewById(R.id.hasilAlamat);

        Mahasiswa mhs = getIntent().getParcelableExtra(MAHASISWA);

        nim.setText(mhs.getNim());
        nama.setText(mhs.getNama());
        tanggalLahir.setText(mhs.getTanggalLahir());
        jenisKelamin.setText(mhs.getJenisKelamin());
        alamat.setText(mhs.getAlamat());
    }
}
