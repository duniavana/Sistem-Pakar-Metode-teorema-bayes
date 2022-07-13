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

public class addGejala extends AppCompatActivity {

    Button btnaddgejalasave;
    EditText edaddgejalaid;
    EditText edaddgejalanama;
    EditText edaddgejalaketerangan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gejala);

        edaddgejalaid = (EditText) findViewById(R.id.edaddgejalaid);
        edaddgejalanama = (EditText) findViewById(R.id.edaddgejalanama);
        edaddgejalaketerangan = (EditText) findViewById(R.id.edaddgejalaketerangan);

        btnaddgejalasave = (Button) findViewById(R.id.btnaddgejalasave);
        btnaddgejalasave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Savegejala(edaddgejalaid.getText().toString(), edaddgejalanama.getText().toString(), edaddgejalaketerangan.getText().toString());
            }

        });
    }

    public void Savegejala(final String id_gejala, final String nama_gejala, final String keterangan_gejala) {

        String Keyidgejala = edaddgejalaid.getText().toString();

        if (Keyidgejala.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Id Gejala Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

        } else {
            StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/insert-gejala.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.dismiss();

                    Toast.makeText(getApplicationContext(), "Tambah Gejala Berhasil", Toast.LENGTH_LONG).show();
                    gejala.obj.RefreshList();
                    finish();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(addGejala.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    params.put("id_gejala", id_gejala);
                    params.put("nama_gejala", nama_gejala);
                    params.put("keterangan_gejala", keterangan_gejala);

                    return params;
                }
            };

            pd = ProgressDialog.show(addGejala.this, "Sedang Mengambil Data", "Connecting", true);
            pd.setCancelable(true);
            Volley.newRequestQueue(this).add(PostRequest);
        }
    }
}