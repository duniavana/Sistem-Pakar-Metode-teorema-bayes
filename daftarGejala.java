package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class daftarGejala extends AppCompatActivity {

    String[] array_id_gejala;
    String[] array_nama_gejala;
    String[] array_keterangan_gejala;
    String[] array_penyakit;
    ListView listpenyakit;

    private JSONObject jObject;
    private String jsonResult ="";
    ProgressDialog pd;
    public static daftarGejala obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_gejala);

        obj = this;

        RefreshList();
    }

    public void RefreshList() {

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url+ "/get-gejala.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(penyakit.this, response, Toast.LENGTH_LONG).show();
                try {
                    jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject
                            .getJSONArray("gejala");

                    array_id_gejala = new String[menuitemArray.length()];
                    array_nama_gejala = new String[menuitemArray.length()];
                    array_keterangan_gejala = new String[menuitemArray.length()];
                    array_penyakit = new String[menuitemArray.length()];
                    for (int i = 0; i < menuitemArray.length(); i++) {
                        array_id_gejala[i] = menuitemArray.getJSONObject(i).getString("id_gejala").toString();
                        array_nama_gejala[i] = menuitemArray.getJSONObject(i).getString("nama_gejala").toString();
                        array_keterangan_gejala[i] = menuitemArray.getJSONObject(i).getString("keterangan_gejala").toString();


                        array_penyakit[i] = menuitemArray.getJSONObject(i).getString("nama_gejala").toString();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getBaseContext(), "Gagal",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                listpenyakit = (ListView)findViewById(R.id.listpenyakit);
                listpenyakit.setAdapter(new ArrayAdapter(daftarGejala.this, android.R.layout.simple_list_item_1, array_penyakit));
                listpenyakit.setSelected(true);
                listpenyakit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        final int posisi = arg2; //.getItemAtPosition(arg2).toString();

                        Intent i = new Intent(getApplicationContext(), daftarGejalaDetail.class);
                        i.putExtra("id_gejala", array_id_gejala[posisi]);
                        i.putExtra("nama_gejala", array_nama_gejala[posisi]);
                        i.putExtra("keterangan_gejala", array_keterangan_gejala[posisi]);
                        startActivity(i);


                    }});

                ((ArrayAdapter)listpenyakit.getAdapter()).notifyDataSetInvalidated();


                pd.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(daftarGejala.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
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
        pd = ProgressDialog.show(daftarGejala.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);

        Volley.newRequestQueue(this).add(PostRequest);
    }

    public void Deletepenyakit(final String id_gejala){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/delete-penyakit.php?id_gejala=" + id_gejala, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.dismiss();

                RefreshList();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(daftarGejala.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                //params.put("username", username);
                //params.put("password", password);

                return params;
            }
        };

        pd = ProgressDialog.show(daftarGejala.this, "Please wait", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optioon_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.option_utama_refresh:
                RefreshList();
                return true;
        }
        return false;
    }
}