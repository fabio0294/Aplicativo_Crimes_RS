package com.example.projetofinal;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetofinal.ui.model.TipoCrime;
import com.example.projetofinal.ui.presenter.InterfaceDados;
//import com.example.projetofinal.ui.presenter.PresenterDadosCrimesPie;
import com.example.projetofinal.ui.presenter.PresenterDadosGerais;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class GraficoPieCrimes extends Fragment implements InterfaceDados.DataView{
    private View root;
    private InterfaceDados.DataPresenter presenter;
    ArrayList<PieEntry> itens = new ArrayList<PieEntry>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_grafico_pie_crimes, container, false);
        this.presenter = new PresenterDadosGerais(this);
        Log.i("debug","1");
        SwipeRefreshLayout str = root.findViewById(R.id.swipeGraficoDadosGerais);
        str.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                str.setRefreshing(true);
                presenter.start();
            }
        });
        Log.i("debug","2");

        str.setRefreshing(true);
        presenter.start();
        Log.i("debug","3");
        return root;
    }

    @Override
    public void bindLista(List<Parcelable> lista) {
        PieChart pieChart = root.findViewById(R.id.piechart);

        for(Parcelable listaDados:lista){
            TipoCrime crime = (TipoCrime) listaDados;
            itens.add(new PieEntry(crime.getCrimeNumeroTotal(),crime.getCrimeTipo()));
        }
        PieDataSet dataSet = new PieDataSet(itens, "Crime/Totais");
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);
    }

    @Override
    public Context getContexto() {
        return this.getContext();
    }

    @Override
    public void stopRefreshing() {
        SwipeRefreshLayout str = root.findViewById(R.id.swipeGraficoDadosGerais);
        str.setRefreshing(false);
    }

    @Override
    public void mostraToast(String msg) {

    }
}

