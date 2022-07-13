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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class aturan extends AppCompatActivity {


    public static aturan odj;
    String[] array_id_aturan;
    String[] array_id_penyakit;
    String[] array_id_gejala;
    String[] array_bobot;
    String[] array_aturan;
    ListView listaturan;

    Button btnaturanadd;

    private JSONObject jObject;
    private String jsonResult ="";
    ProgressDialog pd;
    public static aturan obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aturan);

        obj = this;

        btnaturanadd = (Button) findViewById(R.id.btnaturanadd);
        btnaturanadd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(aturan.this, addAturan.class);
                startActivity(i);
            }
        });

        RefreshList();
    }

    public void RefreshList() {

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url+ "/get-aturan.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject
                            .getJSONArray("aturan");

                    array_id_aturan = new String[menuitemArray.length()];
                    array_id_penyakit = new String[menuitemArray.length()];
                    array_id_gejala = new String[menuitemArray.length()];
                    array_bobot = new String[menuitemArray.length()];
                    array_aturan = new String[menuitemArray.length()];
                    for (int i = 0; i < menuitemArray.length(); i++) {
                        array_id_aturan[i] = menuitemArray.getJSONObject(i).getString("id_aturan").toString();
                        array_id_penyakit[i] = menuitemArray.getJSONObject(i).getString("id_penyakit").toString();
                        array_id_gejala[i] = menuitemArray.getJSONObject(i).getString("id_gejala").toString();
                        array_bobot[i] = menuitemArray.getJSONObject(i).getString("bobot_aturan").toString();
                        array_aturan[i] = menuitemArray.getJSONObject(i).getString("id_aturan").toString() + ". " + menuitemArray.getJSONObject(i).getString("nama_penyakit").toString() + "/" + menuitemArray.getJSONObject(i).getString("nama_gejala").toString() + "\nNilai = " + menuitemArray.getJSONObject(i).getString("bobot_aturan").toString();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getBaseContext(), "Gagal",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                listaturan = (ListView)findViewById(R.id.listaturan);
                listaturan.setAdapter(new ArrayAdapter(aturan.this, android.R.layout.simple_list_item_1, array_aturan));
                listaturan.setSelected(true);
                listaturan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        final int posisi = arg2; //.getItemAtPosition(arg2).toString();
                        final CharSequence[] dialogitem = {"Edit", "Delete"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(aturan.this);
                        builder.setTitle("Pilih ?");
                        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch(item){
                                    case 0 :
                                        Intent i = new Intent(getApplicationContext(), editAturan.class);
                                        i.putExtra("id_aturan", array_id_aturan[posisi]);
                                        i.putExtra("id_penyakit", array_id_penyakit[posisi]);
                                        i.putExtra("id_gejala", array_id_gejala[posisi]);
                                        i.putExtra("bobot_aturan", array_bobot[posisi]);
                                        startActivity(i);
                                        break;
                                    case 1 :
                                        DeleteAturan(array_id_aturan[posisi]);
                                        //RefreshList();
                                        //RefreshList();
                                        break;
                                }
                            }
                        });
                        builder.create().show();
                    }});


                ((ArrayAdapter)listaturan.getAdapter()).notifyDataSetInvalidated();

                pd.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(aturan.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
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
        pd = ProgressDialog.show(aturan.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);

        Volley.newRequestQueue(this).add(PostRequest);
    }

    public void DeleteAturan(final String id_aturan){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/delete-aturan.php?id_aturan=" + id_aturan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.dismiss();

                RefreshList();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(aturan.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
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

        pd = ProgressDialog.show(aturan.this, "Sedang Mengambil Data", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optioon_aturan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_aturan_add:
                Intent i = new Intent(aturan.this, addAturan.class);
                startActivity(i);
                return true;
            case R.id.menu_aturan_refresh:
                RefreshList();
                return true;
            case R.id.menu_aturan_exit:
                finish();
                return true;
            case R.id.option_aturan_refresh:
                RefreshList();
                return true;
        }
        return false;
    }
}