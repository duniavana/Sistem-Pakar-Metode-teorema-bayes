package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class detailGejala extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gejala);

        TextView ededitpenyakitnama = (TextView) findViewById(R.id.ededitpenyakitnama);
        TextView ededitpenyakitketerangan = (TextView) findViewById(R.id.ededitpenyakitketerangan);


        ededitpenyakitnama.setText(getIntent().getStringExtra("namagejala"));
        ededitpenyakitketerangan.setText(getIntent().getStringExtra("keterangangejala"));
    }
}