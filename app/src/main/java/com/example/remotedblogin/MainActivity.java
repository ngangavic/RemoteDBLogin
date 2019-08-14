package com.example.remotedblogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin, buttonRegister;
    RequestQueue requestQueue;
    String url ="http://yourIp/login.php file location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        requestQueue = Volley.newRequestQueue(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //check if email is empty
    private boolean emptyEmail(String email) {
        return email.isEmpty();
    }

    //check if password is empty
    private boolean emptyPassword(String password) {
        return password.isEmpty();
    }

    //check password length
    private boolean passLength(String password) {
        return password.length() < 4;
    }

    //check if email is valid
    private boolean validEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //login method
    private void login() {
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();
        if (emptyEmail(email)) {
            editTextEmail.requestFocus();
            editTextEmail.setError("Cannot be empty");
        } else if (emptyPassword(password)) {
            editTextPassword.requestFocus();
            editTextPassword.setError("Cannot be empty");
        } else if (!validEmail(email)) {
            editTextEmail.requestFocus();
            editTextEmail.setError("Invalid email");
        } else if (passLength(password)) {
            editTextPassword.requestFocus();
            editTextPassword.setError("Password too short");
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        clear();
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
error.printStackTrace();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("password", password);

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }

    }

    private void clear(){
        editTextEmail.getText().clear();
        editTextPassword.getText().clear();
    }


}
