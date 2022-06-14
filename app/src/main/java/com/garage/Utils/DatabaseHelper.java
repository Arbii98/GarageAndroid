package com.garage.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.garage.Models.Reparation;
import com.garage.Models.Voiture;

import java.sql.Connection;


public class DatabaseHelper  extends SQLiteOpenHelper {
    Context context;

    public DatabaseHelper(Context context)
    {
        super(context,"garage",null,1);
        this.context= context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table voiture (id integer PRIMARY KEY AUTOINCREMENT, marque text, modele text, matricule text, proprietaire text,telephone text)");
        db.execSQL("create table equipier (id integer PRIMARY KEY AUTOINCREMENT, nom text, prenom text, telephone text, email text)");
        db.execSQL("create table reparation (id integer PRIMARY KEY AUTOINCREMENT, id_voiture integer, date text, cout text, panne text, done integer)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists voiture");
        db.execSQL("drop table if exists equipier");
        db.execSQL("drop table if exists reparation");
        onCreate(db);
    }

    public void addVoiture(Voiture voiture)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("marque",voiture.getMarque());
        cv.put("modele",voiture.getModele());
        cv.put("matricule",voiture.getMatricule());
        cv.put("proprietaire",voiture.getProprietaire());
        cv.put("telephone",voiture.getTelephone());
        long result = db.insert("voiture",null,cv);
        if(result == -1)
        {
            Toast.makeText(context,"Fail",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_LONG).show();
        }

    }

    public Cursor readAllVoitures()
    {
        String query = "Select * from voiture";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
        {
           cursor = db.rawQuery(query,null);
        }
        else
        {
            System.out.println("ERROR");
        }
        return cursor;
    }

    public Cursor searchVoitures(String cle)
    {
        String query = "Select * from voiture where lower(marque) like lower('%"+cle+"%') or lower(modele) like lower('%"+cle+"%') or lower(matricule) like lower('%"+cle+"%') or lower(proprietaire) like lower('%"+cle+"%') or lower(telephone) like lower('%"+cle+"%')";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query,null);
        }
        else
        {
            System.out.println("ERROR");
        }
        return cursor;
    }

    public void updateVoiture(Voiture voiture)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("marque",voiture.getMarque());
        cv.put("modele",voiture.getModele());
        cv.put("matricule",voiture.getMatricule());
        cv.put("proprietaire",voiture.getProprietaire());
        cv.put("telephone",voiture.getTelephone());
        long result = db.update("voiture",cv,"id=?", new String[]{String.valueOf(voiture.getId())});
        if(result == -1)
        {
            Toast.makeText(context,"Fail",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Updated successfully",Toast.LENGTH_LONG).show();
        }

    }


    public void supprimerVoiture(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("voiture","id=?", new String[]{id});
        if(result == -1)
        {
            Toast.makeText(context,"Fail",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Deleted successfully",Toast.LENGTH_LONG).show();
        }
    }



    public void addReparation(Reparation reparation)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_voiture",reparation.getVoiture());
        cv.put("cout",reparation.getCout());
        cv.put("panne",reparation.getPanne());
        cv.put("date",reparation.getDate_debut());
        cv.put("done",reparation.isDone());

        long result = db.insert("reparation",null,cv);
        if(result == -1)
        {
            Toast.makeText(context,"Fail",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_LONG).show();
        }

    }

    public void supprimerReparations()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("reparation",null,null);
        if(result == -1)
        {
            Toast.makeText(context,"Fail",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Deleted reparations",Toast.LENGTH_LONG).show();
        }
    }


    public Cursor searchReparation(String date)
    {
        String query = "SELECT r.id, r.id_voiture, r.date, r.cout, r.panne, r.done, v.matricule, v.marque,v.modele,v.proprietaire,v.telephone FROM reparation r inner join voiture v on r.id_voiture=v.id WHERE r.date = '"+date+"'";

        //String query = "SELECT * FROM reparation WHERE date = '"+date+"'";
        //String query = "SELECT * FROM reparation ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query,null);
        }
        else
        {
            System.out.println("ERROR");
        }
        return cursor;
    }


}
