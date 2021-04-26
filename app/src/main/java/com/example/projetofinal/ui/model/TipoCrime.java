package com.example.projetofinal.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TipoCrime implements Parcelable {
    private String crimeTipo;
    private List<Cidade> crimeCidades;
    private int crimeNumeroTotal;

    public TipoCrime(String crimeTipo) {
        this.crimeTipo = crimeTipo;
    }

    public TipoCrime(String crimeTipo, List<Cidade> crimeCidades) {
        this.crimeTipo = crimeTipo;
        this.crimeCidades = crimeCidades;
    }

    public TipoCrime(String crimeTipo, List<Cidade> crimeCidades, int crimeNumeroTotal) {
        this.crimeTipo = crimeTipo;
        this.crimeCidades = crimeCidades;
        this.crimeNumeroTotal = crimeNumeroTotal;
    }

    public TipoCrime(String crimeTipo, int crimeNumeroTotal) {
        this.crimeTipo = crimeTipo;
        this.crimeNumeroTotal = crimeNumeroTotal;
    }

    public String getCrimeTipo() {
        return crimeTipo;
    }

    public void setCrimeTipo(String crimeTipo) {
        this.crimeTipo = crimeTipo;
    }

    public List<Cidade> getCrimeCidades() {
        return crimeCidades;
    }

    public void setCrimeCidades(List<Cidade> crimeCidades) {
        this.crimeCidades = crimeCidades;
    }

    public int getCrimeNumeroTotal() {
        return crimeNumeroTotal;
    }

    public void setCrimeNumeroTotal(int crimeNumeroTotal) {
        this.crimeNumeroTotal = crimeNumeroTotal;
    }

    public static Creator<TipoCrime> getCREATOR() {
        return CREATOR;
    }

    protected TipoCrime(Parcel in) {
        crimeTipo = in.readString();
        crimeCidades = in.createTypedArrayList(Cidade.CREATOR);
        crimeNumeroTotal = in.readInt();
    }

    public static final Creator<TipoCrime> CREATOR = new Creator<TipoCrime>() {
        @Override
        public TipoCrime createFromParcel(Parcel in) {
            return new TipoCrime(in);
        }

        @Override
        public TipoCrime[] newArray(int size) {
            return new TipoCrime[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(crimeTipo);
        dest.writeTypedList(crimeCidades);
        dest.writeInt(crimeNumeroTotal);
    }
}
