package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class daftarPenyakitDetail extends AppCompatActivity {

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_penyakit_detail);

        TextView ededitpenyakitnama = (TextView) findViewById(R.id.ededitpenyakitnama);
        TextView ededitpenyakitketerangan = (TextView) findViewById(R.id.ededitpenyakitketerangan);
        TextView ededitpenyakitpenanganan = (TextView) findViewById(R.id.ededitpenyakitpenanganan);


        ededitpenyakitnama.setText(getIntent().getStringExtra("nama_penyakit"));
        ededitpenyakitketerangan.setText(getIntent().getStringExtra("keterangan"));
        ededitpenyakitpenanganan.setText(getIntent().getStringExtra("penanganan"));
    }
}