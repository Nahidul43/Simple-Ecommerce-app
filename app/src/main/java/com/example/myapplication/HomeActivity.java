package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    TextView buyGousto, buyMilk, stockM, stockG, stockMP, stockGP, display;
    int total=0,total1=0;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buyGousto = findViewById(R.id.buyGousto);
        buyMilk = findViewById(R.id.buyMilk);
        stockM = findViewById(R.id.stockM);
        stockG = findViewById(R.id.stockG);
        stockMP = findViewById(R.id.stockMP);
        stockGP = findViewById(R.id.stockGP);
        videoView=findViewById(R.id.videoView);

        Intent intent = getIntent();
        String stock = intent.getStringExtra("stock");
        String stockMilk = intent.getStringExtra("stockMilk");
        String stockPrice = intent.getStringExtra("stockPrice");
        String stockMilkPrice = intent.getStringExtra("stockMilkPrice");

        stockGP.setText("দাম: " + stockPrice + "TK");
        stockMP.setText("দাম: " + stockMilkPrice + "TK");



        int stp = Integer.parseInt(stock);
        RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);
        String url = "https://carefree-straps.000webhostapp.com/demo/calculalate.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response != null){
                            int result = stp - Integer.parseInt(response) ;
                            if(result < 0){
                                stockG.setText("স্টক: Out Off Stock");
                            }else{
                                stockG.setText("স্টক: " + result + "KG");
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", "Error during Volley request", error);
                    }
                });

        queue.add(stringRequest);

        int mst = Integer.parseInt(stockMilk);
        RequestQueue queue1 = Volley.newRequestQueue(HomeActivity.this);
        String url1 = "https://carefree-straps.000webhostapp.com/demo/milk.php";

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response != null){
                            int result1 = mst - Integer.parseInt(response);
                            if(result1 < 0){
                                stockM.setText("স্টক: Out Off Stock");
                            }else{
                                stockM.setText("স্টক: " + result1 + "Liter");
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", "Error during Volley request", error);
                    }
                });

        queue1.add(stringRequest1);

        buyGousto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, cawActivity.class);
                intent.putExtra("product", "meat");
                startActivity(intent);
            }
        });

        buyMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, cawActivity.class);
                intent.putExtra("product", "milk");
                startActivity(intent);
            }
        });



        RequestQueue queue2 = Volley.newRequestQueue(this);
        String url2 = "https://carefree-straps.000webhostapp.com/apps/video.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    for (int x = 0; x < jsonArray.length(); x++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
                        String id = jsonObject.getString("video_id");
                        String videoUrl = "https://carefree-straps.000webhostapp.com/apps/image/" + id + ".mp4";
                        MediaController mediaController = new MediaController(HomeActivity.this);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse(videoUrl));
                        videoView.requestFocus();
                        videoView.start();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonArrayRequest);




















    }
}
