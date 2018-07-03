package com.lucas.pickapic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lucas.pickapic.model.Votacao;
import com.lucas.pickapic.service.VotacaoService;

import java.util.ArrayList;
import java.util.List;

public class MinhasVotacoesActivity extends AppCompatActivity {

    private Long idUsuarioLogado;

    private VotacaoService votacaoService = new VotacaoService();

    List<Votacao> minhasVotacoes = new ArrayList<>();

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

        listViewMinhasVotacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                verVotacao(minhasVotacoes.get(position));
            }
        });

        try {
            this.minhasVotacoes = this.votacaoService.findMyVotacoes(idUsuarioLogado);

            ArrayAdapter<Votacao> adapter = new ArrayAdapter<Votacao>(this,
                    android.R.layout.simple_list_item_1, this.minhasVotacoes);

            listViewMinhasVotacoes.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Erro ao buscar minhas votações!", Toast.LENGTH_LONG + 5).show();
        }

    }

    public void verVotacao(Votacao votacao) {
        Intent intent = new Intent(this, EstatisticaVotacao.class);
        intent.putExtra("idVotacao", votacao.getId());
        intent.putExtra("descricaoVotacao", votacao.getDescricao());
        startActivity(intent);
    }

    public void novaVotacao(View view) {
        Intent intent = new Intent(this, NovaVotacaoActivity.class);
        startActivity(intent);
    }
}
