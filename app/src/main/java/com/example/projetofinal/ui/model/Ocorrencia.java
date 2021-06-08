package com.example.projetofinal.ui.model;

import android.provider.ContactsContract;

import java.sql.Timestamp;

public class Ocorrencia {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String dataOcorrido;
    private String assunto;
    private String descricaoOcorrencia;

    public Ocorrencia(String nome, String cpf, String dataNascimento, String dataOcorrido, String assunto, String descricaoOcorrencia) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.dataOcorrido = dataOcorrido;
        this.assunto = assunto;
        this.descricaoOcorrencia = descricaoOcorrencia;
    }
}
