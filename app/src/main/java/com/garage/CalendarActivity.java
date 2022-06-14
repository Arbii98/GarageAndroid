package com.garage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.garage.Utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    DatabaseHelper db;

    CalendarView myCalendar;
    RecyclerView listReparations;

    ArrayList<String> couts, pannes, dates, matricules, marques, modeles, proprietaires, telephones;

    ReparationAdapter reparationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        couts = new ArrayList<String>();
        pannes = new ArrayList<String>();
        dates = new ArrayList<String>();
        matricules = new ArrayList<String>();
        marques = new ArrayList<String>();
        modeles = new ArrayList<String>();
        proprietaires = new ArrayList<String>();
        telephones = new ArrayList<String>();

        db = new DatabaseHelper(this);

        listReparations = findViewById(R.id.list_reparations);
        myCalendar = findViewById(R.id.myCalendar);
        myCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int annee, int mois, int jour) {
                mois++;
                String date = jour + "-" + mois + "-" + annee;
                System.out.println("Clicked date : " + date);
                GetReparationsByDate(date);
                ShowData();
                reparationAdapter = new ReparationAdapter(CalendarActivity.this, couts, pannes, dates, matricules, marques, modeles, proprietaires, telephones);
                //reparationAdapter.notifyDataSetChanged();

                listReparations.setAdapter(reparationAdapter);
                listReparations.setLayoutManager(new LinearLayoutManager(CalendarActivity.this));


            }
        });

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        GetReparationsByDate(day+"-"+month+"-"+year);
        ShowData();
        reparationAdapter = new ReparationAdapter(CalendarActivity.this, couts, pannes, dates, matricules, marques, modeles, proprietaires, telephones);

        listReparations.setAdapter(reparationAdapter);
        listReparations.setLayoutManager(new LinearLayoutManager(CalendarActivity.this));

    }

    public void GetReparationsByDate(String date) {
        couts.clear();
        pannes.clear();
        dates.clear();
        matricules.clear();
        marques.clear();
        modeles.clear();
        proprietaires.clear();
        telephones.clear();
        Cursor cursor = db.searchReparation(date);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Pas de reparations", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                couts.add(cursor.getString(3));
                pannes.add(cursor.getString(4));
                dates.add(cursor.getString(2));
                matricules.add(cursor.getString(6));
                marques.add(cursor.getString(7));
                modeles.add(cursor.getString(8));
                proprietaires.add(cursor.getString(9));
                telephones.add(cursor.getString(10));
            }
        }
    }

    public void ShowData() {
        for (int i = 0; i < couts.size(); i++) {
            System.out.println("Reparation : {Cout :" + couts.get(i) + ", Panne :" + pannes.get(i) + ", Date :" + dates.get(i) + ", Matricule :" + matricules.get(i) + ", Marque :" + marques.get(i) + ", Modele :" + modeles.get(i) + ", Proprietaire :" + proprietaires.get(i) + ", Telephone :" + telephones.get(i) + "}");
        }
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
                Toast.makeText(this, "Gérer les réparations", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.cars:
                startActivity(new Intent(CalendarActivity.this, ViewActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}