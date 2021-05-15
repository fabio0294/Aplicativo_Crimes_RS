package com.example.projetofinal.ui.adapter;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinal.databinding.ContentCidadesBinding;
import com.example.projetofinal.databinding.ContentCrimesBinding;
import com.example.projetofinal.ui.model.Cidade;
import com.example.projetofinal.ui.model.TipoCrime;

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
        }
    }


    @Override
    public int getItemCount() {
        try {
            return this.lista.size();
        } catch (Exception ex){return 0;}
    }
}