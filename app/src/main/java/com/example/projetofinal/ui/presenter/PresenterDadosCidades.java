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

    private List<Parcelable> lista;
    private InterfaceDados.DataView view;
    private List<TipoCrime> crimes =  new ArrayList<>();
    private List<TipoCrime> listacrimes =  new ArrayList<>();
    private TipoCrime crime;
    private Iterator<String> keysCidade;
    private Iterator<String> keysCrimes;
    private int totalCrimes;
    private int aux = 0;

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
        this.lista = new ArrayList<>();

        try {
            Log.i("DEBUG","**********************AQUI FAZEMOS A LEITURA DA CIDADE INSTANCIADA COM SEUS CRIMES E TOTAIS");

            for(int i = 0; i < response.length(); i++) {
                JSONObject json = response.getJSONObject(i);
                keysCidade = json.keys();

                //LEITURA DA CIDADE
                while (keysCidade.hasNext()) {

                    String keyCidade = keysCidade.next();

                    totalCrimes = 0;
                    crimes.clear();

                    //LEITURA DOS ITENS DA CIDADE (TIPOS DE CRIMES E VALOR)
                    JSONObject jsonExt = json.getJSONObject(keyCidade);
                    keysCrimes = jsonExt.keys();
                    while (keysCrimes.hasNext()) {

                        String keyItemCrime = keysCrimes.next();

                        crime = new TipoCrime(keyItemCrime,jsonExt.getInt(keyItemCrime));
                        crimes.add(crime);
                        totalCrimes += crime.getCrimeNumeroTotal();
                    }

                    Cidade obj = new Cidade(keyCidade,crimes,totalCrimes);
                    lista.add(obj);

                    listacrimes = obj.getCidadeCrimes();

                    Log.i("DEBUG","**********************");
                    Log.i("DEBUG","Nome cidade: " + obj.getCidadeNome());
                    Log.i("DEBUG","**********************");
                    for(TipoCrime tipocrime:listacrimes){
                        Log.i("DEBUG","Tipo crime: " + tipocrime.getCrimeTipo() + " - Número total de crimes: " + tipocrime.getCrimeNumeroTotal());
                    }

                    //EXIBIMOS APENAS AS DUAS PRIMEIRAS CIDADES PARA TESTE
                    aux ++;
                    if (aux == 2){
                        break;
                    }
                }
                //EXIBIMOS APENAS AS DUAS PRIMEIRAS CIDADES PARA TESTE
                if (aux == 2){
                    break;
                }
                //EXIBIMOS APENAS AS DUAS PRIMEIRAS CIDADES PARA TESTE
                if (aux == 2){
                    break;
                }
            }

            Log.i("DEBUG","**********************");
            Log.i("DEBUG", "**********************RESULTADO DA LISTA DE PARCELABLE QUE ARMAZENA AS INSTANCIAS DE CIDADE (AQUI ESTÁ APRESENTANDO OS TOTAIS DA CIDADE AGUA SANTA PARA TODAS AS CIDADES LIDAS )");

            for (Parcelable parcelable:lista) {
                Cidade cidade = (Cidade) parcelable;

                Log.i("DEBUG","**********************");
                Log.i("DEBUG", "Nome cidade: " + cidade.getCidadeNome());
                Log.i("DEBUG","**********************");

                listacrimes = cidade.getCidadeCrimes();

                for (TipoCrime crime : listacrimes) {
                    Log.i("DEBUG", "Tipo crime: "+crime.getCrimeTipo() + " - Número total de crimes: " + crime.getCrimeNumeroTotal());
                }
            }

            Log.i("DEBUG","**********************");
            Log.i("DEBUG", "**********************RESULTADO DA LISTA DE PARCELABLE QUE ARMAZENA AS INSTANCIAS DE CIDADE (AQUI ESTÁ APRESENTANDO OS TOTAIS DA CIDADE AGUA SANTA PARA TODAS AS CIDADES LIDAS )");

            for (Parcelable parcelable:lista) {
                Cidade cidade = (Cidade) parcelable;

                Log.i("DEBUG","**********************");
                Log.i("DEBUG", "Nome cidade: " + cidade.getCidadeNome());
                Log.i("DEBUG","**********************");

                listacrimes = cidade.getCidadeCrimes();

                for (TipoCrime crime : listacrimes) {
                    Log.i("DEBUG", "Tipo crime: "+crime.getCrimeTipo() + " - Número total de crimes: " + crime.getCrimeNumeroTotal());
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