package com.example.projetofinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.projetofinal.ui.adapter.Adapter;
import com.example.projetofinal.ui.presenter.InterfaceDados;
import com.example.projetofinal.ui.presenter.PresenterDadosCidades;

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
                str.setRefreshing(true);
            }
        });

        ImageView image = root.findViewById(R.id.buttonOpenMap);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), MapaDadosCidades.class);
                startActivity(intent);
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