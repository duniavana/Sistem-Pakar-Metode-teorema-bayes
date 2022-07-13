package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;


public class petunjuk extends AppCompatActivity {

    private CardView ptdiagnosis,ptpenyakit, ptgejala, pthistory,ptlainnya,ptrujuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk);

        ptdiagnosis = (CardView)findViewById(R.id.ptdiagnosis);
        ptpenyakit = (CardView)findViewById(R.id.ptpenyakit);
        ptgejala = (CardView)findViewById(R.id.ptgejala);
        pthistory = (CardView)findViewById(R.id.pthistory);
        ptlainnya = (CardView)findViewById(R.id.ptlainnya);
        ptrujuk = (CardView)findViewById(R.id.ptrujuk);


        ptdiagnosis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(petunjuk.this, ptdiagnosis.class);
                startActivity(i);
            }
        });

        ptpenyakit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(petunjuk.this, ptpenyakit.class);
                startActivity(i);
            }
        });

        ptgejala.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(petunjuk.this, ptgejala.class);
                startActivity(i);
            }
        });
        pthistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(petunjuk.this, pthistory.class);
                startActivity(i);
            }
        });

        ptlainnya.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(petunjuk.this, ptlainnya.class);
                startActivity(i);
            }
        });

        ptrujuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(petunjuk.this, ptrujuk.class);
                startActivity(i);
            }
        });
    }
}