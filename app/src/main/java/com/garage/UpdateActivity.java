package com.garage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.garage.Models.Voiture;
import com.garage.Utils.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    Button confirm;
    Button supprimer;
    Button reparer;
    EditText marqueEdit;
    EditText modeleEdit;
    EditText matriculeEdit;
    EditText proprietaireEdit;
    EditText telephoneEdit;
    EditText idVoiture;

    String id,marque,modele,matricule,proprietaire,telephone;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        db = new DatabaseHelper(UpdateActivity.this);

        idVoiture = findViewById(R.id.idVoiture);
        marqueEdit = findViewById(R.id.marqueEdit);
        modeleEdit = findViewById(R.id.modeleEdit);
        matriculeEdit = findViewById(R.id.matriculeEdit);
        proprietaireEdit = findViewById(R.id.proprietaireEdit);
        telephoneEdit = findViewById(R.id.telephoneEdit);

        confirm = findViewById(R.id.edit_btn);
        supprimer = findViewById(R.id.delete_btn);
        reparer = findViewById(R.id.reparer_btn);

        reparer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this,AddReparationActivity.class);
                intent.putExtra("id",idVoiture.getText().toString());
                intent.putExtra("marque",marqueEdit.getText().toString());
                intent.putExtra("modele",modeleEdit.getText().toString());
                startActivity(intent);

            }
        });


        getAndSetIntentData();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateVoiture();
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
            }
        });

    }

    public void confirmDelete()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Suppression");
        builder.setMessage("Etes-vous sur de vouloir supprimer cette voiture ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                supprimerVoiture();
            }
        });
        //builder.setNegativeButton("Non");
        builder.show();

    }


    public void supprimerVoiture()
    {
        db.supprimerVoiture(idVoiture.getText().toString());
        Intent intent = new Intent(UpdateActivity.this, ViewActivity.class);
        startActivity(intent);
    }

    public void updateVoiture()
    {
        Voiture voiture = new Voiture(Integer.parseInt(idVoiture.getText().toString()),marqueEdit.getText().toString(),modeleEdit.getText().toString(),telephoneEdit.getText().toString(),matriculeEdit.getText().toString(),proprietaireEdit.getText().toString());
        db.updateVoiture(voiture);
        Intent intent = new Intent(UpdateActivity.this, ViewActivity.class);
        startActivity(intent);

    }

    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id"))
        {
            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            marque = getIntent().getStringExtra("marque");
            modele = getIntent().getStringExtra("modele");
            matricule = getIntent().getStringExtra("matricule");
            proprietaire = getIntent().getStringExtra("proprietaire");
            telephone = getIntent().getStringExtra("telephone");
            //Setting data

            idVoiture.setText(id);
            marqueEdit.setText(marque);
            modeleEdit.setText(modele);
            matriculeEdit.setText(matricule);
            proprietaireEdit.setText(proprietaire);
            telephoneEdit.setText(telephone);

        }
        else
        {
            Toast.makeText(this,"No data",Toast.LENGTH_LONG).show();
        }
    }
}