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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.shape.ShapePath;

import java.util.ArrayList;
import java.util.List;

public class DetalheCidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cidade);
        ArrayList<PieEntry> itens = new ArrayList<PieEntry>();
        TextView tv;

        Intent intent = getIntent();
        Cidade cidade = intent.getParcelableExtra("objCidade");

        tv = findViewById(R.id.textViewDetalheNomeCidade);
        tv.setText(cidade.getCidadeNome());

        PieChart pieChart = findViewById(R.id.piechartDetalheCidade);

        String nomeCrime1 = intent.getStringExtra("NomeCrime1");
        int valorCrime1 = intent.getIntExtra("NumeroCrime1",0);
        if (valorCrime1 > 0) {
            itens.add(new PieEntry(valorCrime1, nomeCrime1));
        }

        String nomeCrime2 = intent.getStringExtra("NomeCrime2");
        int valorCrime2 = intent.getIntExtra("NumeroCrime2",0);
        if (valorCrime2 > 0) {
            itens.add(new PieEntry(valorCrime2, nomeCrime2));
        }

        String nomeCrime3 = intent.getStringExtra("NomeCrime3");
        int valorCrime3 = intent.getIntExtra("NumeroCrime3",0);
        if (valorCrime3 > 0) {
            itens.add(new PieEntry(valorCrime3, nomeCrime3));
        }

        String nomeCrime4 = intent.getStringExtra("NomeCrime4");
        int valorCrime4 = intent.getIntExtra("NumeroCrime4",0);
        if (valorCrime4 > 0) {
            itens.add(new PieEntry(valorCrime4, nomeCrime4));
        }

        String nomeCrime5 = intent.getStringExtra("NomeCrime5");
        int valorCrime5 = intent.getIntExtra("NumeroCrime5",0);
        if (valorCrime5 > 0) {
            itens.add(new PieEntry(valorCrime5, nomeCrime5));
        }

        String nomeCrime6 = intent.getStringExtra("NomeCrime6");
        int valorCrime6 = intent.getIntExtra("NumeroCrime6",0);
        if (valorCrime6 > 0) {
            itens.add(new PieEntry(valorCrime6, nomeCrime6));
        }

        String nomeCrime7 = intent.getStringExtra("NomeCrime7");
        int valorCrime7 = intent.getIntExtra("NumeroCrime7",0);
        if (valorCrime7 > 0) {
            itens.add(new PieEntry(valorCrime7, nomeCrime7));
        }

        String nomeCrime8 = intent.getStringExtra("NomeCrime8");
        int valorCrime8 = intent.getIntExtra("NumeroCrime8",0);
        if (valorCrime8 > 0) {
            itens.add(new PieEntry(valorCrime8, nomeCrime8));
        }

        String nomeCrime9 = intent.getStringExtra("NomeCrime9");
        int valorCrime9 = intent.getIntExtra("NumeroCrime9",0);
        if (valorCrime9 > 0) {
            itens.add(new PieEntry(valorCrime9, nomeCrime9));
        }

        PieDataSet dataSet = new PieDataSet(itens, "Cidade" + cidade.getCidadeNome());
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);
    }
}