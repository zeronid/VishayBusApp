package com.example.vishaybusapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vishaybusapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class WatchActivityAdapter extends  RecyclerView.Adapter<WatchActivityAdapter.MyViewHolder>{

    HashMap map;
    Context c;
    ArrayList arrayKeys;

    public WatchActivityAdapter(Context c,HashMap values){
        this.c = c;
        this.map = values;
        fillKeys();
    }

    private void fillKeys() {
        Set keys = map.keySet();
        arrayKeys = new ArrayList(keys);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(c);
        View view = inf.inflate(R.layout.watch_activity_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.busNumber.setText(arrayKeys.get(position).toString());
        HashMap<String,String>  valuesMap = (HashMap<String,String>) map.get(arrayKeys.get(position));
        holder.time.setText(valuesMap.get("Time"));
        if(valuesMap.get("Main or secondary").equals("0")){
            holder.priority.setText("עיקרי");
        }else{
            holder.priority.setText("משני");
        }
        holder.lineNumber.setText(valuesMap.get("Line number"));
    }

    @Override
    public int getItemCount() {
        return arrayKeys.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView busNumber,lineNumber,time,priority;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            busNumber = itemView.findViewById(R.id.BusNumberTextView);
            lineNumber = itemView.findViewById(R.id.lineNumberTextView);
            time = itemView.findViewById(R.id.timeTextView);
            priority = itemView.findViewById(R.id.priorityTextView);
        }
    }

}
