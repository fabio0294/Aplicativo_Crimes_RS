package com.example.projetofinal.ui.adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavType;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinal.DetalheCidade;
import com.example.projetofinal.R;
import com.example.projetofinal.databinding.ContentCidadesBinding;
import com.example.projetofinal.databinding.ContentCrimesBinding;
import com.example.projetofinal.ui.model.Cidade;
import com.example.projetofinal.ui.model.TipoCrime;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListaViewHolder> {
    private List<Parcelable> lista;
    private int layout;

    public class ListaViewHolder extends RecyclerView.ViewHolder {
        public ContentCrimesBinding viewcrimes;
        public ContentCidadesBinding viewcidades;

        public ListaViewHolder(@NonNull ContentCrimesBinding itemView) {
            super(itemView.getRoot());
            this.viewcrimes = itemView;
        }

        public ListaViewHolder(@NonNull ContentCidadesBinding itemView) {
            super(itemView.getRoot());
            this.viewcidades = itemView;
        }
    }

    public Adapter(List<Parcelable> crimes, int layout) {
        this.lista = crimes;
        this.layout = layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentCrimesBinding layoutCrimes = null;
        ContentCidadesBinding layoutCidades = null;

        if (lista.get(0) instanceof TipoCrime) {
            layoutCrimes = ContentCrimesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ListaViewHolder(layoutCrimes);
        }else if(lista.get(0) instanceof Cidade) {
            layoutCidades = ContentCidadesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ListaViewHolder(layoutCidades);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {
        if (this.lista.get(position) instanceof TipoCrime) {
            TipoCrime tipoCrime = (TipoCrime) this.lista.get(position);
            holder.viewcrimes.setCrimes(tipoCrime);
        }else if(this.lista.get(position) instanceof Cidade){
            Cidade cidade = (Cidade) this.lista.get(position);

            holder.viewcidades.setCidades(cidade);

            CardView bt = holder.viewcidades.getRoot().findViewById(R.id.cardViewCidade);
            bt.setTag(cidade);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CardView btn = (CardView) v;
                    Cidade cidade = (Cidade) btn.getTag();
                    Intent intent = new Intent(holder.viewcidades.getRoot().getContext(), DetalheCidade.class);

                    List<TipoCrime> arrayCrimes = cidade.getCidadeCrimes();
                    Log.i("DEBUG","**********************");
                    Log.i("DEBUG","Tamanho da lista de crimes no adapter: " + arrayCrimes.size());
                    Log.i("DEBUG","**********************");

                    intent.putExtra("objCidade", cidade);
                    holder.viewcidades.getRoot().getContext().startActivity(intent);

                }
            });
        }
    }

    /*// aqui vai atender o onclick definido no layout
    public void cardClick(View v) {
        Log.e("OPA","aqui");
        CardView btn = (CardView) v;
        Cidade cidade = (Cidade) btn.getTag();
        Log.e("OPA","aqui1");
        Intent intent = new Intent(v.getContext(), DetalheCidade.class);
        intent.putExtra("objcidade", cidade);
        v.getContext().startActivity(intent);
        Log.e("OPA","aqui2");
    }*/


    @Override
    public int getItemCount() {
        try {
            return this.lista.size();
        } catch (Exception ex){return 0;}
    }
}