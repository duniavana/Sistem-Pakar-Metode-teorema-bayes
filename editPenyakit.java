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

public class editPenyakit extends AppCompatActivity {

    Button btneditpenyakitsave;
    EditText ededitpenyakitid;
    EditText ededitpenyakitnama;
    EditText ededitpenyakitbobot;
    EditText ededitketerangan;
    EditText ededitpenanganan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_penyakit);

        ededitpenyakitid = (EditText) findViewById(R.id.ededitpenyakitid);
        ededitpenyakitnama = (EditText) findViewById(R.id.ededitpenyakitnama);
        ededitpenyakitbobot = (EditText) findViewById(R.id.ededitpenyakitbobot);
        ededitketerangan = (EditText) findViewById(R.id.ededitketerangan);
        ededitpenanganan = (EditText) findViewById(R.id.ededitpenanganan);


        ededitpenyakitid.setText(getIntent().getStringExtra("id_penyakit"));
        ededitpenyakitnama.setText(getIntent().getStringExtra("nama_penyakit"));
        ededitpenyakitbobot.setText(getIntent().getStringExtra("bobot_penyakit"));
        ededitketerangan.setText(getIntent().getStringExtra("keterangan"));
        ededitpenanganan.setText(getIntent().getStringExtra("penanganan"));

        btneditpenyakitsave = (Button) findViewById(R.id.btneditpenyakitsave);
        // daftarkan even onClick pada btnSimpan
        btneditpenyakitsave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Updatepenyakit(getIntent().getStringExtra("id_penyakit"), ededitpenyakitnama.getText().toString(), ededitpenyakitbobot.getText().toString(), ededitketerangan.getText().toString(), ededitpenanganan.getText().toString());

            }

        });
    }

    public void Updatepenyakit(final String id_penyakit, final String nama_penyakit, final String bobot_penyakit, final String keterangan, final String penanganan){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/update-penyakit.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

                Toast.makeText(getApplicationContext(), "Edit Data Penyakit Berhasil", Toast.LENGTH_LONG).show();
                penyakit.obj.RefreshList();
                finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(editPenyakit.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("id_penyakit", id_penyakit);
                params.put("nama_penyakit", nama_penyakit);
                params.put("bobot_penyakit", bobot_penyakit);
                params.put("keterangan", keterangan);
                params.put("penanganan", penanganan);

                return params;
            }
        };

        pd = ProgressDialog.show(editPenyakit.this, "Please Wait", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);
    }

}