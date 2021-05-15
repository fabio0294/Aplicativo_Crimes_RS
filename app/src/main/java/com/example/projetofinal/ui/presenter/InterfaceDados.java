package com.example.projetofinal.ui.presenter;

import android.content.Context;
import android.os.Parcelable;

import java.util.List;

public interface InterfaceDados {
    interface DataView {
        public void bindLista(List<Parcelable> lista);
        public Context getContexto();
        public void stopRefreshing();
        public void mostraToast(String msg);
    }

    interface DataPresenter {
        public void start();
    }
}
