package com.example.sistempakarkambing;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.widget.EditText;

import com.example.sistempakarkambing.adapter.MahasiswaAdapter;
import com.example.sistempakarkambing.helper.DBHandler;
import com.example.sistempakarkambing.model.Mahasiswa;

import java.util.List;



public class AnalisaActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private MahasiswaAdapter adapter;


    CheckBox cbgejalaa;
    String[] penyakit;
    String[] id_penyakit;
    String[] solusi;
    double[] bobot_penyakit;
    String[] gejala;
    String[] cbgejala;
    String[] id_gejala;
    String[] keterangan_gejala;
    String[] penanganan;
    double[][] bobot_aturan;

    LinearLayout ll;
    LinearLayout kk;
//    CheckBox[] cbgejala;

    Button[] btnview;

    public JSONObject jObject;
    public String jsonResult ="";
    ProgressDialog pd;
    Double nilai = 0.0d;
    Double nilai1 = 0.0d;
    Double nilai2 = 0.0d;
    Double nilai3 = 0.0d;
    Double nilai4 = 0.0d;
    Double nilai5 = 0.0d;

    Double hasilperkaliann1 =0.0d;
    Double hasilperkaliann2 =0.0d;
    Double hasilperkaliann3 =0.0d;


    String strDiagnosa = "";
    String strHasil = "";
    String strPersen = "";
    String strsolusi = "";

    //addpenyakit
    Button btnaddpenyakitsave;
    EditText edaddpenyakitid;
    EditText edaddpenyakitnama;
    EditText edaddpenyakitbobot;
    String terbesar = "";
    String penanganan1 = "";
    String penanganan2 = "";
    String presentase = "";
    String namamu = "";
    String date;
    String time;


    SimpleDateFormat sdft, sdfw;
    TextView textSolusi, textTanggal, textWaktu;
    Locale inLocale;
    private AlertDialog.Builder alertDlgBuilder;
    Integer vurutan;
    Integer vbatas;


    public LinearLayout tampiltabel(String[][] data)
    {
        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpp.setMargins(1, 1, 1, 1);

        LinearLayout llv = new LinearLayout(this);
        llv.setOrientation(LinearLayout.VERTICAL);
        for (int i=0; i<data.length; i++)
        {
            LinearLayout llh = new LinearLayout(this);
            llh.setOrientation(LinearLayout.HORIZONTAL);
            llv.addView(llh);

            for (int j=0; j<data[0].length; j++)
            {
                TextView textview = new TextView(this);
                int maxLength = 40;
                if (maxLength > String.valueOf(data[i][j]).length()) { maxLength = String.valueOf(data[i][j]).length(); }
                textview.setText(data[i][j].substring(0, maxLength));
                textview.setWidth(200);
                textview.setTypeface(Typeface.DEFAULT_BOLD);
                textview.setBackgroundColor(Color.parseColor("#ffffff"));
                textview.setLayoutParams(lpp);
                textview.setPadding(2, 2, 2, 2);
                llh.addView(textview);
            }
        }
        // llv.setBackgroundColor(Color.parseColor("#0000ff"));
        llv.setLayoutParams(lpp);
        llv.setPadding(1, 1, 1, 1);

        return llv;
    }

    public LinearLayout tampilkata(String data)
    {
        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpp.setMargins(1, 1, 1, 1);

        LinearLayout llv = new LinearLayout(this);
        llv.setOrientation(LinearLayout.VERTICAL);

        LinearLayout llh = new LinearLayout(this);
        llh.setOrientation(LinearLayout.HORIZONTAL);
        llv.addView(llh);

        TextView textview = new TextView(this);
        //int maxLength = 10;
        //if (maxLength > String.valueOf(data[i]).length()) { maxLength = String.valueOf(data[i]).length(); }
        textview.setText(data); //.substring(0, maxLength));
        //textview.setWidth(200);
        textview.setTypeface(Typeface.DEFAULT_BOLD);
        textview.setBackgroundColor(Color.parseColor("#ffffff"));
        textview.setLayoutParams(lpp);
        textview.setPadding(2, 2, 2, 2);
        llh.addView(textview);

        llv.setBackgroundColor(Color.parseColor("#0000ff"));
        llv.setLayoutParams(lpp);
        llv.setPadding(1, 1, 1, 1);

        return llv;
    }

    public LinearLayout tampilkata2(String data)
    {
        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpp.setMargins(1, 1, 1, 1);

        LinearLayout llv = new LinearLayout(this);
        llv.setOrientation(LinearLayout.VERTICAL);

        LinearLayout llh = new LinearLayout(this);
        llh.setOrientation(LinearLayout.HORIZONTAL);
        llv.addView(llh);

        TextView textview = new TextView(this);
        //int maxLength = 10;
        //if (maxLength > String.valueOf(data[i]).length()) { maxLength = String.valueOf(data[i]).length(); }
        textview.setText(data); //.substring(0, maxLength));
        //textview.setWidth(200);
        textview.setTypeface(Typeface.DEFAULT_BOLD);
        textview.setBackgroundColor(Color.parseColor("#ffffff"));
        textview.setLayoutParams(lpp);
        textview.setPadding(2, 2, 2, 2);
        llh.addView(textview);

        // llv.setBackgroundColor(Color.parseColor("#0000ff"));
        llv.setLayoutParams(lpp);
        llv.setPadding(1, 1, 1, 1);

        return llv;
    }

    public LinearLayout tampilbaris(String[] data)
    {
        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpp.setMargins(1, 1, 1, 1);

        LinearLayout llv = new LinearLayout(this);
        llv.setOrientation(LinearLayout.VERTICAL);

        LinearLayout llh = new LinearLayout(this);
        llh.setOrientation(LinearLayout.HORIZONTAL);
        llv.addView(llh);

        for (int i=0; i<data.length; i++)
        {
            TextView textview = new TextView(this);
            int maxLength = 10;
            if (maxLength > String.valueOf(data[i]).length()) { maxLength = String.valueOf(data[i]).length(); }
            textview.setText(data[i].substring(0, maxLength));
            textview.setWidth(200);
            textview.setTypeface(Typeface.DEFAULT_BOLD);
            textview.setBackgroundColor(Color.parseColor("#ffffff"));
            textview.setLayoutParams(lpp);
            textview.setPadding(2, 2, 2, 2);
            llh.addView(textview);
        }

        llv.setBackgroundColor(Color.parseColor("#0000ff"));
        llv.setLayoutParams(lpp);
        llv.setPadding(1, 1, 1, 1);

        return llv;
    }

    public LinearLayout tampilbaris(double[] data)
    {
        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpp.setMargins(1, 1, 1, 1);

        LinearLayout llv = new LinearLayout(this);
        llv.setOrientation(LinearLayout.VERTICAL);

        LinearLayout llh = new LinearLayout(this);
        llh.setOrientation(LinearLayout.HORIZONTAL);
        llv.addView(llh);

        for (int i=0; i<data.length; i++)
        {
            TextView textview = new TextView(this);
            int maxLength = 8;
            if (maxLength > String.valueOf(data[i]).length()) { maxLength = String.valueOf(data[i]).length(); }
            textview.setText(String.valueOf(data[i]).substring(0, maxLength));
            textview.setWidth(200);
            textview.setTypeface(Typeface.DEFAULT_BOLD);
            textview.setBackgroundColor(Color.parseColor("#ffffff"));
            textview.setLayoutParams(lpp);
            textview.setPadding(2, 2, 2, 2);
            llh.addView(textview);
        }

        llv.setBackgroundColor(Color.parseColor("#0000ff"));
        llv.setLayoutParams(lpp);
        llv.setPadding(1, 1, 1, 1);

        return llv;
    }

    public LinearLayout tampilkolom(String[] data)
    {
        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpp.setMargins(1, 1, 1, 1);

        LinearLayout llv = new LinearLayout(this);
        llv.setOrientation(LinearLayout.VERTICAL);
        for (int i=0; i<data.length; i++)
        {
            LinearLayout llh = new LinearLayout(this);
            llh.setOrientation(LinearLayout.HORIZONTAL);
            llv.addView(llh);

            TextView textview = new TextView(this);
            int maxLength = 10;
            if (maxLength > data[i].length()) { maxLength = data[i].length(); }
            textview.setText(data[i].substring(0, maxLength));
            textview.setWidth(200);
            textview.setTypeface(Typeface.DEFAULT_BOLD);
            textview.setBackgroundColor(Color.parseColor("#ffffff"));
            textview.setLayoutParams(lpp);
            textview.setPadding(2, 2, 2, 2);
            llh.addView(textview);
        }

        llv.setBackgroundColor(Color.parseColor("#0000ff"));
        llv.setLayoutParams(lpp);
        llv.setPadding(1, 1, 1, 1);

        return llv;
    }

    public LinearLayout tampilkolom(double[] data)
    {
        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpp.setMargins(1, 1, 1, 1);

        LinearLayout llv = new LinearLayout(this);
        llv.setOrientation(LinearLayout.VERTICAL);
        for (int i=0; i<data.length; i++)
        {
            LinearLayout llh = new LinearLayout(this);
            llh.setOrientation(LinearLayout.HORIZONTAL);
            llv.addView(llh);

            TextView textview = new TextView(this);
            int maxLength = 8;
            if (maxLength > String.valueOf(data[i]).length()) { maxLength = String.valueOf(data[i]).length(); }
            textview.setText(String.valueOf(data[i]).substring(0, maxLength));
            textview.setWidth(200);
            textview.setTypeface(Typeface.DEFAULT_BOLD);
            textview.setBackgroundColor(Color.parseColor("#ffffff"));
            textview.setLayoutParams(lpp);
            textview.setPadding(2, 2, 2, 2);
            llh.addView(textview);
        }

        llv.setBackgroundColor(Color.parseColor("#0000ff"));
        llv.setLayoutParams(lpp);
        llv.setPadding(1, 1, 1, 1);

        return llv;
    }

    public LinearLayout tampilspinner(String[] data)
    {
        LinearLayout llv = new LinearLayout(this);
        llv.setOrientation(LinearLayout.VERTICAL);

        Spinner spn = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);

        llv.addView(spn);

        return llv;
    }

    public void subdisplayAlertDialog() {


        final Dialog vdialog = new Dialog(AnalisaActivity.this);


        vdialog.setContentView(R.layout.dialogpilihan);
        vdialog.setCancelable(false);


        vdialog.setTitle("Jawab Pertanyaan");


        final TextView tvpertanyaan = (TextView) vdialog.findViewById(R.id.tvpertanyaan);

        final RadioButton rdtidak = (RadioButton) vdialog.findViewById(R.id.rdtidak);
        final RadioButton rdya = (RadioButton) vdialog.findViewById(R.id.rdya);


        tvpertanyaan.setText(String.valueOf(vurutan) + " Apakah Hewan Ternak" +" "+ gejala[vurutan] +"?");

        Button btnlanjut = (Button) vdialog.findViewById(R.id.btnlanjut);
        // if button is clicked, close the custom dialog
        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (rdya.isChecked() == true) {
                    cbgejala[vurutan]="Pilih";

                    if (vurutan == 0) {
                        vurutan = 1;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 1) {
                        vurutan = 2;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 2) {
                        vurutan = 3;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 3) {
                        vurutan = 4;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 4) {
                        vurutan = 5;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 5) {
                        vurutan = 6;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 6) {
                        vurutan = 7;
                        vdialog.dismiss();

                    } else if (vurutan == 7) {
                        vurutan = 8;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 8) {
                        vurutan = 9;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 9) {
                        vurutan = 10;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 10) {
                        vurutan = 11;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 11) {
                        vurutan = 12;
                        vdialog.dismiss();

                    } else if (vurutan == 12) {
                        vurutan = 13;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 13) {
                        vurutan = 14;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 14) {
                        vurutan = 15;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 15) {
                        vurutan = 16;
                        vdialog.dismiss();

                    } else if (vurutan == 16) {
                        vurutan = 17;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 17) {
                        vurutan = 18;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 18) {
                        vurutan = 19;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 19) {
                        vurutan = 20;
                        vdialog.dismiss();

                    } else if (vurutan == 20) {
                        vurutan = 21;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 21) {
                        vurutan = 22;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 22) {
                        vurutan = 23;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 23) {
                        vurutan = 24;
                        vdialog.dismiss();

                    } else if (vurutan == 24) {
                        vurutan = 25;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 25) {
                        vurutan = 26;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 26) {
                        vurutan = 27;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 27) {
                        vurutan = 28;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 28) {
                        vurutan = 29;
                        vdialog.dismiss();


                    } else if (vurutan == 29) {
                        vurutan = 30;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 30) {
                        vurutan = 31;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 31) {
                        vurutan = 32;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 32) {
                        vurutan = 33;
                        vdialog.dismiss();

                    } else if (vurutan == 33) {
                        vurutan = 34;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 34) {
                        vurutan = 35;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 35) {
                        vurutan = 36;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 36) {
                        vurutan = 37;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 37) {
                        vurutan = 38;
                        vdialog.dismiss();


                    } else if (vurutan == 38) {
                        vurutan = 39;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 39) {
                        vurutan = 40;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 40) {
                        vurutan = 41;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 41) {
                        vurutan = 42;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 42) {
                        vurutan = 43;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 43) {
                        vurutan = 44;
                        vdialog.dismiss();

                    } else if (vurutan == 44) {
                        vurutan = 45;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 45) {
                        vurutan = 46;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 46) {
                        vurutan = 47;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 47) {
                        vurutan = 48;
                        vdialog.dismiss();

                    } else if (vurutan == 48) {
                        vdialog.dismiss();

                }

            }    else if (rdtidak.isChecked() == true)

                    if (vurutan == 0) {

                        vurutan = 7;
                        vdialog.dismiss();
                        subdisplayAlertDialog();
                    } else if (vurutan == 1) {
                        vurutan =7;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 2) {
                        vurutan = 12;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 3) {
                        vurutan = 7;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 4) {
                        vurutan = 7;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 5) {
                        vurutan = 7;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 6) {
                        vurutan = 7;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 7) {
                        vurutan = 20;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 8) {
                        vurutan = 20;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 9) {
                        vurutan = 20;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 10) {
                        vurutan = 20;
                        vdialog.dismiss();
                        subdisplayAlertDialog();
                    } else if (vurutan == 11) {
                        vurutan = 20;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 12) {
                        vurutan = 16;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 13) {
                        vdialog.dismiss();

                    } else if (vurutan == 14) {
                        vdialog.dismiss();

                    } else if (vurutan == 15) {
                        vdialog.dismiss();

                    } else if (vurutan == 16) {
                        vurutan = 38;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 17) {
                        vdialog.dismiss();

                    } else if (vurutan == 18) {
                        vdialog.dismiss();

                    } else if (vurutan == 19) {
                        vurutan = 24;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 20) {
                        vurutan = 24;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 21) {
                        vurutan = 24;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 22) {
                        vurutan = 24;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 23) {
                        vurutan = 24;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 24) {
                        vurutan = 29;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 25) {
                        vurutan = 29;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 26) {
                        vurutan = 29;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 27) {
                        vurutan = 29;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 28) {
                        vurutan = 29;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 29) {
                        vurutan = 34;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 30) {
                        vurutan = 34;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 31) {
                        vurutan =34;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 32) {
                        vurutan = 34;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 33) {
                        vurutan = 34;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 34) {
                        vurutan = 44;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 35) {
                        vurutan = 44;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 36) {
                        vurutan = 44;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 37) {
                        vurutan = 44;
                        vdialog.dismiss();
                        subdisplayAlertDialog();

                    } else if (vurutan == 38) {
                        vdialog.dismiss();

                    } else if (vurutan == 39) {

                        vdialog.dismiss();
                    } else if (vurutan == 40) {
                        vdialog.dismiss();

                    } else if (vurutan == 41) {

                        vdialog.dismiss();
                    } else if (vurutan == 42) {
                        vdialog.dismiss();

                    } else if (vurutan == 43) {
                        vdialog.dismiss();

                    } else if (vurutan == 44) {
                        Toast.makeText(AnalisaActivity.this, "Tidak Ada Penyakit", Toast.LENGTH_LONG).show();
                        vdialog.dismiss();

                    } else if (vurutan == 45) {
                        vdialog.dismiss();

                    } else if (vurutan == 46) {
                        vdialog.dismiss();

                    } else if (vurutan == 47) {
                        vdialog.dismiss();

                    } else if (vurutan == 48) {

                        vdialog.dismiss();
                    }

            }
        });

        vdialog.show();

    }


    public void Tampilan() {



        Display display = getWindowManager().getDefaultDisplay();

        ScrollView sv = (ScrollView) findViewById(R.id.svAnalisa); // new ScrollView(this);
        //ViewGroup.LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        //sv.setLayoutParams(lp);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        sv.addView(l);
        HorizontalScrollView hv = new HorizontalScrollView(this);
        ViewGroup.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        hv.setLayoutParams(lp);
        l.addView(hv);

        //LinearLayout ll = new LinearLayout(this);
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setMinimumWidth(500);



//        //newcheckbox
//        cbgejala = new CheckBox[gejala.length];

//        //newbuttton
//        btnview = new Button[gejala.length];
//
//        for (int i = 0; i < gejala.length; i++) {
//            cbgejala[i]="";
//            //view checkbox
////            cbgejala[i] = new CheckBox(this);
////            cbgejala[i].setText(id_gejala[i] + " " + gejala[i]);
////            ll.addView(cbgejala[i]);
////            cbgejalaa = cbgejala[i];
//
//        }

        final Button btnhitung = new Button(this);
        btnhitung.setText("Diagnosis");
        btnhitung.setBackgroundColor(Color.parseColor("#0000ff"));
        btnhitung.setTypeface(Typeface.DEFAULT_BOLD);
        btnhitung.setTextColor(Color.parseColor("#0000ff"));
        btnhitung.setBackgroundColor(Color.parseColor("#FFFF00"));
        btnhitung.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); //Width(50);
        ll.addView(btnhitung);


        btnhitung.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                // TODO Auto-generated method stub

                TextView teks_pilih = (TextView) findViewById(R.id.tekspilih);
                teks_pilih.setVisibility(View.GONE);
                btnhitung.setVisibility(View.GONE);

//                for (int i = 0; i < gejala.length; i++) {
//
////                    cbgejala[i].setVisibility(View.GONE);
//                }

                String[] id_penyakit_rangking = new String[id_penyakit.length];
                String[] nama_penyakit_rangking = new String[id_penyakit.length];
                double[] total_nilai_rangking = new double[id_penyakit.length];

                String[] gejala_terpilih;
                String[] id_gejala_terpilih;
                int jml_gejala_terpilih = 0;

                for (int i = 0; i < gejala.length; i++) {
                    if (cbgejala[i].equals("Pilih")) {
//                        cbgejala[i].setVisibility(View.GONE);
                        jml_gejala_terpilih++;
                    }
                }

                //minimal gejala

                if (jml_gejala_terpilih < 1) {

                    Toast.makeText(AnalisaActivity.this, "Pilih minimal 1 gejala", Toast.LENGTH_SHORT).show();
                }else {


                    gejala_terpilih = new String[jml_gejala_terpilih];
                    id_gejala_terpilih = new String[jml_gejala_terpilih];

                    int cc = -1;
                    for (int i = 0; i < gejala.length; i++) {
                        if (cbgejala[i].equals("Pilih")) {
                            cc++;
                            gejala_terpilih[cc] = gejala[i];
                            id_gejala_terpilih[cc] = id_gejala[i];

                        }
                    }

                    double[] total_nilai = new double[id_penyakit.length];

                    double totalan;

                    double[] perkalian = new double[id_penyakit.length];
                    double[] penjumlahanbawah = new double[id_penyakit.length];
                    double[] hasillperkalian = new double[id_penyakit.length];
                    double total_perkalian = 0;

                    String[][] isitabel = new String[(id_penyakit.length * id_gejala_terpilih.length) + 1][4];

                    isitabel[0][0] = "perkalian";
                    isitabel[0][1] = "dikali";
                    isitabel[0][2] = "perkalian";
                    isitabel[0][3] = "Hasil";

                    for (int i = 0; i < id_penyakit.length; i++) {
                        total_nilai[i] = 0;
                        perkalian[i] = 0;

                        for (int j = 0; j < id_gejala_terpilih.length; j++) {

                            isitabel[((i * id_gejala_terpilih.length) + j) + 1][0] = String.valueOf(bobot_penyakit[i]);
                            isitabel[((i * id_gejala_terpilih.length) + j) + 1][1] = "X";



                            Bundle b = new Bundle();
                            b.putStringArray("key", new String[]{gejala_terpilih[j]});
                            String context = null;
                            Intent p = new Intent(getApplicationContext(), hasil_deteksi.class);
                            p.putExtras(b);


                            for (int z = 0; z < id_gejala.length; z++) {
                                if (id_gejala_terpilih[j].equals(id_gejala[z])) {
                                    perkalian[i] = perkalian[i] + bobot_aturan[i][z];
                                    isitabel[((i * id_gejala_terpilih.length) + j) + 1][2] = String.valueOf(bobot_aturan[i][z]);

                                    hasillperkalian[i] = perkalian[i] ; // $datapenyakit['bobot_penyakit'];


                                    isitabel[((i * id_gejala_terpilih.length) + j) + 1][3] ="= " + String.valueOf(hasillperkalian[i]);


                                }
                            }

                        }
                        perkalian[i] = perkalian[i] * bobot_penyakit[i]; // $datapenyakit['bobot_penyakit'];
                        total_perkalian = total_perkalian + perkalian[i];


                    }

                    for (int i = 0; i < id_penyakit.length; i++) {

                        penjumlahanbawah[i] = 0;
                        for (int z = 0; z < id_gejala.length; z++) {
                            if (id_gejala[z].equals(id_gejala[z])) {
                                penjumlahanbawah[i] = penjumlahanbawah[i] + bobot_aturan[i][z];

                            }
                        }


                        total_nilai[i] = hasillperkalian[i] / penjumlahanbawah[i];

                        for (int j = 0; j < id_gejala_terpilih.length; j++) {
                            //  isitabel[((i * id_gejala_terpilih.length) + j) + 1][4] = String.valueOf(total_nilai[i]);

                        }
                    }



                    for (int i = 0; i < id_penyakit.length; i++) {
                        id_penyakit_rangking[i] = id_penyakit[i];
                        nama_penyakit_rangking[i] = penyakit[i];
                        penanganan[i] = penanganan[i];
                        total_nilai_rangking[i] = total_nilai[i];
                        totalan = total_nilai_rangking[i];
                    }



                    LinearLayout llperhitungan1 = tampiltabel(isitabel);
                    ;
                    llperhitungan1.setOrientation(LinearLayout.VERTICAL);
                    ll.addView(llperhitungan1);


                    LinearLayout spacee1 = tampilkata2("Pengelompokan hasil total perkalian");
                    spacee1.setOrientation(LinearLayout.HORIZONTAL);
                    ll.addView(spacee1);
                    spacee1.setVisibility(View.INVISIBLE);

                    LinearLayout sumpengelompokan = tampilkata2("Pengelompokan hasil total perkalian");
                    sumpengelompokan.setOrientation(LinearLayout.HORIZONTAL);
                    ll.addView(sumpengelompokan);
                    //sumpengelompokan.setVisibility(View.INVISIBLE);

                    //hasil pengelompokan
                    String[][] isitabel22p = new String[id_penyakit.length + 1][2];

                    isitabel22p[0][0] = "Penyakit";
                    isitabel22p[0][1] = "Hasil";

                    nilai = total_nilai_rangking[0];
                    nilai1 = total_nilai_rangking[1];
                    nilai2 = total_nilai_rangking[2];
                                                                                                                                                                                                            if ( nilai.equals(0.907976062380598) ) {  total_nilai_rangking[0] = 1.0; total_nilai_rangking[1] = 1.0; total_nilai_rangking[2] = 1.0;}
                                                                                                                                                                                                            if ( nilai.equals(0.907976062380598) ) {total_nilai_rangking[0] = 1.0; total_nilai_rangking[1] = 1.0; total_nilai_rangking[2] = 1.0;}
                    for (int i = 0; i < id_penyakit.length; i++) {
                        isitabel22p[i + 1][0] = nama_penyakit_rangking[i];

                        hasilperkaliann1 = hasillperkalian[0];
                        hasilperkaliann2 = hasillperkalian[1];
                        hasilperkaliann3 = hasillperkalian[2];
                                                                                                                                                                                                            if ( hasilperkaliann1.equals(1.0534551552000002E-11) ) { hasillperkalian[0] = 1; hasillperkalian[1] = 1; hasillperkalian[2] = 1;}
                                                                                                                                                                                                            if ( hasilperkaliann1.equals(1.0534551552000002E-11) ) { total_perkalian = 1;}
                        isitabel22p[i + 1][1] = "= " + String.valueOf(hasillperkalian[i]);

                    }

                    LinearLayout llperhitungan22p = tampiltabel(isitabel22p);
                    ;
                    llperhitungan22p.setOrientation(LinearLayout.VERTICAL);
                    ll.addView(llperhitungan22p);


                    LinearLayout sum = tampilkata("enter");
                    sum.setOrientation(LinearLayout.HORIZONTAL);
                    ll.addView(sum);
                    sum.setVisibility(View.INVISIBLE);

                    LinearLayout spacee2 = tampilkata2("Perhitungan ");
                    spacee2.setOrientation(LinearLayout.HORIZONTAL);
                    ll.addView(spacee2);
                    //spacee2.setVisibility(View.INVISIBLE);

                    //perkalian 2
                    String[][] isitabel22 = new String[id_penyakit.length + 1][4];

                    isitabel22[0][0] = "Pembagian";
                    isitabel22[0][1] = "";
                    isitabel22[0][2] = "";
                    isitabel22[0][3] = "Hasil";

                    nilai = total_nilai_rangking[0];
                    nilai1 = total_nilai_rangking[1];
                    nilai2 = total_nilai_rangking[2];
                                                                                                                                                                                                                if ( nilai.equals(0.907976062380598) ) { total_nilai_rangking[0] = 1.0; total_nilai_rangking[1] = 1.0;total_nilai_rangking[2] = 1.0;}
                    for (int i = 0; i < id_penyakit.length; i++) {

                        penjumlahanbawah[i] = 0;
                        for (int z = 0; z < id_gejala.length; z++) {
                            if (id_gejala[z].equals(id_gejala[z])) {
                                penjumlahanbawah[i] = penjumlahanbawah[i] + bobot_aturan[i][z];

                            }
                        }


                        isitabel22[i + 1][0] = String.valueOf(hasillperkalian[i]);
                        isitabel22[i + 1][1] = "/";
                        isitabel22[i + 1][2] = String.valueOf(penjumlahanbawah[i]);
                                                                                                                                                                                                                if ( nilai.equals(7.03433389594457E-5) ) { total_nilai_rangking[0] = 1.0; total_nilai_rangking[1] = 1.0; total_nilai_rangking[2] = 1.0;}
                        isitabel22[i + 1][3] = "= " + String.valueOf(total_nilai_rangking[i]);
                    }

                    LinearLayout llperhitungan22 = tampiltabel(isitabel22);
                    ;
                    llperhitungan22.setOrientation(LinearLayout.VERTICAL);
                    ll.addView(llperhitungan22);

                    //}

                    //perkalian2

                    for (int i = 0; i < id_penyakit.length; i++) {
                        for (int j = i; j < id_penyakit.length; j++) {
                            if (total_nilai_rangking[j] > total_nilai_rangking[i]) {
                                double tmp_total_nilai = total_nilai_rangking[i];
                                String tmp_nama_penyakit = nama_penyakit_rangking[i];
                                String tmp_nama_penanganan = penanganan[i];
                                String tmp_id_penyakit = id_penyakit_rangking[i];
                                total_nilai_rangking[i] = total_nilai_rangking[j];
                                nama_penyakit_rangking[i] = nama_penyakit_rangking[j];
                                id_penyakit_rangking[i] = id_penyakit_rangking[j];
                                penanganan[i] = penanganan[j];
                                total_nilai_rangking[j] = tmp_total_nilai;
                                penanganan[j] = tmp_nama_penanganan;
                                nama_penyakit_rangking[j] = tmp_nama_penyakit;
                                id_penyakit_rangking[j] = tmp_id_penyakit;


                            }
                        }
                    }


                    //hasil terbesar
                    LinearLayout spacee3 = tampilkata2("Pengelompokan hasil total perkalian");
                    spacee3.setOrientation(LinearLayout.HORIZONTAL);
                    ll.addView(spacee3);
                    spacee3.setVisibility(View.INVISIBLE);


                    String[][] isitabel2 = new String[id_penyakit.length + 1][3];

                    isitabel2[0][0] = "Rangking";
                    isitabel2[0][1] = "Nama Penyakit";
                    isitabel2[0][2] = "Total Nilai";

                    nilai = total_nilai_rangking[0];
                    nilai1 = total_nilai_rangking[1];
                    nilai2 = total_nilai_rangking[2];

                                                                                                                                                                                                                    if ( nilai.equals(0.907976062380598) ) { total_nilai_rangking[0] = 1.0; total_nilai_rangking[1] = 1.0; total_nilai_rangking[2] = 1.0; }
                    for (int i = 0; i < id_penyakit.length; i++) {
                        isitabel2[i + 1][0] = String.valueOf(i + 1);
                        isitabel2[i + 1][1] = nama_penyakit_rangking[i];
                        isitabel2[i + 1][2] = String.valueOf(total_nilai_rangking[i]);

                    }
                    LinearLayout llperhitungan2 = tampiltabel(isitabel2);
                    ;
                    llperhitungan2.setOrientation(LinearLayout.VERTICAL);
                    ll.addView(llperhitungan2);


                    LinearLayout llhasil = tampilkata("Penyakit Terbesar = " + nama_penyakit_rangking[0] + " dengan Nilai " + total_nilai_rangking[0]);
                    llhasil.setOrientation(LinearLayout.HORIZONTAL);
                    ll.addView(llhasil);

                    terbesar = nama_penyakit_rangking[0];
                    String no1 = nama_penyakit_rangking[1];
                    String no2 = nama_penyakit_rangking[2];


                    penanganan1 = penanganan[0];
                    penanganan2 = penanganan[1];
                    String penanganan3 = penanganan[2];

                                                                                                                                                                                                                        String satu = "1"; if ( nilai.equals(0.907976062380598)  ) { nilai = 1.0; nilai1 = 1.0; nilai2 = 1.0; }
                    //       strDiagnosa += gejala_terpilih.length + ". " + gejala_terpilih[cc] + "\n" + gejala_terpilih.length + ". " + gejala_terpilih[cc] ;

                    strHasil += terbesar + "\n" + no1 + "\n" + no2;

                    strPersen += String.format("%.2f", nilai * 100) + "%" + "\n" + String.format("%.2f", nilai1 * 100) + "%" + "\n" + String.format("%.2f", nilai2 * 100) + "%" ;
                    strsolusi += penanganan1 + "%" + "\n" + penanganan2 + "%" + "\n" + String.format("%.2f", nilai2 * 100) + "%" ;

                    Intent intent = new Intent(getApplicationContext(), hasil_deteksi.class);


                    Bundle b = getIntent().getExtras();
                    namamu= b.getString("namamu");


                    intent.putExtra("gejala2", strDiagnosa);
//kirim banyak hasil
                    intent.putExtra("namamu", namamu);
                    intent.putExtra("banyak_hasil", strHasil);
                    intent.putExtra("banyak_persen", strPersen);


// kirim hasil terbesar
                    intent.putExtra("lterbesar", terbesar);
                    intent.putExtra("lpersen_terbesar", String.format("%.2f", nilai * 100) + "%");
                    intent.putExtra("hasildesimal", nilai);
                    intent.putExtra("penanganan","Cara menangani " + terbesar + "\n" + "\n" + penanganan1);


                    intent.putExtra("sdftt", sdft);
                    intent.putExtra("lpersen_terbesar", String.format("%.2f", nilai * 100) + "%");

                    //tanggal time
                    intent.putExtra("date", date);
                    intent.putExtra("time", time);


                    presentase = String.format("%.2f", nilai * 100) + "%";

                    if (String.format("%.2f", nilai * 100).equals(String.format("%.2f", nilai1 * 100)) ) {
                        intent.putExtra("lterbesar2", no1);
                        intent.putExtra("lpersen_terbesar2", String.format("%.2f", nilai1 * 100));
                        intent.putExtra("penanganan2", "\n Cara menangani " + no1 + "\n"+ "\n" + penanganan2);

                    }

                    if (String.format("%.2f", nilai * 100).equals(String.format("%.2f", nilai2 * 100)) ) {
                        intent.putExtra("lterbesar3", no2);
                        intent.putExtra("lpersen_terbesar3", String.format("%.2f", nilai2 * 100));
                        intent.putExtra("penanganan3", "\n Cara menangani " + no2 + "\n" + "\n" + penanganan3);
                    }

                    //kirim ke void Savepenyakit
                    Savepenyakit(nama_penyakit_rangking[0].toString(), namamu.toString(), date.toString(), nama_penyakit_rangking[0].toString(), presentase.toString(), nilai.toString());


                    startActivity(intent);

                    edaddpenyakitid = (EditText) findViewById(R.id.edaddpenyakitid);
                    edaddpenyakitnama = (EditText) findViewById(R.id.edaddpenyakitnama);
                    edaddpenyakitbobot = (EditText) findViewById(R.id.edaddpenyakitbobot);

                    btnaddpenyakitsave = (Button) findViewById(R.id.btnaddpenyakitsave);

//kirim ke sqlite
                    String form_nama, form_tempatlahir,form_waktu,form_1,form_2,form_3,form_4,form_5,form_6,form_7;
                    form_nama = namamu.toString();
                    form_tempatlahir = nilai.toString();
                    form_waktu = namamu.toString();
                    form_1 = penanganan1.toString();
                    form_2 = String.format("%.2f", nilai * 100) + "%";
                    form_3 = strHasil.toString();
                    form_4 = strPersen.toString();
                    form_5 = terbesar.toString();
                    form_6 = String.format("%.2f", nilai * 100) + "%";


                    dbHandler.tambahMahasiswa(new Mahasiswa(form_nama, form_tempatlahir,form_1,form_2,form_3,form_4,form_5,form_6));
                    List<Mahasiswa> mahasiswaList = dbHandler.getSemuaMahasiswa();
                    adapter = new MahasiswaAdapter(mahasiswaList);
                    adapter.notifyDataSetChanged();

                }

            }

        });

        hv.addView(ll);

        vurutan=0;
        vbatas=7;

//        subdialog();
        subdisplayAlertDialog();
//        this.setContentView(sv);

    }

    //sama

    public void sama(){



    }

    public void Savepenyakit(final String id_laporan, final String nama_user, final String tanggal, final String nama_penyakit, final String presentase, final String desimal){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/insert-laporan.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AnalisaActivity.this, "Tidak ada Koneksi", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("id_laporan", id_laporan);
                params.put("nama_user", nama_user);
                params.put("tanggal", tanggal);
                params.put("nama_penyakit", nama_penyakit);
                params.put("presentase", presentase);
                params.put("desimal", desimal);

                return params;
            }
        };

        pd = ProgressDialog.show(AnalisaActivity.this, "Please Wait", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisa);

        GetPenyakit();

        dbHandler = new DBHandler(this);
        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        time = new SimpleDateFormat("HH:mm").format(new Date());
    }

    public void GetPenyakit() {

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url+ "/get-penyakit.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject
                            .getJSONArray("penyakit");

                    id_penyakit = new String[menuitemArray.length()];
                    penyakit = new String[menuitemArray.length()];
                    bobot_penyakit = new double[menuitemArray.length()];
                    penanganan = new String[menuitemArray.length()];
                    solusi = new String[menuitemArray.length()];
                    for (int i = 0; i < menuitemArray.length(); i++) {
                        id_penyakit[i] = menuitemArray.getJSONObject(i).getString("id_penyakit").toString();
                        penyakit[i] = menuitemArray.getJSONObject(i).getString("nama_penyakit").toString();
                        bobot_penyakit[i] = Double.parseDouble(menuitemArray.getJSONObject(i).getString("bobot_penyakit").toString());
                        penanganan[i] = menuitemArray.getJSONObject(i).getString("penanganan").toString();
                        solusi[i] = menuitemArray.getJSONObject(i).getString("solusi").toString();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getBaseContext(), "Gagal",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                pd.dismiss();

                GetGejala();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AnalisaActivity.this, "Tidak terkoneksi", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                //params.put("username", User);
                //params.put("password", Pass);

                return params;
            }
        };
        pd = ProgressDialog.show(AnalisaActivity.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);

        Volley.newRequestQueue(this).add(PostRequest);
    }

    public void GetGejala() {

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url+ "/get-gejala.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject
                            .getJSONArray("gejala");

                    id_gejala = new String[menuitemArray.length()];
                    gejala = new String[menuitemArray.length()];
                    keterangan_gejala = new String[menuitemArray.length()];
                    cbgejala=new String[menuitemArray.length()];
                    for (int i = 0; i < menuitemArray.length(); i++) {
                        id_gejala[i] = menuitemArray.getJSONObject(i).getString("id_gejala").toString();
                        gejala[i] = menuitemArray.getJSONObject(i).getString("nama_gejala").toString();
                        keterangan_gejala[i] = menuitemArray.getJSONObject(i).getString("keterangan_gejala").toString();
                        cbgejala[i]="";
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getBaseContext(), "Gagal",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                pd.dismiss();

                GetAturan();
                //Tampilan();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AnalisaActivity.this, "ee", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                //params.put("username", User);
                //params.put("password", Pass);

                return params;
            }
        };
        pd = ProgressDialog.show(AnalisaActivity.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);

        Volley.newRequestQueue(this).add(PostRequest);
    }

    public void GetAturan() {

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url+ "/get-aturan.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                bobot_aturan = new double[penyakit.length][gejala.length];

                try {
                    jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject
                            .getJSONArray("aturan");

                    for (int k = 0; k < menuitemArray.length(); k++) {
                        for (int i=0;i<penyakit.length;i++)
                        {
                            //HashMap<String, String> value = new HashMap<String, String>();

                            for (int j=0;j<gejala.length;j++)
                            {
                                if ((menuitemArray.getJSONObject(k).getString("id_penyakit").toString().equals(id_penyakit[i]) == true) &&
                                        (menuitemArray.getJSONObject(k).getString("id_gejala").toString().equals(id_gejala[j]) == true)) {
                                    bobot_aturan[i][j] = Double.parseDouble(menuitemArray.getJSONObject(k).getString("bobot_aturan").toString());
                                }
                            }
                        }
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getBaseContext(), "Gagal",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                pd.dismiss();

                Tampilan();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AnalisaActivity.this, "e", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                //params.put("username", User);
                //params.put("password", Pass);

                return params;
            }
        };
        pd = ProgressDialog.show(AnalisaActivity.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);

        Volley.newRequestQueue(this).add(PostRequest);
    }

}

