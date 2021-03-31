package com.example.vishaybusapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vishaybusapp.Adapters.PickADateToWatchAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class PickADateToWatchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String[] dates;
    LinearLayoutManager llm;
    PickADateToWatchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_a_date_to_watch);

        fillDates(); //Fills the dates array with dates from 30 days ago untill now.

        recyclerView = findViewById(R.id.RecyclerView);
        llm = new LinearLayoutManager(this);
        adapter = new PickADateToWatchAdapter(this,dates);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(llm);

        {//Action Bar Settings
            getSupportActionBar().setTitle("בחר תאריך לצפייה");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    private void fillDates() {
        dates = new String[30];
        Calendar cal = GregorianCalendar.getInstance();
        dates[0] = Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + " " + Integer.toString(cal.get(Calendar.MONTH) + 1) + " " + Integer.toString(cal.get(Calendar.YEAR));
        for(int i = 1 ; i < 30 ; i++){
            cal.add( Calendar.DAY_OF_YEAR, -1);
            String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + " " + Integer.toString(cal.get(Calendar.MONTH)+1) + " " + Integer.toString(cal.get(Calendar.YEAR));
            dates[i] = day.toString();
        }
    }
}