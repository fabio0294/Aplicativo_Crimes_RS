package com.example.projetofinal;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetofinal.ui.adapter.Adapter;
import com.example.projetofinal.ui.model.TipoCrime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DadosGerais extends Fragment implements Response.Listener<JSONArray>, Response.ErrorListener{
    private String url;
    List<Parcelable> tiposCrimes =  new ArrayList<>();
    private RecyclerView rv;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dados_gerais, container, false);
        url = "https://babyier.rocks/uniritter/DadosCrimeRS2020_NomeTiposCrimes.json";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);
        queue.add(jsonArrayRequest);

        return root;
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for(int i = 0; i < response.length(); i++) {
                JSONObject json = null;
                json = response.getJSONObject(i);

                TipoCrime obj = new TipoCrime(json.getString("TIPO"),900);
                obj.getCrimeTipo();
                tiposCrimes.add(obj);
            }
            rv = root.findViewById(R.id.RecyclerViewListApi);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));

            Adapter crimeAdapter = new Adapter(tiposCrimes, R.layout.content_crimes);
            rv.setAdapter(crimeAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }
}