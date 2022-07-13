package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.EditText;
import android.widget.Toast;

import com.example.sistempakarkambing.activity.LihatPenyakitActivity;


public class MainActivity extends AppCompatActivity {


    private CardView btnmainanalisa, btndaftarpenyakit, btnhistrory,btnpetunjuk,btngejala,btnrujuk;
    private String m_namaText = "";
    public static String url = "https://skripsii.uptdmetrologikabsorong.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnmainanalisa = (CardView)findViewById(R.id.btnmainanalisa);
        btndaftarpenyakit = (CardView)findViewById(R.id.btndaftarpenyakit);
        btnpetunjuk = (CardView)findViewById(R.id.btnpetunjuk);

        btnhistrory = (CardView)findViewById(R.id.btnhistory);
        btngejala = (CardView)findViewById(R.id.btngejala);
        btnrujuk = (CardView)findViewById(R.id.btnrujuk);

        btnmainanalisa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                builder.setTitle("Isikan Nama Pemilik Ternak");

                final EditText input = new EditText(view.getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        m_namaText = input.getText().toString();
                        if(m_namaText.equals("")){
                            Toast.makeText(getApplicationContext(), "Isi nama Pemilik Ternak terlebih dahulu", Toast.LENGTH_LONG).show();

                        }else {
                            Intent intent = new Intent(getApplicationContext(), AnalisaActivity.class);
                            Bundle b = new Bundle();
                            b.putString("namamu", m_namaText);

                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    }
                });


                builder.show();
//              startActivity(new Intent(MainActivity.this, Diagnosa.class));
            }
        });

        btndaftarpenyakit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, daftarPenyakit.class);
                startActivity(i);
            }
        });

        btnrujuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, rujuk.class);
                startActivity(i);
            }
        });

        btnpetunjuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, petunjuk.class);
                startActivity(i);
            }
        });

        btnhistrory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, LihatPenyakitActivity.class);
                startActivity(i);
            }
        });

        btngejala.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, daftarGejala.class);
                startActivity(i);
            }
        });

   /*   btnmainexit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });*/

    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {

        getMenuInflater() .inflate(R.menu.optioon_menu, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.option_Tentang)

        {
            startActivity(new Intent(this, tentang.class));
        }

        else  if (id==R.id.option_shared)

        {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plan");
            String shareBody = "Ayo coba Aplikasi Sistem Penyakit Pakar kambing. Saya menggunakannya untuk mendeteksi penyakit Hewan Ternak Kambing . Dapatkan secara gratis di http://bit.ly/kambing ";

            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity((Intent.createChooser(myIntent, "Share via")));
        }
        else  if (id==R.id.option_exit)

        {
            Toast.makeText(this, "Anda Telah Keluar Dari Sistem Pakar Penyakit Kambing", Toast.LENGTH_SHORT).show();
            finish();
        }


        else  if (id==R.id.option_dokter)

        {

            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://skripsi.uptdmetrologikabsorong.com/"));
            startActivity(browserIntent);


        }else  if (id==R.id.option_dokterandro)

        {
            startActivity(new Intent(this, login.class));
        }


        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_utama);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Apakah Anda yakin ingin keluar ??")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { dialog.cancel(); }
                });
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }
}


