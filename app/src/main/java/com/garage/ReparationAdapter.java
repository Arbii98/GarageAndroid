package com.garage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReparationAdapter extends RecyclerView.Adapter<ReparationAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> couts,pannes,dates,matricules, marques, modeles, proprietaires,telephones;
    private ArrayList<Integer> ids,idsReparations;

    //public ReparationAdapter(Context context, ArrayList<String> couts, ArrayList<String> pannes, ArrayList<String> dates, ArrayList<String> matricules, ArrayList<String> marques, ArrayList<String> modeles, ArrayList<String> proprietaires, ArrayList<String> telephones, ArrayList<Integer> ids, ArrayList<Integer> idsReparations) {
    public ReparationAdapter(Context context, ArrayList<String> couts, ArrayList<String> pannes, ArrayList<String> dates, ArrayList<String> matricules, ArrayList<String> marques, ArrayList<String> modeles, ArrayList<String> proprietaires, ArrayList<String> telephones) {

        this.context = context;
        this.couts = couts;
        this.pannes = pannes;
        this.dates = dates;
        this.matricules = matricules;
        this.marques = marques;
        this.modeles = modeles;
        this.proprietaires = proprietaires;
        this.telephones = telephones;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reparation_row,parent,false);
        return new ReparationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.coutReparation.setText("Cout :"+String.valueOf(couts.get(position))+" DT");
        holder.panneReparation.setText("Panne :"+String.valueOf(pannes.get(position)));
        holder.marqueReparation.setText(String.valueOf(marques.get(position)));
        holder.modeleReparation.setText(String.valueOf(modeles.get(position)));
        holder.matriculeReparation.setText(String.valueOf(matricules.get(position)));
        holder.proprietaireReparation.setText(String.valueOf(proprietaires.get(position)));
        holder.telephoneReparation.setText(String.valueOf(telephones.get(position)));

    }

    @Override
    public int getItemCount() {
        return couts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView marqueReparation;
        TextView modeleReparation;
        TextView matriculeReparation;
        TextView proprietaireReparation;
        TextView telephoneReparation;
        TextView coutReparation;
        TextView panneReparation;
        LinearLayout mainLayoutReparation;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coutReparation = itemView.findViewById(R.id.coutReparation);
            panneReparation = itemView.findViewById(R.id.panneReparation);
            marqueReparation = itemView.findViewById(R.id.marqueReparation);
            modeleReparation = itemView.findViewById(R.id.modeleReparation);
            matriculeReparation = itemView.findViewById(R.id.matriculeReparation);
            proprietaireReparation = itemView.findViewById(R.id.proprietaireReparation);
            telephoneReparation = itemView.findViewById(R.id.telephoneReparation);
            mainLayoutReparation = itemView.findViewById(R.id.mainLayoutReparation);
        }
    }
}
