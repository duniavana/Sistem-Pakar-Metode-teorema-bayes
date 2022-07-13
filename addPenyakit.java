package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class addPenyakit extends AppCompatActivity {

    Button btnaddpenyakitsave;
    EditText edaddpenyakitid;
    EditText edaddpenyakitnama;
    EditText edaddpenyakitbobot;
    ProgressDialog pd;


    EditText edaddketerangan;
    EditText edaddpenanganan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_penyakit);

        edaddpenyakitid = (EditText) findViewById(R.id.edaddpenyakitid);
        edaddpenyakitnama = (EditText) findViewById(R.id.edaddpenyakitnama);
        edaddpenyakitbobot = (EditText) findViewById(R.id.edaddpenyakitbobot);
        edaddketerangan = (EditText) findViewById(R.id.edaddketerangan);
        edaddpenanganan = (EditText) findViewById(R.id.edaddpenanganan);

        btnaddpenyakitsave = (Button) findViewById(R.id.btnaddpenyakitsave);

        btnaddpenyakitsave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Savepenyakit(edaddpenyakitid.getText().toString(), edaddpenyakitnama.getText().toString(), edaddpenyakitbobot.getText().toString(), edaddketerangan.getText().toString(), edaddpenanganan.getText().toString());
            }

        });
    }

    public void Savepenyakit(final String id_penyakit, final String nama_penyakit, final String bobot_penyakit, final String keterangan, final String penanganan) {

        String Keyidpenyakit = edaddpenyakitid.getText().toString();

        if (Keyidpenyakit.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Id Penyakit Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

        } else {
            StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/insert-penyakit.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.dismiss();

                    Toast.makeText(getApplicationContext(), "Tambah Data Berhasil", Toast.LENGTH_LONG).show();
                    penyakit.obj.RefreshList();
                    finish();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(addPenyakit.this, "Tidak ada Koneksi", Toast.LENGTH_SHORT).show();
                        }
                    }


            ) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    params.put("id_penyakit", id_penyakit);
                    params.put("nama_penyakit", nama_penyakit);
                    params.put("bobot_penyakit", bobot_penyakit);
                    params.put("keterangan", keterangan);
                    params.put("penanganan", penanganan);

                    return params;
                }
            };

            pd = ProgressDialog.show(addPenyakit.this, "Please Wait", "Connecting", true);
            pd.setCancelable(true);
            Volley.newRequestQueue(this).add(PostRequest);
        }
    }
}