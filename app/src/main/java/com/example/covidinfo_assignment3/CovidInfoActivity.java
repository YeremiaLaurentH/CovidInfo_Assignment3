package com.example.covidinfo_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidInfoActivity extends AppCompatActivity {
    TextView totalCases;
    TextView totalRecovered;
    TextView totalDeath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalCases = findViewById(R.id.total_cases);
        totalRecovered = findViewById(R.id.total_recover);
        totalDeath = findViewById(R.id.total_deaths);
        getAllInfoCovid();
    }

    private void getAllInfoCovid() {
        Call<List<Covid>> call = RetrofitClient.getInstance().myAPI().getAllCovidInfo();
        call.enqueue(new Callback<List<Covid>>() {
            @Override
            public void onResponse(Call<List<Covid>> call, Response<List<Covid>> response) {
                System.out.println("response: " + response.toString());
                List<Covid> covidList = response.body();

                System.out.println("Cases: " + covidList.get(0).getCasses());
                System.out.println("Recovered: " + covidList.get(0).getRecovered());
                System.out.println("Death: " + covidList.get(0).getDeaths());

                totalCases.setText(covidList.get(0).getCasses());
                totalRecovered.setText(covidList.get(0).getRecovered());
                totalDeath.setText(covidList.get(0).getDeaths());
            }

            @Override
            public void onFailure(Call<List<Covid>> call, Throwable t) {
                System.out.println("Failed Data: " + t.fillInStackTrace());
            }
        });
    }
}