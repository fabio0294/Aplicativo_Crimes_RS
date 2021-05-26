package com.example.projetofinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.projetofinal.ui.model.Cidade;
import com.example.projetofinal.ui.model.TipoCrime;
import com.google.android.material.shape.ShapePath;

import java.util.ArrayList;
import java.util.List;

public class DetalheCidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cidade);

        TextView tv;

        Intent intent = getIntent();
        Cidade cidade = intent.getParcelableExtra("objCidade");

        tv = findViewById(R.id.textViewDetalheNomeCidade);
        tv.setText(cidade.getCidadeNome());

        try {
            Log.e("OPA","NRO CRIMES:" + cidade.getCidadeCrimes().size()+"");
        } catch (Exception ex){
            Log.e("OPA","ERRO:" + ex.getMessage());;
        }

        /*List<TipoCrime> arrayCrimes = cidade.getCidadeCrimes();
        int aux = 1;
        for(TipoCrime tipoCrime:arrayCrimes) {
            if(aux == 1){
                tv = findViewById(R.id.textViewCrime1);
                tv.setText(tipoCrime.getCrimeTipo());
                aux += 1;
            }else if(aux == 2){
                tv = findViewById(R.id.textViewCrime2);
                tv.setText(tipoCrime.getCrimeTipo());
                break;
            }
        }*/
    }
}