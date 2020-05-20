package com.blogspot.svdevs.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int posCountry;
    TextView country,cases,recovered,active,deaths,todayDeaths,todayCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        posCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        country = findViewById(R.id.countryV);
        cases = findViewById(R.id.casesV);
        recovered = findViewById(R.id.recoverV);
        active = findViewById(R.id.activeV);
        deaths = findViewById(R.id.desthsV);
        todayCases = findViewById(R.id.t_cases_v);
        todayDeaths = findViewById(R.id.t_deaths_v);

        country.setText(TrackCountries.countryList.get(posCountry).getCountry());
        cases.setText(TrackCountries.countryList.get(posCountry).getCases());
        recovered.setText(TrackCountries.countryList.get(posCountry).getRecovered());
        active.setText(TrackCountries.countryList.get(posCountry).getActive());
        deaths.setText(TrackCountries.countryList.get(posCountry).getDeaths());
        todayCases.setText(TrackCountries.countryList.get(posCountry).getTodayCases());
        todayDeaths.setText(TrackCountries.countryList.get(posCountry).getTodayDeaths());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
