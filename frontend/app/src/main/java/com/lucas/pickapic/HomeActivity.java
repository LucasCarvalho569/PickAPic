package com.lucas.pickapic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lucas.pickapic.model.Foto;
import com.lucas.pickapic.model.Votacao;
import com.lucas.pickapic.service.FotoService;
import com.lucas.pickapic.service.VotacaoService;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    // SERVICES
    private VotacaoService votacaoService = new VotacaoService();
    private FotoService fotoService = new FotoService();

    // OBJETOS
    private Long idUsuarioLogado;
    private List<Votacao> todasVotacoes = new ArrayList<>();

    private Votacao votacaoAtual;
    private Foto foto1;
    private Foto foto2;
    private Foto foto3;
    private Foto foto4;

    // VIEW COMPONENTS

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;

    TextView editTextdescricaoVotacaoAtual;

    // VOTACAO CONTROL
    private int nextVotacao = 0;

    private boolean hasVotacao = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupIdUsuarioLogado();

        editTextdescricaoVotacaoAtual = (TextView) findViewById(R.id.lblTituloVotacao);

        setupImagesView();
        setupTodasVotacoes();
        setupVotacaoAndFotos();
    }

    public void setupIdUsuarioLogado() {
        SharedPreferences storage = getSharedPreferences("MY_STORAGE", MODE_PRIVATE);
        this.idUsuarioLogado = storage.getLong("idUsuarioLogado", 0L);
    }


    public void computarVoto(Foto foto) {
        try{
            Foto fotoComputada = this.fotoService.computarVoto(foto.getId());
            nextVotacao++;
            setupVotacaoAndFotos();
            Toast.makeText(getApplicationContext(),
                    "Voto registrado com sucesso! "+ votacaoAtual.getId() +"!",
                    Toast.LENGTH_LONG + 10).show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void setupImagesView() {
        img1 = (ImageView) findViewById(R.id.foto1);
        img2 = (ImageView) findViewById(R.id.foto2);
        img3 = (ImageView) findViewById(R.id.foto3);
        img4 = (ImageView) findViewById(R.id.foto4);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computarVoto(foto1);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computarVoto(foto2);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computarVoto(foto3);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computarVoto(foto4);
            }
        });
    }

    private Bitmap getBitmap(Foto foto) {
        return BitmapFactory.decodeByteArray(foto.getArquivo(), 0, foto.getArquivo().length);
    }

    public void setupVotacaoAndFotos() {
        if(this.todasVotacoes != null
                && this.todasVotacoes.size() > 0
                && nextVotacao < this.todasVotacoes.size()) {
            this.votacaoAtual = this.todasVotacoes.get(nextVotacao);
            editTextdescricaoVotacaoAtual.setText(votacaoAtual.getDescricao());
            try{
                List<Foto> fotos = fotoService.findAllByVotacao(votacaoAtual.getId());
                if(fotos != null && fotos.size() == 4) {
                    foto1 = fotos.get(0);
                    foto2 = fotos.get(1);
                    foto3 = fotos.get(2);
                    foto4 = fotos.get(3);
                    img1.setImageBitmap(getBitmap(foto1));
                    img2.setImageBitmap(getBitmap(foto2));
                    img3.setImageBitmap(getBitmap(foto3));
                    img4.setImageBitmap(getBitmap(foto4));
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Erro ao buscar fotos da votação "+ votacaoAtual.getId() +"!", Toast.LENGTH_LONG + 5).show();
            }

        }else{
            editTextdescricaoVotacaoAtual.setText("Aguarde novas votações...");
            hasVotacao = false;
            img1.setImageBitmap(null);
            img2.setImageBitmap(null);
            img3.setImageBitmap(null);
            img4.setImageBitmap(null);
        }
    }

    public void setupTodasVotacoes() {
        try {
            //Linha abaixo é a correta, buscar minhas votações pra facilitar desenvolvimento
            //this.todasVotacoes = this.votacaoService.findTodasVotacoesExceptMy(idUsuarioLogado);
            this.todasVotacoes = this.votacaoService.findMyVotacoes(idUsuarioLogado);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Erro ao buscar todas votações!", Toast.LENGTH_LONG + 5).show();
        }
    }

    public void listarVotacoes(View view) {
        Intent intent = new Intent(this, MinhasVotacoesActivity.class);
        startActivity(intent);
    }
}
