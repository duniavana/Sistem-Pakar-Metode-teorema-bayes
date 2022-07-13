package com.example.sistempakarkambing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    EditText edloginusername;
    EditText edloginpassword;
    Button btnlogin;
    private JSONObject jObject;
    private String jsonResult ="";
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        edloginusername = (EditText)findViewById(R.id.edloginusername);
        edloginpassword = (EditText)findViewById(R.id.edloginpassword);

        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                RequestLogin(edloginusername.getText().toString(), edloginpassword.getText().toString());
            }
        });
    }

    public void RequestLogin(final String username,final String password) {

        String Keyusername = edloginusername.getText().toString();
        String Keypassword = edloginpassword.getText().toString();

        if (Keyusername.isEmpty() && Keyusername.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

        }

        else if (Keypassword.isEmpty() && Keypassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

        } else {

            StringRequest PostRequest = new StringRequest(Request.Method.POST, MainActivity.url + "/cek-login.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        jObject = new JSONObject(response);
                        JSONArray menuitemArray = jObject
                                .getJSONArray("login");


                        if (menuitemArray.length() > 0) {
                            Intent i = new Intent(login.this, admin.class);
                            startActivity(i);
                            admin.userlogin = edloginusername.getText().toString();
                            //AdminActivity.userlogin = menuitemArray.getJSONObject(0).getString("username").toString();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(getBaseContext(), "Login gagal",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    pd.dismiss();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(login.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    params.put("username", username);
                    params.put("password", password);

                    return params;
                }
            };

            pd = ProgressDialog.show(login.this, "Sedang mengambil data", "Connecting", true);
            pd.setCancelable(true);
            Volley.newRequestQueue(this).add(PostRequest);
        }
    }

    public void checkLogin(View arg0) {

        final String email = edloginusername.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            edloginusername.setError("Invalid Email");
        }

        final String pass = edloginpassword.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            edloginpassword.setError("Password cannot be empty");
        }

        if(isValidEmail(email) && isValidPassword(pass))
        {
            // Validation Completed
        }

    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }
}