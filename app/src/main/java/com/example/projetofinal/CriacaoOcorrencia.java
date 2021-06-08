package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetofinal.ui.model.Ocorrencia;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

public class CriacaoOcorrencia extends AppCompatActivity {
    private DatabaseReference mDatabase;

// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criacao_ocorrencia);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button bt = findViewById(R.id.buttonGerarOcorrencia);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = findViewById(R.id.BoletimNome);
                EditText cpf = findViewById(R.id.BoletimCpf);
                EditText dataNascimento = findViewById(R.id.BoletimDataNascimento);
                EditText dataOcorrido = findViewById(R.id.BoletimDataOcorrido);
                EditText assunto = findViewById(R.id.BoletimAssunto);
                EditText descricaoOcorrencia = findViewById(R.id.BoletimDescricao);

                Log.i("debug","nome: " + nome.getText().toString() + ",dataNascimento:" + dataNascimento.getText().toString());

                Ocorrencia ocorrencia = new Ocorrencia(nome.getText().toString(),cpf.getText().toString(),dataNascimento.getText().toString(),dataOcorrido.getText().toString(),assunto.getText().toString(),descricaoOcorrencia.getText().toString());

                mDatabase.child("ocorrencias").setValue(ocorrencia.toString());
            }
        });
    }
}