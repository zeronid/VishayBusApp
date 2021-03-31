package com.example.vishaybusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.vishaybusapp.Adapters.PickADateToWatchAdapter;
import com.example.vishaybusapp.Adapters.WatchActivityAdapter;

import java.util.HashMap;

public class WatchActivity extends AppCompatActivity {

    RecyclerView recView;
    LinearLayoutManager llm;
    WatchActivityAdapter adapter;
    HashMap<String,HashMap<String,String>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);

        Intent intent = getIntent();
        map = (HashMap<String,HashMap<String,String>>) intent.getSerializableExtra("key");

        recView = findViewById(R.id.recyclerView);
        llm = new LinearLayoutManager(this);
        adapter = new WatchActivityAdapter(this,map);
        recView.setAdapter(adapter);
        recView.setLayoutManager(llm);
    }
}