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

import com.example.projetofinal.ui.adapter.Adapter;
import com.example.projetofinal.ui.presenter.InterfaceDados;
import com.example.projetofinal.ui.presenter.PresenterDadosGerais;

import java.util.List;

public class DadosGerais extends Fragment implements InterfaceDados.DataView {
    private RecyclerView rv;
    private View root;
    private InterfaceDados.DataPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dados_gerais, container, false);
        this.presenter = new PresenterDadosGerais(this);

        SwipeRefreshLayout str = root.findViewById(R.id.swipeDadosGerais);
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
        rv = root.findViewById(R.id.RecyclerViewListApi);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        Adapter adapter = new Adapter(lista, R.layout.content_crimes);
        rv.setAdapter(adapter);
    }

    @Override
    public Context getContexto() {
        return this.getContext();
    }

    @Override
    public void stopRefreshing() {
        SwipeRefreshLayout str = root.findViewById(R.id.swipeDadosGerais);
        str.setRefreshing(false);
    }

    @Override
    public void mostraToast(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG).show();
    }
}