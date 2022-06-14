package com.garage.Models;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Reparation {

    private int id;
    private int id_voiture;
    private String date_debut;
    private float cout;
    private String panne;
    private boolean done;

    public Reparation() {
        this.done= false;
    }

    public Reparation(int id, int voiture, String date_debut, float cout, String panne) {
        this.id = id;
        this.id_voiture = voiture;
        this.date_debut = date_debut;
        this.cout = cout;
        this.panne = panne;
        this.done = false;

    }

    @Override
    public String toString() {
        return "Reparation{" +
                "id=" + id +
                ", id_voiture=" + id_voiture +
                ", date_debut=" + date_debut +
                ", cout=" + cout +
                ", panne='" + panne + '\'' +
                ", done=" + done +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoiture() {
        return id_voiture;
    }

    public void setVoiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public float getCout() {
        return cout;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }

    public String getPanne() { return panne; }

    public void setPanne(String panne) { this.panne = panne; }

    public boolean isDone(){ return this.done; }

    public void Finish()
    {
        this.done=true;
    }
}
