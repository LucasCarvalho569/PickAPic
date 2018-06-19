package com.lucas.pickapic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MinhasVotacoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_votacoes);
    }

    public void novaVotacao(View view) {
        Intent intent = new Intent(this, NovaVotacaoActivity.class);
        startActivity(intent);
    }
}
