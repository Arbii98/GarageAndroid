package com.garage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VoituresAdapter extends RecyclerView.Adapter<VoituresAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> matricules, marques, modeles, proprietaires,telephones;
    private ArrayList<Integer> ids;

    public VoituresAdapter(Context context, ArrayList<String> matricules, ArrayList<String> marques, ArrayList<String> modeles, ArrayList<String> proprietaires, ArrayList<String> telephones, ArrayList<Integer> ids) {
        this.context = context;
        this.matricules = matricules;
        this.marques = marques;
        this.modeles = modeles;
        this.proprietaires = proprietaires;
        this.telephones = telephones;
        this.ids = ids;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.voitures_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.marque.setText(String.valueOf(marques.get(position)));
        holder.modele.setText(String.valueOf(modeles.get(position)));
        holder.matricule.setText(String.valueOf(matricules.get(position)));
        holder.proprietaire.setText(String.valueOf(proprietaires.get(position)));
        holder.telephone.setText(String.valueOf(telephones.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(ids.get(position)));
                intent.putExtra("marque",String.valueOf(marques.get(position)));
                intent.putExtra("modele",String.valueOf(modeles.get(position)));
                intent.putExtra("matricule",String.valueOf(matricules.get(position)));
                intent.putExtra("proprietaire",String.valueOf(proprietaires.get(position)));
                intent.putExtra("telephone",String.valueOf(telephones.get(position)));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return matricules.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder
    {
        TextView marque;
        TextView modele;
        TextView matricule;
        TextView proprietaire;
        TextView telephone;
        LinearLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            marque = itemView.findViewById(R.id.marque);
            modele = itemView.findViewById(R.id.modele);
            matricule = itemView.findViewById(R.id.matricule);
            proprietaire = itemView.findViewById(R.id.proprietaire);
            telephone = itemView.findViewById(R.id.telephone);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
