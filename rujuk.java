package com.example.sistempakarkambing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class rujuk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rujuk);

        final Button kampung1 = findViewById(R.id.kampung1);
        final Button kampung2 = findViewById(R.id.kampung2);
        final Button kampung3 = findViewById(R.id.kampung3);
        final Button kampung4 = findViewById(R.id.kampung4);
        final Button kampung5 = findViewById(R.id.kampung5);
        final Button kampung6 = findViewById(R.id.kampung6);
        final Button kampung7 = findViewById(R.id.kampung7);


        kampung1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rujuk.this, kampung1.class));
            }
        });

        kampung2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rujuk.this, kampung2.class));
            }
        });

        kampung3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rujuk.this, kampung3.class));
            }
        });

        kampung4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rujuk.this, kampung4.class));
            }
        });

        kampung5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rujuk.this, kampung5.class));
            }
        });

        kampung6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rujuk.this, kampung6.class));
            }
        });

        kampung7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rujuk.this, kampung7.class));
            }
        });
    }


}