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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class addAturan extends AppCompatActivity {


    Button btnaddaturansave;
    EditText edaddaturanid;
    Spinner spnaddaturanidpenyakit;
    Spinner spnaddaturanidgejala;
    EditText edaddaturanbobot;

    String[] array_id_penyakit;
    String[] array_nama_penyakit;
    String[] array_id_gejala;
    String[] array_nama_gejala;
    private JSONObject jObject;
    private String jsonResult ="";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aturan);

        edaddaturanid = (EditText) findViewById(R.id.edaddaturanid);
        spnaddaturanidpenyakit = (Spinner) findViewById(R.id.spnaddaturanidpenyakit);
        spnaddaturanidgejala = (Spinner) findViewById(R.id.spnaddaturanidgejala);
        edaddaturanbobot = (EditText) findViewById(R.id.edaddaturanbobot);

        GetPenyakit();

        btnaddaturansave = (Button) findViewById(R.id.btnaddaturansave);
        btnaddaturansave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SaveAturan(edaddaturanid.getText().toString(), array_id_penyakit[spnaddaturanidpenyakit.getSelectedItemPosition()], array_id_gejala[spnaddaturanidgejala.getSelectedItemPosition()], edaddaturanbobot.getText().toString());
            }

        });

    }

    public void SaveAturan(final String id_aturan, final String id_penyakit, final String id_gejala, final String bobot) {

        String Keyidaturan = edaddaturanid.getText().toString();
        String Keynamapenyakit = edaddaturanbobot.getText().toString();

        if (Keyidaturan.isEmpty() && Keynamapenyakit.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Id Dan Bobot Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

        } else {

            StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/insert-aturan.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    pd.dismiss();

                    Toast.makeText(getApplicationContext(), "Tambah Aturan Berhasil", Toast.LENGTH_LONG).show();
                    aturan.odj.RefreshList();
                    finish();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(addAturan.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    params.put("id_aturan", id_aturan);
                    params.put("id_penyakit", id_penyakit);
                    params.put("id_gejala", id_gejala);
                    params.put("bobot_aturan", bobot);

                    return params;
                }
            };

            pd = ProgressDialog.show(addAturan.this, "Sedang mengambil data", "Connecting", true);
            pd.setCancelable(true);
            Volley.newRequestQueue(this).add(PostRequest);
        }
    }

        public void GetPenyakit () {

            StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/get-penyakit.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        jObject = new JSONObject(response);
                        JSONArray menuitemArray = jObject
                                .getJSONArray("penyakit");

                        array_id_penyakit = new String[menuitemArray.length()];
                        array_nama_penyakit = new String[menuitemArray.length()];
                        for (int i = 0; i < menuitemArray.length(); i++) {
                            array_id_penyakit[i] = menuitemArray.getJSONObject(i).getString("id_penyakit").toString();
                            array_nama_penyakit[i] = menuitemArray.getJSONObject(i).getString("nama_penyakit").toString();
                        }

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(getBaseContext(), "Gagal",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }


                    pd.dismiss();

                    ArrayAdapter<String> adapter_spnaddaturanidpenyakit = new ArrayAdapter<String>(addAturan.this, android.R.layout.simple_spinner_item, array_nama_penyakit);
                    adapter_spnaddaturanidpenyakit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnaddaturanidpenyakit.setAdapter(adapter_spnaddaturanidpenyakit);

                    GetGejala();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(addAturan.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    //params.put("username", User);
                    //params.put("password", Pass);

                    return params;
                }
            };
            pd = ProgressDialog.show(addAturan.this, "Sedang mengambil data", "Connecting", true);
            pd.setCancelable(true);

            Volley.newRequestQueue(this).add(PostRequest);
        }

        public void GetGejala () {

            StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/get-gejala.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        jObject = new JSONObject(response);
                        JSONArray menuitemArray = jObject
                                .getJSONArray("gejala");

                        array_id_gejala = new String[menuitemArray.length()];
                        array_nama_gejala = new String[menuitemArray.length()];
                        for (int i = 0; i < menuitemArray.length(); i++) {
                            array_id_gejala[i] = menuitemArray.getJSONObject(i).getString("id_gejala").toString();
                            array_nama_gejala[i] = menuitemArray.getJSONObject(i).getString("nama_gejala").toString();
                        }

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(getBaseContext(), "Gagal",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    pd.dismiss();

                    ArrayAdapter<String> adapter_spnaddaturanidgejala = new ArrayAdapter<String>(addAturan.this, android.R.layout.simple_spinner_item, array_nama_gejala);
                    adapter_spnaddaturanidgejala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnaddaturanidgejala.setAdapter(adapter_spnaddaturanidgejala);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(addAturan.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    //params.put("username", User);
                    //params.put("password", Pass);

                    return params;
                }
            };
            pd = ProgressDialog.show(addAturan.this, "Sedang mengambil data", "Connecting", true);
            pd.setCancelable(true);

            Volley.newRequestQueue(this).add(PostRequest);
        }

}