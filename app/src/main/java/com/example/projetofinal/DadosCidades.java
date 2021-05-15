package com.example.projetofinal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetofinal.ui.adapter.Adapter;
import com.example.projetofinal.ui.model.Cidade;
import com.example.projetofinal.ui.model.TipoCrime;
import com.example.projetofinal.ui.presenter.InterfaceDados;
import com.example.projetofinal.ui.presenter.PresenterDadosCidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DadosCidades extends Fragment implements InterfaceDados.DataView {
    private RecyclerView rv;
    private View root;
    private InterfaceDados.DataPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dados_cidades, container, false);
        this.presenter = new PresenterDadosCidades(this);

        SwipeRefreshLayout str = root.findViewById(R.id.swipeDadosCidades);
        str.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                str.setRefreshing(true);
                presenter.start();
            }
        });

        str.setRefreshing(true);
        presenter.start();

        return root;
    }

    @Override
    public void bindLista(List<Parcelable> lista) {
        rv = root.findViewById(R.id.RecyclerViewListApiCidades);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        Adapter adapter = new Adapter(lista, R.layout.content_cidades);
        rv.setAdapter(adapter);
    }

    @Override
    public Context getContexto() {
        return this.getContext();
    }

    @Override
    public void stopRefreshing() {
        SwipeRefreshLayout str = root.findViewById(R.id.swipeDadosCidades);
        str.setRefreshing(false);
    }

    @Override
    public void mostraToast(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG).show();
    }
}