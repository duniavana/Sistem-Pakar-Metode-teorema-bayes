package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sistempakarkambing.activity.HomeScreenActivity;

public class admin extends AppCompatActivity {

    private CardView btndatapenyakit, btndatagejala, btndataaturan, btngantipassword;
    public static String userlogin = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btndatapenyakit = (CardView)findViewById(R.id.btndatapenyakit);
        btndatagejala = (CardView)findViewById(R.id.btndatagejala);
        btndataaturan = (CardView) findViewById(R.id.btndataaturan);
        btngantipassword = (CardView) findViewById(R.id.btngantipassword);

        btndatapenyakit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(admin.this, penyakit.class);
                startActivity(i);
            }
        });

        btndatagejala.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(admin.this, gejala.class);
                startActivity(i);
            }
        });

        btndataaturan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(admin.this, aturan.class);
                startActivity(i);
            }
        });
        btngantipassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(admin.this, gantiPassword.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {

        getMenuInflater() .inflate(R.menu.optioon_admin, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.option_ganti)

        {
            startActivity(new Intent(this, gantiPassword.class));
        }
        else  if (id==R.id.option_bantuan)

        {
            startActivity(new Intent(this, login.class));
        }
        else  if (id==R.id.option_Tentang)

        {
            startActivity(new Intent(this, HomeScreenActivity.class));
        }
        else  if (id==R.id.option_logout)

        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("Logout Admin");
            builder.setMessage("Apakah Anda yakin ingin logout ??")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            admin.this.finish();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Logout Admin");
        builder.setMessage("Apakah Anda yakin ingin logout ??")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        admin.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}