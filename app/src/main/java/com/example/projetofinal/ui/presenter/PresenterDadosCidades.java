package com.example.projetofinal.ui.presenter;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetofinal.DetalheCidade;
import com.example.projetofinal.ui.model.Cidade;
import com.example.projetofinal.ui.model.TipoCrime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PresenterDadosCidades implements Response.Listener<JSONArray>,
        Response.ErrorListener, InterfaceDados.DataPresenter{

    private List<Parcelable> lista = new ArrayList<>();
    private InterfaceDados.DataView view;
    private Iterator<String> keysCidade;
    private Iterator<String> keysCrimes;
    private int totalCrimes;
    public PresenterDadosCidades(InterfaceDados.DataView view) {
        this.view = view;
    }

    @Override
    public void start() {
        String url = "https://babyier.rocks/uniritter/DadosCrimeRS2020_CrimesPorCidade.json";
        RequestQueue queue = Volley.newRequestQueue(view.getContexto());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for(int i = 0; i < response.length(); i++) {
                JSONObject json = response.getJSONObject(i);
                keysCidade = json.keys();

                //LEITURA DA CIDADE
                while (keysCidade.hasNext()) {
                    List<TipoCrime> crimes = new ArrayList<>();
                    String keyCidade = keysCidade.next();

                    totalCrimes = 0;
                    crimes.clear();

                    //LEITURA DOS ITENS DA CIDADE (TIPOS DE CRIMES E VALOR)
                    JSONObject jsonExt = json.getJSONObject(keyCidade);
                    keysCrimes = jsonExt.keys();
                    while (keysCrimes.hasNext()) {
                        String keyItemCrime = keysCrimes.next();
                        TipoCrime crime = new TipoCrime(keyItemCrime, jsonExt.getInt(keyItemCrime));
                        totalCrimes += crime.getCrimeNumeroTotal();

                        crimes.add(crime);
                    }

                    Cidade obj = new Cidade(keyCidade, crimes, totalCrimes);
                    lista.add(obj);
                }
            }

            view.bindLista(lista);
            view.stopRefreshing();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String msg = error.getMessage();
        view.mostraToast("Error: "+msg);
    }
}