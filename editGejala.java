package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class editGejala extends AppCompatActivity {

    Button btneditgejalasave;
    EditText ededitgejalaid;
    EditText ededitgejalanama;
    EditText ededitgejalaketerangan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gejala);

        ededitgejalaid = (EditText) findViewById(R.id.ededitgejalaid);
        ededitgejalanama = (EditText) findViewById(R.id.ededitgejalanama);
        ededitgejalaketerangan = (EditText) findViewById(R.id.ededitgejalaketerangan);

        ededitgejalaid.setText(getIntent().getStringExtra("id_gejala"));
        ededitgejalanama.setText(getIntent().getStringExtra("nama_gejala"));
        ededitgejalaketerangan.setText(getIntent().getStringExtra("keterangan_gejala"));

        btneditgejalasave = (Button) findViewById(R.id.btneditgejalasave);
        // daftarkan even onClick pada btnSimpan
        btneditgejalasave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Updategejala(getIntent().getStringExtra("id_gejala"), ededitgejalanama.getText().toString(), ededitgejalaketerangan.getText().toString());
            }

        });
    }

    public void Updategejala(final String id_gejala, final String nama_gejala, final String keterangan_gejala){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/update-gejala.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

                Toast.makeText(getApplicationContext(), "Edit Gejala Berhasil", Toast.LENGTH_LONG).show();
                gejala.obj.RefreshList();
                finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(editGejala.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("id_gejala", id_gejala);
                params.put("nama_gejala", nama_gejala);
                params.put("keterangan_gejala", keterangan_gejala);

                return params;
            }
        };

        pd = ProgressDialog.show(editGejala.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);
    }
}