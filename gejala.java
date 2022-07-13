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

public class gejala extends AppCompatActivity {

    String[] array_id_gejala;
    String[] array_nama_gejala;
    String[] array_keterangan_gejala;
    String[] array_gejala;
    ListView listgejala;

    Button btngejalaadd;

    private JSONObject jObject;
    private String jsonResult ="";
    ProgressDialog pd;
    public static gejala obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala);

        obj = this;

        btngejalaadd = (Button) findViewById(R.id.btngejalaadd);
        btngejalaadd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(gejala.this, addGejala.class);
                startActivity(i);
            }
        });

        RefreshList();
    }

    public void RefreshList() {

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url+ "/get-gejala.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject
                            .getJSONArray("gejala");

                    array_id_gejala = new String[menuitemArray.length()];
                    array_nama_gejala = new String[menuitemArray.length()];
                    array_keterangan_gejala = new String[menuitemArray.length()];
                    array_gejala = new String[menuitemArray.length()];
                    for (int i = 0; i < menuitemArray.length(); i++) {
                        array_id_gejala[i] = menuitemArray.getJSONObject(i).getString("id_gejala").toString();
                        array_nama_gejala[i] = menuitemArray.getJSONObject(i).getString("nama_gejala").toString();
                        array_keterangan_gejala[i] = menuitemArray.getJSONObject(i).getString("keterangan_gejala").toString();
                        array_gejala[i] = menuitemArray.getJSONObject(i).getString("id_gejala").toString() + ". " + menuitemArray.getJSONObject(i).getString("nama_gejala").toString() + " ";
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getBaseContext(), "Gagal",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                listgejala = (ListView)findViewById(R.id.listgejala);
                listgejala.setAdapter(new ArrayAdapter(gejala.this, android.R.layout.simple_list_item_1, array_gejala));
                listgejala.setSelected(true);
                listgejala.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        final int posisi = arg2; //.getItemAtPosition(arg2).toString();
                        final CharSequence[] dialogitem = {"Edit", "Delete"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(gejala.this);
                        builder.setTitle("Pilih ?");
                        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch(item){
                                    case 0 :
                                        Intent i = new Intent(getApplicationContext(), editGejala.class);
                                        i.putExtra("id_gejala", array_id_gejala[posisi]);
                                        i.putExtra("nama_gejala", array_nama_gejala[posisi]);
                                        i.putExtra("keterangan_gejala", array_keterangan_gejala[posisi]);
                                        startActivity(i);
                                        break;
                                    case 1 :
                                        Deletegejala(array_id_gejala[posisi]);
                                        //RefreshList();
                                        break;
                                }
                            }
                        });
                        builder.create().show();
                    }});


                ((ArrayAdapter)listgejala.getAdapter()).notifyDataSetInvalidated();

                pd.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(gejala.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
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
        pd = ProgressDialog.show(gejala.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);

        Volley.newRequestQueue(this).add(PostRequest);
    }

    public void Deletegejala(final String id_gejala){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/delete-gejala.php?id_gejala=" + id_gejala, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // String cek = "Login Sukses";
                // Toast.makeText(
                // getBaseContext(),
                // "Response Server : "+response,
                // Toast.LENGTH_LONG).show();

                pd.dismiss();

                RefreshList();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(gejala.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
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

        pd = ProgressDialog.show(gejala.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optioon_gejala, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_gejala_add:
                Intent i = new Intent(gejala.this, addGejala.class);
                startActivity(i);
                return true;
            case R.id.menu_gejala_refresh:
                RefreshList();
                return true;
            case R.id.menu_gejala_exit:
                finish();
                return true;
            case R.id.option_gejala_refresh:
                RefreshList();
                return true;
        }
        return false;
    }
}