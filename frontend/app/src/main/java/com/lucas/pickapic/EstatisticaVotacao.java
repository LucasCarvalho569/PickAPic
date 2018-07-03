package com.lucas.pickapic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lucas.pickapic.model.Foto;
import com.lucas.pickapic.model.Votacao;
import com.lucas.pickapic.service.FotoService;

import java.text.DecimalFormat;
import java.util.List;

public class EstatisticaVotacao extends AppCompatActivity {

    //SERVICES
    private FotoService fotoService = new FotoService();

    //OBJETOS
    private Long idUsuarioLogado;
    private Integer idVotacao;
    private String descricaoVotacao;

    // VIEW COMPONENTS

    TextView labelDescricaoVotacao;
    TextView labelPercentFoto1;
    TextView labelPercentFoto2;
    TextView labelPercentFoto3;
    TextView labelPercentFoto4;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatistica_votacao);

        setupIdUsuarioLogado();
        setupIdVotacao();
        setupImagesViewAndLabels();
        setupImagesAndDescricaoVotacao();
    }

    private Bitmap getBitmap(Foto foto) {
        return BitmapFactory.decodeByteArray(foto.getArquivo(), 0, foto.getArquivo().length);
    }

    public void setupImagesAndDescricaoVotacao() {
        labelDescricaoVotacao.setText(descricaoVotacao);
        try{
            List<Foto> fotos = fotoService.findAllByVotacao(idVotacao);
            if(fotos != null && fotos.size() == 4) {
                Foto foto1 = fotos.get(0);
                Foto foto2 = fotos.get(1);
                Foto foto3 = fotos.get(2);
                Foto foto4 = fotos.get(3);
                img1.setImageBitmap(getBitmap(foto1));
                img2.setImageBitmap(getBitmap(foto2));
                img3.setImageBitmap(getBitmap(foto3));
                img4.setImageBitmap(getBitmap(foto4));

                Integer somaVotos = 0;
                for(Foto ft : fotos) {
                    somaVotos += ft.getVotos();
                }

                labelPercentFoto1.setText( getPercent(foto1.getVotos(), somaVotos) + " %" );
                labelPercentFoto2.setText( getPercent(foto2.getVotos(), somaVotos) + " %" );
                labelPercentFoto3.setText( getPercent(foto3.getVotos(), somaVotos) + " %" );
                labelPercentFoto4.setText( getPercent(foto4.getVotos(), somaVotos) + " %" );
            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Erro ao buscar fotos da votação "+ idVotacao +"!", Toast.LENGTH_LONG + 5).show();
        }
    }

    private String getPercent(Integer i1, Integer i2) {
        Double result = ((double)i1 / (double)i2) * 100;
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(result);
    }

    public void setupImagesViewAndLabels() {
        labelDescricaoVotacao = (TextView) findViewById(R.id.lblTituloVotacao2);

        img1 = (ImageView) findViewById(R.id.foto11);
        img2 = (ImageView) findViewById(R.id.foto22);
        img3 = (ImageView) findViewById(R.id.foto33);
        img4 = (ImageView) findViewById(R.id.foto44);

        labelPercentFoto1 = (TextView) findViewById(R.id.lblPercentFoto1);
        labelPercentFoto2 = (TextView) findViewById(R.id.lblPercentFoto2);
        labelPercentFoto3 = (TextView) findViewById(R.id.lblPercentFoto3);
        labelPercentFoto4 = (TextView) findViewById(R.id.lblPercentFoto4);

    }

    public void setupIdUsuarioLogado() {
        SharedPreferences storage = getSharedPreferences("MY_STORAGE", MODE_PRIVATE);
        this.idUsuarioLogado = storage.getLong("idUsuarioLogado", 0L);
    }

    public void setupIdVotacao() {
        Intent i = getIntent();
        this.descricaoVotacao = i.getStringExtra("descricaoVotacao");
        this.idVotacao = i.getIntExtra("idVotacao", 0);
    }
}
