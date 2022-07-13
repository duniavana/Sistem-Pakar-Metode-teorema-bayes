package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ptdiagnosis extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptdiagnosis);
    }
}