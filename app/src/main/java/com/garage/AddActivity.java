package com.garage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.garage.Models.Reparation;
import com.garage.Models.Voiture;
import com.garage.Utils.DatabaseHelper;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    Button confirm;
    EditText marque;
    EditText modele;
    EditText matricule;
    EditText telephone;
    EditText proprietaire;
    EditText cout;
    EditText panne;
    DatePicker date;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = new DatabaseHelper(AddActivity.this);

        marque = findViewById(R.id.marque);
        modele = findViewById(R.id.modele);
        matricule = findViewById(R.id.matricule);
        telephone = findViewById(R.id.telephone);
        proprietaire = findViewById(R.id.proprietaire);
       /* cout = findViewById(R.id.cout);
        panne = findViewById(R.id.panne);
        date = findViewById(R.id.date);*/

        confirm = findViewById(R.id.add_btn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVoiture();
                Intent intent = new Intent(AddActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void addVoiture()
    {
        Voiture voiture = new Voiture(0,marque.getText().toString(),modele.getText().toString(),telephone.getText().toString(),matricule.getText().toString(),proprietaire.getText().toString());
       // Reparation reparation = new Reparation(0,voiture,new Date(date.getDayOfMonth(),date.getMonth(),date.getYear()),Float.parseFloat(cout.getText().toString()),panne.getText().toString());
        db.addVoiture(voiture);

    }
}