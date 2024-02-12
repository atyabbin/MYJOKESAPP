package com.example.myjokeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView joke;


    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joke=findViewById(R.id.textView2);
        b=findViewById(R.id.button);
        ProgressBar p;
        p=findViewById(R.id.progressBar);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://official-joke-api.appspot.com/random_joke",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    p.setVisibility(View.VISIBLE);

                    String s = response.getString("setup");
                    s = s + "\n\n" + response.getString("punchline");
                    p.setVisibility(View.GONE);
                    joke.setText(s);
                }catch (JSONException e){
                    Toast.makeText(MainActivity.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);


    }
    public void nextjoke(View v){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://official-joke-api.appspot.com/random_joke",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ProgressBar p;
                    p=findViewById(R.id.progressBar);
                    p.setProgress(0);
                    for(int i=0;i<1000;i++){

                    }

                    p.setVisibility(View.VISIBLE);

                    String s = response.getString("setup");
                    s = s + "\n\n" + response.getString("punchline");

                    joke.setText(s);
                    p.setVisibility(View.GONE);
                }catch (JSONException e){
                    Toast.makeText(MainActivity.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}