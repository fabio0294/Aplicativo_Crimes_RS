package com.example.projetofinal.ui.adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinal.R;
import com.example.projetofinal.ui.model.Cidade;
import com.example.projetofinal.ui.model.TipoCrime;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListaViewHolder> {
    private List<Parcelable> lista;

    private int layout;
    private TextView tv;
    private LayoutInflater inflator;

    public class ListaViewHolder extends RecyclerView.ViewHolder {
        public View viewLista;
        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewLista = itemView;
        }
    }
    public Adapter(List<Parcelable> crimes, int layout) {
        this.lista = crimes;
        this.layout = layout;
    }

    public void setLayout(int layout){
        this.layout = layout;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ListaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {
        if (this.lista.get(position) instanceof TipoCrime) {
            TipoCrime tipoCrime = (TipoCrime) this.lista.get(position);

            tv = holder.viewLista.findViewById(R.id.textViewTitleNomeCrime);
            tv.setText(tipoCrime.getCrimeTipo());

            tv = holder.viewLista.findViewById(R.id.textViewNumeroCrimesCidade);
            tv.setText(tipoCrime.getCrimeNumeroTotal() + "");
        }else if(this.lista.get(position) instanceof Cidade){
            Cidade cidade = (Cidade) this.lista.get(position);

            tv = holder.viewLista.findViewById(R.id.textViewTitleNomeCidade);
            tv.setText(cidade.getCidadeNome());

            CardView bt = holder.viewLista.findViewById(R.id.cardViewCidade);
            bt.setTag(cidade);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CardView btn = (CardView) v;
                    Cidade cidade = (Cidade) btn.getTag();
                    /*setLayout(R.layout.detalhe_cidade);
                    Intent intent = new Intent(holder.viewLista.getContext(), DetalheCidadeActivity.class);
                    intent.putExtra("cidade",cidade.getCidadeNome());
                    holder.viewLista.getContext().startActivity(intent);*/
                }
            });

            tv = holder.viewLista.findViewById(R.id.textViewNumeroCrimesCidade);
            tv.setText(cidade.getcidadeNumeroTotalCrimes() + "");
        }
    }



    @Override
    public int getItemCount() {
        try {
            return this.lista.size();
        } catch (Exception ex){return 0;}
    }
}