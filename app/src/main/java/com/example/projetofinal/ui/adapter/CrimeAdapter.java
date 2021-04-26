package com.example.projetofinal.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinal.R;
import com.example.projetofinal.ui.model.TipoCrime;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder> {
    private List<TipoCrime> listaCrimes;

    private int layout;
    private TextView tv;
    private LayoutInflater inflator;

    public class CrimeViewHolder extends RecyclerView.ViewHolder {
        public View viewTipoCrime;
        public CrimeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewTipoCrime = itemView;
        }
    }
    public CrimeAdapter(List<TipoCrime> crimes, int layout) {
        this.listaCrimes = crimes;
        this.layout = layout;
    }

    public CrimeAdapter(Context context) {
        this.inflator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new CrimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeViewHolder holder, int position) {
        TipoCrime tipoCrime = (TipoCrime) this.listaCrimes.get(position);

        tv = holder.viewTipoCrime.findViewById(R.id.textViewTitleCrime);
        tv.setText(tipoCrime.getCrimeTipo());

        tv = holder.viewTipoCrime.findViewById(R.id.textViewNumeroCrime);
        tv.setText(tipoCrime.getCrimeNumeroTotal()+"");
    }

    @Override
    public int getItemCount() {
        try {
            return this.listaCrimes.size();
        } catch (Exception ex){return 0;}
    }
}