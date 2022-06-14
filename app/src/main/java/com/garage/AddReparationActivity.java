package com.garage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.garage.Models.Reparation;
import com.garage.Utils.DatabaseHelper;

import java.util.Calendar;

public class AddReparationActivity extends AppCompatActivity {

    Button addReparation_btn;

    EditText cout;
    EditText panne;
    DatePicker date;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reparation);

        db = new DatabaseHelper(AddReparationActivity.this);

        if(getIntent().hasExtra("id"))
        {
            Toast.makeText(this,getIntent().getStringExtra("marque")+" "+getIntent().getStringExtra("modele"),Toast.LENGTH_SHORT).show();
        }

        cout = findViewById(R.id.cout);
        panne = findViewById(R.id.panne);
        date = findViewById(R.id.date);

        addReparation_btn = findViewById(R.id.reparer_btn);
        addReparation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReparation();
                Intent intent = new Intent(AddReparationActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });


    }


    public void addReparation()
    {
        int day = date.getDayOfMonth();
        int month = date.getMonth()+1;
        int year =  date.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        //System.out.println(calendar.getTime());
        Reparation reparation = new Reparation(
                0,
                    Integer.parseInt(getIntent().getStringExtra("id")),
                        day+"-"+month+"-"+year,
                            Float.parseFloat(cout.getText().toString()),
                                panne.getText().toString());
        System.out.println(reparation.toString());

        db.addReparation(reparation);


    }
}