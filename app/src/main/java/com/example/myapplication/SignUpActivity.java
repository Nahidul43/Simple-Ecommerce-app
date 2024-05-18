package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText edName,edEmail,edMobile,edPassword;
   TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edMobile = findViewById(R.id.edMobile);
        edPassword = findViewById(R.id.edPassword);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(view -> {
            String name = edName.getText().toString();
            String email = edEmail.getText().toString();
            String mobile = edMobile.getText().toString();
            String pass = edPassword.getText().toString();
            if (!name.isEmpty() && !mobile.isEmpty() && !pass.isEmpty()){
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);


                String url = "https://carefree-straps.000webhostapp.com/demo/signup.php?n="+name+"&&e="+email+"&&m="+mobile+"&&p="+pass;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        response -> {
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            Toast.makeText(SignUpActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();


                        },
                        error -> {

                            Log.e("VolleyError", "Error during Volley request", error);

                        });


                queue.add(stringRequest);
            }else{
                Toast.makeText(SignUpActivity.this, "Input All Data", Toast.LENGTH_SHORT).show();
            }


        });



    }
}