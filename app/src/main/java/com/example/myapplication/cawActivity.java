package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

public class cawActivity extends AppCompatActivity {

    TextInputEditText quantity,edAdress,edMobile,edDelivery;
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caw);

        quantity=findViewById(R.id.quantity);
        edAdress=findViewById(R.id.edAdress);
        edMobile=findViewById(R.id.edMobile);
        edDelivery=findViewById(R.id.edDelivery);
        submit=findViewById(R.id.submit);

Intent intent=getIntent();
String product=intent.getStringExtra("product");

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String Quantity = quantity.getText().toString();
        String Adress = edAdress.getText().toString();
        String Mobile = edMobile.getText().toString();
        String Delivery = edDelivery.getText().toString();
        if (!Quantity.isEmpty() && !Adress.isEmpty() && !Mobile.isEmpty() && !Delivery.isEmpty()) {
            RequestQueue queue = Volley.newRequestQueue(cawActivity.this);


            String url = "https://carefree-straps.000webhostapp.com/demo/customer.php?n=" + Quantity + "&&e=" + Adress + "&&m=" + Mobile + "&&p=" + Delivery + "&&k=" + product;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
//                        startActivity(new Intent(cawActivity.this, HomeActivity.class));
                        Toast.makeText(cawActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();

                        finish();
                    },
                    error -> {

                        Log.e("VolleyError", "Error during Volley request", error);

                    });


            queue.add(stringRequest);
        } else {
            Toast.makeText(cawActivity.this, "Input All Data", Toast.LENGTH_SHORT).show();
        }


    }
});



    }
}