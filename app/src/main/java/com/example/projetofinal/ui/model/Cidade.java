package com.example.projetofinal.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Cidade implements Parcelable {
    private String cidadeNome;
    private List<TipoCrime> cidadeCrimes;
    private int cidadeNumeroTotalCrimes;

    public Cidade(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public Cidade(String cidadeNome, List<TipoCrime> cidadeCrimes) {
        this.cidadeNome = cidadeNome;
        this.cidadeCrimes = cidadeCrimes;
    }

    public Cidade(String cidadeNome, List<TipoCrime> cidadeCrimes, int cidadeNumeroTotalCrimes) {
        this.cidadeNome = cidadeNome;
        this.cidadeCrimes = cidadeCrimes;
        this.cidadeNumeroTotalCrimes = cidadeNumeroTotalCrimes;
    }

    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public List<TipoCrime> getCidadeCrimes() {
        return cidadeCrimes;
    }

    public void setCidadeCrimes(List<TipoCrime> cidadeCrimes) {
        this.cidadeCrimes = cidadeCrimes;
    }

    public int getcidadeNumeroTotalCrimes() {
        return cidadeNumeroTotalCrimes;
    }

    public void setcidadeNumeroTotalCrimes(int cidadeNumeroTotalCrimes) {
        this.cidadeNumeroTotalCrimes = cidadeNumeroTotalCrimes;
    }

    public static Creator<Cidade> getCREATOR() {
        return CREATOR;
    }

    protected Cidade(Parcel in) {
        cidadeNome = in.readString();
        cidadeNumeroTotalCrimes = in.readInt();
    }

    public static final Creator<Cidade> CREATOR = new Creator<Cidade>() {
        @Override
        public Cidade createFromParcel(Parcel in) {
            return new Cidade(in);
        }

        @Override
        public Cidade[] newArray(int size) {
            return new Cidade[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cidadeNome);
        dest.writeInt(cidadeNumeroTotalCrimes);
    }
}
