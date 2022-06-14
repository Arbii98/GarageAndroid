package com.garage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.garage.Utils.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    Button gestionVoitures;
    Button buttonReparations;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, CalendarActivity.class));
        gestionVoitures = findViewById(R.id.buttonVoitures);
        gestionVoitures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
            }
        });

        buttonReparations = findViewById(R.id.buttonReparations);
        buttonReparations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        db = new DatabaseHelper(this);
        //db.supprimerReparations();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.repair:
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
                return true;

            case R.id.cars:
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}