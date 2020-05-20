package com.blogspot.svdevs.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView cases,recovered,deaths,active,affectedC,critical,todayCases,todayDeaths;
    PieChart pieChart;
    Button trackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cases = findViewById(R.id.tv_cases);
        recovered = findViewById(R.id.tv_recovered);
        deaths = findViewById(R.id.tv_death);
        active = findViewById(R.id.tv_active);
        affectedC = findViewById(R.id.tv_countries);
        critical = findViewById(R.id.tv_critical);
        todayCases = findViewById(R.id.tv_tcases);
        todayDeaths = findViewById(R.id.tv_tdeaths);
        pieChart = findViewById(R.id.piechart);
        trackBtn = findViewById(R.id.btnTrack);
        
        trackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackCountries();
            }
        });

        fetchInfo();
    }

    private void trackCountries() {
        Intent track  = new Intent(MainActivity.this,TrackCountries.class);
        startActivity(track);
    }

    private void fetchInfo() {

        final String  url = "https://corona.lmao.ninja/v2/all";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response.toString());

                            cases.setText(object.getString("cases"));
                            recovered.setText(object.getString("recovered"));
                            deaths.setText(object.getString("deaths"));
                            active.setText(object.getString("active"));
                            critical.setText(object.getString("critical"));
                            affectedC.setText(object.getString("affectedCountries"));
                            todayCases.setText(object.getString("todayCases"));
                            todayDeaths.setText(object.getString("todayDeaths"));

                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(cases.getText().toString()), Color.parseColor("#531CB3")));
                            pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#00FF00")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(deaths.getText().toString()), Color.parseColor("#D00000")));
                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()), Color.parseColor("#48ACF0")));
                            pieChart.startAnimation();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
    
    
}
