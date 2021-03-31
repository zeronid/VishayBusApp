package com.example.vishaybusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class InputActivity extends AppCompatActivity {

    Button mainButton,secondaryButton,otherButton,addButton; //The top three buttons
    int pressedButton = 0; //Holds the position of the button that is currently pressed (0,1,2 -> from right to left
    EditText busNumber,lineNumber;
    FirebaseDatabase db;
    DatabaseReference dbReference;
    Calendar cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        mainButton = findViewById(R.id.optionMainButton);
        secondaryButton = findViewById(R.id.optionSecondaryButton);
        otherButton = findViewById(R.id.optionOtherButton);
        addButton = findViewById(R.id.button4);
        busNumber = findViewById(R.id.busNumberEditText);
        lineNumber = findViewById(R.id.lineNumberEditText);

        getSupportActionBar().setTitle("Vishay");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cal = GregorianCalendar.getInstance();
        String date = cal.get(Calendar.DAY_OF_MONTH) + " " + Integer.toString(cal.get(Calendar.MONTH) + 1) + " " + cal.get(Calendar.YEAR);

        db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(date);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void changeBusPriority(View view) {
        switch(view.getId()) {
            case R.id.optionMainButton:
                mainButton.setBackgroundColor(getResources().getColor(R.color.primary_color));
                mainButton.setTextColor(getResources().getColor(R.color.white));
                secondaryButton.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                secondaryButton.setTextColor(getResources().getColor(R.color.black));
                otherButton.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                otherButton.setTextColor(getResources().getColor(R.color.black));
                pressedButton = 0;
                break;
            case R.id.optionSecondaryButton:
                mainButton.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                mainButton.setTextColor(getResources().getColor(R.color.black));
                secondaryButton.setBackgroundColor(getResources().getColor(R.color.primary_color));
                secondaryButton.setTextColor(getResources().getColor(R.color.white));
                otherButton.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                otherButton.setTextColor(getResources().getColor(R.color.black));
                pressedButton = 1;
                break;
            case R.id.optionOtherButton:
                mainButton.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                mainButton.setTextColor(getResources().getColor(R.color.black));
                secondaryButton.setBackgroundColor(getResources().getColor(R.color.secondary_color));
                secondaryButton.setTextColor(getResources().getColor(R.color.black));
                otherButton.setBackgroundColor(getResources().getColor(R.color.primary_color));
                otherButton.setTextColor(getResources().getColor(R.color.white));
                pressedButton = 2;
                break;
        }
    }

    public void add(View view) {
        if(!TextUtils.isEmpty(busNumber.getText()) && !TextUtils.isEmpty(lineNumber.getText())){
            int hour = cal.get(Calendar.HOUR_OF_DAY);
                if(hour <= 12){ //Morning time
                    dbReference.child("Morning").child(busNumber.getText().toString()).child("Time").setValue(Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes());
                    dbReference.child("Morning").child(busNumber.getText().toString()).child("Main or secondary").setValue(Integer.toString(pressedButton));
                    dbReference.child("Morning").child(busNumber.getText().toString()).child("Line number").setValue(lineNumber.getText().toString());
                    Toast.makeText(this, "Entry added", Toast.LENGTH_SHORT).show();
                }else{
                    dbReference.child("Evening").child(busNumber.getText().toString()).child("Time").setValue(Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes());
                    dbReference.child("Evening").child(busNumber.getText().toString()).child("Main or secondary").setValue(Integer.toString(pressedButton));
                    dbReference.child("Evening").child(busNumber.getText().toString()).child("Line number").setValue(lineNumber.getText().toString());
                    Toast.makeText(this, "Entry added", Toast.LENGTH_SHORT).show();
                }
        }else{
            Toast.makeText(this, "Please input bus number and line number.", Toast.LENGTH_SHORT).show();
        }
    }
}