package com.garage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.garage.Utils.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    FloatingActionButton add_btn;
    RecyclerView list;
    EditText searchVoiture;

    DatabaseHelper db;
    ArrayList<String> matricules, marques, modeles, proprietaires, telephones;
    ArrayList<Integer> ids;

    VoituresAdapter voituresAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        db = new DatabaseHelper(ViewActivity.this);

        matricules = new ArrayList<String>();
        marques = new ArrayList<String>();
        modeles = new ArrayList<String>();
        telephones = new ArrayList<String>();
        proprietaires = new ArrayList<String>();
        ids = new ArrayList<Integer>();

        list = findViewById(R.id.list_voitures);
        searchVoiture = findViewById(R.id.searchVoiture);
        searchVoiture.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Toast.makeText(ViewActivity.this,"Changed",Toast.LENGTH_SHORT).show();
                FetchVoituesSearch();
                voituresAdapter = new VoituresAdapter(ViewActivity.this, matricules, marques, modeles, proprietaires, telephones, ids);
                voituresAdapter.notifyDataSetChanged();

                list.setAdapter(voituresAdapter);
                list.setLayoutManager(new LinearLayoutManager(ViewActivity.this));

            }
        });

        FetchVoitures();

        voituresAdapter = new VoituresAdapter(ViewActivity.this, matricules, marques, modeles, proprietaires, telephones, ids);
        list.setAdapter(voituresAdapter);
        list.setLayoutManager(new LinearLayoutManager(ViewActivity.this));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewActivity.this, AddActivity.class));
            }
        });

    }


    public void FetchVoituesSearch() {
        ids.clear();
        marques.clear();
        modeles.clear();
        matricules.clear();
        proprietaires.clear();
        telephones.clear();
        Cursor cursor = db.searchVoitures(searchVoiture.getText().toString());
        if (cursor.getCount() == 0) {
            //Toast.makeText(this,"No data",Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                ids.add(cursor.getInt(0));
                marques.add(cursor.getString(1));
                modeles.add(cursor.getString(2));
                matricules.add(cursor.getString(3));
                proprietaires.add(cursor.getString(4));
                telephones.add(cursor.getString(5));
            }
        }
    }

    public void FetchVoitures() {
        Cursor cursor = db.readAllVoitures();
        if (cursor.getCount() == 0) {
            //Toast.makeText(this,"No data",Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                ids.add(cursor.getInt(0));
                marques.add(cursor.getString(1));
                modeles.add(cursor.getString(2));
                matricules.add(cursor.getString(3));
                proprietaires.add(cursor.getString(4));
                telephones.add(cursor.getString(5));
            }
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
                startActivity(new Intent(ViewActivity.this, CalendarActivity.class));
                return true;

            case R.id.cars:
                Toast.makeText(this, "Gérer les voitures", Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}