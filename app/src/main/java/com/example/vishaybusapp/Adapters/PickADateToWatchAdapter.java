package com.example.vishaybusapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vishaybusapp.PickADateToWatchActivity;
import com.example.vishaybusapp.R;
import com.example.vishaybusapp.WatchActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class PickADateToWatchAdapter extends RecyclerView.Adapter<PickADateToWatchAdapter.MyViewHolder> {

    String[] dates;
    Context c;
    FirebaseDatabase db;
    DatabaseReference dbReference;
    Calendar cal;

    public PickADateToWatchAdapter(Context c,String[] dates){
        this.c = c;
        this.dates = dates;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(c);
        View view = inf.inflate(R.layout.pick_a_date_to_watch_adapter_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dateTextView.setText(dates[position]);
    }

    @Override
    public int getItemCount() {
        return dates.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dateTextView;
        HashMap map;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cal = GregorianCalendar.getInstance();
            String date = Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + " " + Integer.toString(cal.get(Calendar.MONTH) + 1) + " " + Integer.toString(cal.get(Calendar.YEAR));
            dateTextView = itemView.findViewById(R.id.dateTextView);

            db = FirebaseDatabase.getInstance();
            dbReference = db.getReference(date);

            Button morning = itemView.findViewById(R.id.morningButton);
            morning.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbReference = db.getReference(dateTextView.getText().toString());
                    dbReference.child("Morning").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(!task.isSuccessful()){
                                Log.e("firebase","errorGettingData",task.getException());
                            }else{
                                map = (HashMap) task.getResult().getValue();
                                if(!(map == null)) {
                                    Intent intent = new Intent(c.getApplicationContext(), WatchActivity.class);
                                    intent.putExtra("key", map);
                                    c.startActivity(intent);
                                }else{
                                    Toast.makeText(c, "There is nothing to show", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            });
            Button evening = itemView.findViewById(R.id.eveningButton);
            evening.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbReference = db.getReference(dateTextView.getText().toString());
                   dbReference.child("Evening").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                       @Override
                       public void onComplete(@NonNull Task<DataSnapshot> task) {
                           if(!task.isSuccessful()){
                               Log.e("firebase","errorGettingData",task.getException());
                           }else{
                               map = (HashMap) task.getResult().getValue();
                               if(!(map == null)) {
                                   Intent intent = new Intent(c.getApplicationContext(), WatchActivity.class);
                                   intent.putExtra("key", map);
                                   c.startActivity(intent);
                               }else{
                                   Toast.makeText(c, "There is nothing to show", Toast.LENGTH_SHORT).show();
                               }
                           }
                       }
                   });
                }
            });
        }
    }
}
