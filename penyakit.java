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



public class penyakit extends AppCompatActivity {

    String[] array_id_penyakit;
    String[] array_nama_penyakit;
    String[] array_bobot_penyakit;
    String[] array_penyakit;
    String[] array_keterangan;
    String[] array_penanganan;
    ListView listpenyakit;

    Button btnpenyakitadd;

    private JSONObject jObject;
    private String jsonResult ="";
    ProgressDialog pd;
    public static penyakit obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyakit);

        obj = this;

        btnpenyakitadd = (Button) findViewById(R.id.btnpenyakitadd);
        btnpenyakitadd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(penyakit.this, addPenyakit.class);
                startActivity(i);
            }
        });

        RefreshList();

    }

    public void RefreshList() {

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url+ "/get-penyakit.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(penyakit.this, response, Toast.LENGTH_LONG).show();
                try {
                    jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject
                            .getJSONArray("penyakit");

                    array_id_penyakit = new String[menuitemArray.length()];
                    array_nama_penyakit = new String[menuitemArray.length()];
                    array_bobot_penyakit = new String[menuitemArray.length()];
                    array_keterangan = new String[menuitemArray.length()];
                    array_penanganan = new String[menuitemArray.length()];
                    array_penyakit = new String[menuitemArray.length()];
                    for (int i = 0; i < menuitemArray.length(); i++) {
                        array_id_penyakit[i] = menuitemArray.getJSONObject(i).getString("id_penyakit").toString();
                        array_nama_penyakit[i] = menuitemArray.getJSONObject(i).getString("nama_penyakit").toString();
                        array_bobot_penyakit[i] = menuitemArray.getJSONObject(i).getString("bobot_penyakit").toString();
                        array_keterangan[i] = menuitemArray.getJSONObject(i).getString("keterangan").toString();
                        array_penanganan[i] = menuitemArray.getJSONObject(i).getString("penanganan").toString();

                        array_penyakit[i] = menuitemArray.getJSONObject(i).getString("id_penyakit").toString() + ". " + menuitemArray.getJSONObject(i).getString("nama_penyakit").toString() + " (" + menuitemArray.getJSONObject(i).getString("bobot_penyakit").toString() + ") ";
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getBaseContext(), "Gagal",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                listpenyakit = (ListView)findViewById(R.id.listpenyakit);
                listpenyakit.setAdapter(new ArrayAdapter(penyakit.this, android.R.layout.simple_list_item_1, array_penyakit));
                listpenyakit.setSelected(true);
                listpenyakit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        final int posisi = arg2; //.getItemAtPosition(arg2).toString();
                        final CharSequence[] dialogitem = {"Edit", "Delete"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(penyakit.this);
                        builder.setTitle("Pilih ?");
                        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch(item){
                                    case 0 :
                                        Intent i = new Intent(getApplicationContext(), editPenyakit.class);
                                        i.putExtra("id_penyakit", array_id_penyakit[posisi]);
                                        i.putExtra("nama_penyakit", array_nama_penyakit[posisi]);
                                        i.putExtra("bobot_penyakit", array_bobot_penyakit[posisi]);
                                        i.putExtra("keterangan", array_keterangan[posisi]);
                                        i.putExtra("penanganan", array_penanganan[posisi]);
                                        startActivity(i);
                                        break;
                                    case 1 :
                                        Deletepenyakit(array_id_penyakit[posisi]);
                                        //RefreshList();
                                        break;
                                }
                            }
                        });
                        builder.create().show();
                    }});

                ((ArrayAdapter)listpenyakit.getAdapter()).notifyDataSetInvalidated();


                pd.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(penyakit.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
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
        pd = ProgressDialog.show(penyakit.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);

        Volley.newRequestQueue(this).add(PostRequest);
    }

    public void Deletepenyakit(final String id_penyakit){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/delete-penyakit.php?id_penyakit=" + id_penyakit, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.dismiss();

                RefreshList();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(penyakit.this, "error", Toast.LENGTH_SHORT).show();
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

        pd = ProgressDialog.show(penyakit.this, "Please wait", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optioon_penyakit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_penyakit_add:
                Intent i = new Intent(penyakit.this, addPenyakit.class);
                startActivity(i);
                return true;
            case R.id.menu_penyakit_refresh:
                RefreshList();
                return true;
            case R.id.menu_penyakit_exit:
                finish();
                return true;
            case R.id.option_penyakit_refresh:
                RefreshList();
                return true;
        }
        return false;
    }


}