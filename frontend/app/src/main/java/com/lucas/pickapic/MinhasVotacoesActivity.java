package com.lucas.pickapic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lucas.pickapic.model.Votacao;
import com.lucas.pickapic.service.VotacaoService;

import java.util.List;

public class MinhasVotacoesActivity extends AppCompatActivity {

    private Long idUsuarioLogado;

    private VotacaoService votacaoService = new VotacaoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_votacoes);

        SharedPreferences storage = getSharedPreferences("MY_STORAGE", MODE_PRIVATE);
        this.idUsuarioLogado = storage.getLong("idUsuarioLogado", 0L);

        setupListView();
    }

    public void setupListView() {
        ListView listViewMinhasVotacoes = (ListView) findViewById(R.id.listView);

        try {
            List<Votacao> minhasVotacoes = this.votacaoService.findMyVotacoes(idUsuarioLogado);

            ArrayAdapter<Votacao> adapter = new ArrayAdapter<Votacao>(this,
                    android.R.layout.simple_list_item_1, minhasVotacoes);

            listViewMinhasVotacoes.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Erro ao buscar minhas votações!", Toast.LENGTH_LONG + 5).show();
        }

    }

    public void novaVotacao(View view) {
        Intent intent = new Intent(this, NovaVotacaoActivity.class);
        startActivity(intent);
    }
}
