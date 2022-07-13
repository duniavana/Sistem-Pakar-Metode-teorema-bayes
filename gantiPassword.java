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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class gantiPassword extends AppCompatActivity {

    EditText edpasswordusername;
    EditText edpasswordold;
    EditText edpasswordnew;
    EditText edpasswordconfirm;
    Button btnpasswordsave;
    private JSONObject jObject;
    private String jsonResult ="";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);

        edpasswordusername = (EditText)findViewById(R.id.edpasswordusername);
        edpasswordold = (EditText)findViewById(R.id.edpasswordold);
        edpasswordnew = (EditText)findViewById(R.id.edpasswordnew);
        edpasswordconfirm = (EditText)findViewById(R.id.edpasswordconfirm);

        edpasswordusername.setText(admin.userlogin);

        btnpasswordsave = (Button)findViewById(R.id.btnpasswordsave);
        btnpasswordsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (edpasswordnew.getText().toString().equals(edpasswordconfirm.getText().toString()) == true) {
                    CekLogin(edpasswordusername.getText().toString(), edpasswordold.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Password Baru Tidak Sama dengan Konfirmasi Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void CekLogin(final String username,final String password){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url+ "/cek-login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jObject = new JSONObject(response);
                    JSONArray menuitemArray = jObject
                            .getJSONArray("login");

                    if (menuitemArray.length() > 0) {
                        pd.dismiss();
                        UpdatePassword(username, edpasswordnew.getText().toString());
                    }
                    else
                    {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Password Lama Salah", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getBaseContext(), "Login Gagal",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(gantiPassword.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        pd = ProgressDialog.show(gantiPassword.this, "Please Wait", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);
    }

    public void UpdatePassword(final String username, final String password){

        StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/update-password.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.dismiss();

                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(gantiPassword.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        pd = ProgressDialog.show(gantiPassword.this, "Please Wait", "Connecting", true);
        pd.setCancelable(true);
        Volley.newRequestQueue(this).add(PostRequest);
    }
}