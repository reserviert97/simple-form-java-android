package ebiodata.tugasuts;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    //Deklarasi Component yang diperlukan
    Button tombolSimpan;
    EditText inputNim, inputNama, inputAlamat, inputTanggalLahir;
    TextView thasil;
    RadioGroup radioJenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//         pilih component berdasarkan idnya
        tombolSimpan = findViewById(R.id.tombolSimpan);
        inputNim = findViewById(R.id.inputNim);
        inputNama = findViewById(R.id.inputNama);

        inputTanggalLahir = findViewById(R.id.inputTanggal);
        radioJenisKelamin = findViewById(R.id.jk);
        inputAlamat = findViewById(R.id.alamat);

        // Pindah Activity
        tombolSimpan.setOnClickListener(this);
        inputTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilkanTanggal();
            }
        });

    }

    private void tampilkanTanggal(){
        DatePickerDialog kalender = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        kalender.show();
    }

    public void onClick(View v){
        String inputnim = inputNim.getText().toString().trim();
        String inputnama = inputNama.getText().toString();
        String inputalamat = inputAlamat.getText().toString();
        String inputtanggallahir = inputTanggalLahir.getText().toString();

        int gender = radioJenisKelamin.getCheckedRadioButtonId();
        RadioButton jk = findViewById(R.id.gender);
        String inputjk = jk.getText().toString();

        Boolean formKosong = false;

        if (TextUtils.isEmpty(inputnim)) {
            formKosong = true;
            inputNim.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(inputnama)) {
            formKosong = true;
            inputNama.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(inputalamat)) {
            formKosong = true;
            inputAlamat.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(inputtanggallahir)) {
            formKosong = true;
            inputTanggalLahir.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(inputjk)){
            formKosong = true;
        }

        if (!formKosong) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.setNim(inputnim);
            mhs.setNama(inputnama);
            mhs.setAlamat(inputalamat);
            mhs.setTanggalLahir(inputtanggallahir);
            mhs.setJenisKelamin(inputjk);

            Intent moveIntent = new Intent(MainActivity.this, OutputActivity.class);
            moveIntent.putExtra(OutputActivity.MAHASISWA, mhs);
            startActivity(moveIntent);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar kalender = Calendar.getInstance();
        kalender.set(year,month,dayOfMonth);
        SimpleDateFormat format = new SimpleDateFormat("d MMMM yyyy");
        String tanggalan = format.format(kalender.getTime());
        inputTanggalLahir.setText(tanggalan  );
    }
}
