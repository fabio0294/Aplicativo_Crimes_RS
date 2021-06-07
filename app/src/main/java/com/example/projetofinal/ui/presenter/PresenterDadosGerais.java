package com.example.projetofinal.ui.presenter;

import android.os.Parcelable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetofinal.ui.model.Cidade;
import com.example.projetofinal.ui.model.TipoCrime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PresenterDadosGerais implements Response.Listener<JSONArray>,
        Response.ErrorListener, InterfaceDados.DataPresenter{

    private List<Parcelable> lista;
    private InterfaceDados.DataView view;
    private Iterator<String> keysCidade;
    private Iterator<String> keysCrimes;
    private int totalCrimes;

    public PresenterDadosGerais(InterfaceDados.DataView view) {
        this.view = view;
    }

    @Override
    public void start() {
        String url = "https://babyier.rocks/uniritter/DadosCrimeRS2020_CrimesPorTipo.json";
        RequestQueue queue = Volley.newRequestQueue(view.getContexto());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onResponse(JSONArray response) {
        this.lista = new ArrayList<>();

        try {
            for(int i = 0; i < response.length(); i++) {
                JSONObject json = response.getJSONObject(i);
                keysCrimes = json.keys();

                //LEITURA DO TIPO DE CRIME
                while (keysCrimes.hasNext()) {
                    List<Cidade> cidades = new ArrayList<>();
                    String keyCrime = keysCrimes.next();

                    totalCrimes = 0;
                    cidades.clear();

                    //LEITURA DOS ITENS DA CIDADE (TIPOS DE CRIMES E VALOR)
                    JSONObject jsonExt = json.getJSONObject(keyCrime);
                    keysCrimes = jsonExt.keys();
                    while (keysCrimes.hasNext()) {
                        String keyItemCidade = keysCrimes.next();
                        Cidade cidade = new Cidade(keyItemCidade, jsonExt.getInt(keyItemCidade));
                        totalCrimes += cidade.getCidadeNumeroTotalCrimes();

                        cidades.add(cidade);
                    }

                    TipoCrime crime = new TipoCrime(keyCrime, cidades, totalCrimes);
                    lista.add(crime);
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