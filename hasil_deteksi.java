package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.x;
import static android.R.attr.y;

public class hasil_deteksi extends AppCompatActivity {

    Toolbar toolbar;
    protected Cursor cursor;
    TextView nama_penyakit;

    Intent intent;
    SimpleDateFormat sdft, sdfw;
    TextView textSolusi, textTanggal, textWaktu;
    Locale inLocale;
    private String m_Text = "";

    String final_result, persenterbesar;

    TextView pro1;

    Double hasildesimal = 0.0d;
    String isitb,gejala,penyakit,sendpenanganan,nilai,lpersen_terbesar,namamukirim,time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_deteksi);

        //tanggal n jam
        inLocale = new Locale("id", "ID");
        sdft = new SimpleDateFormat("EEEE, dd MMMM yyyy", inLocale);
        sdfw = new SimpleDateFormat("HH:mm", inLocale);

        textTanggal = (TextView) findViewById(R.id.tanggal);
        textWaktu = (TextView) findViewById(R.id.waktu);

        textTanggal.setText(sdft.format(new Date()));
        textWaktu.setText(sdfw.format(new Date()));



        TextView namamu = (TextView) findViewById(R.id.namamu);
        TextView final_result = (TextView) findViewById(R.id.final_result);
        TextView persenterbesar = (TextView) findViewById(R.id.persenterbesar);

        TextView judul_keterangan = (TextView) findViewById(R.id.textView5);
        TextView nama_penyakit = (TextView) findViewById(R.id.nama_penyakit);

        TextView persen = (TextView) findViewById(R.id.persen);
        TextView penanganan = (TextView) findViewById(R.id.penanganan);

        TextView nama_penyakit2 = (TextView) findViewById(R.id.nama_penyakit2);
        TextView persen2 = (TextView) findViewById(R.id.persen2);
        TextView penanganan2 = (TextView) findViewById(R.id.penanganan2);
        TextView nama_penyakit3 = (TextView) findViewById(R.id.nama_penyakit3);
        TextView persen3 = (TextView) findViewById(R.id.persen3);
        TextView penanganan3 = (TextView) findViewById(R.id.penanganan3);

        judul_keterangan.setVisibility(View.VISIBLE);

        Bundle b = getIntent().getExtras();

        namamukirim= b.getString("namamu");
        time= b.getString("time");
        date= b.getString("date");
        hasildesimal= b.getDouble("hasildesimal");
        gejala= b.getString("gejala2");
        penyakit= b.getString("lterbesar");
        sendpenanganan = b.getString("penanganan");
        nilai = b.getString("nilai");
        lpersen_terbesar = b.getString("lpersen_terbesar");

        namamu.setText(getIntent().getStringExtra("namamu"));
        final_result.setText(getIntent().getStringExtra("banyak_hasil"));
        persenterbesar.setText(getIntent().getStringExtra("banyak_persen"));

        nama_penyakit.setText(getIntent().getStringExtra("lterbesar"));

        persen.setText(getIntent().getStringExtra("lpersen_terbesar"));

        penanganan.setText(getIntent().getStringExtra("penanganan"));


        nama_penyakit2.setText(getIntent().getStringExtra("lterbesar2"));
        persen2.setText(getIntent().getStringExtra("lpersen_terbesar2"));
        penanganan2.setText(getIntent().getStringExtra("penanganan2"));
        nama_penyakit3.setText(getIntent().getStringExtra("lterbesar3"));
        persen3.setText(getIntent().getStringExtra("lpersen_terbesar3"));
        penanganan3.setText(getIntent().getStringExtra("penanganan3"));


        Button Mulailagi = (Button) findViewById(R.id.mulailagi);
        Mulailagi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnalisaActivity.class);
                Bundle b = new Bundle();
                b.putString("namamu",namamukirim);
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        Button detail = (Button) findViewById(R.id.btndetail);
        detail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        Button cetakpdf = (Button) findViewById(R.id.btncetakpdf);
        cetakpdf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Isikan Nama File");

                final EditText input = new EditText(view.getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        m_Text = input.getText().toString();
                        if(m_Text.equals("")){
                            Toast.makeText(getApplicationContext(), "Isi nama peternak terlebih dahulu", Toast.LENGTH_LONG).show();

                        }else {
                            Intent intent = new Intent(getApplicationContext(), printPdf.class);
                            Bundle b = new Bundle();
                            b.putString("hasildesimal",String.format("%.2f", hasildesimal * 100));
                            b.putString("namafile", m_Text);
                            b.putString("gejala", gejala);
                            b.putString("penyakit",penyakit);
                            b.putString("namamu",namamukirim);
                            b.putString("time",time);
                            b.putString("date",date);
                            b.putString("sendpenanganan",sendpenanganan);
                            intent.putExtras(b);
                            startActivity(intent);
                            Toast.makeText(hasil_deteksi.this, "Berhasil Menambahkan Data di memory telefon/Hasil_Diagnosis", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                //startActivity(new Intent(MainActivity.this, Diagnosa.class));
            }
        });

    }
}