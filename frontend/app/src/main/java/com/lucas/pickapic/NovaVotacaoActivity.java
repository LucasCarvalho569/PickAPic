package com.lucas.pickapic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.lucas.pickapic.model.Foto;
import com.lucas.pickapic.model.Usuario;
import com.lucas.pickapic.model.Votacao;
import com.lucas.pickapic.service.FotoService;
import com.lucas.pickapic.service.VotacaoService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class NovaVotacaoActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;

    // SERVICES
    private VotacaoService votacaoService = new VotacaoService();
    private FotoService fotoService = new FotoService();

    // VIEW COMPONENTS
    EditText editTextDescricao;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;

    ImageView currentImgViewClicked;

    Uri uriImg1;
    Uri uriImg2;
    Uri uriImg3;
    Uri uriImg4;

    Button buttonConcluir;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_votacao);

        editTextDescricao = (EditText) findViewById(R.id.edtDescricao);
        setupImageViewsClickListener();
        setupButtons();
    }

    public void setupButtons() {
        this.buttonConcluir = (Button) findViewById(R.id.btnConcluir);

        this.buttonConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario usuarioLogado = new Usuario(AccessToken.getCurrentAccessToken().getUserId());
                    Votacao votacao = new Votacao(editTextDescricao.getText().toString(), usuarioLogado);
                    votacao = votacaoService.save(votacao);

                    List<Foto> fotosVotacao = Arrays.asList(
                            new Foto(getBytes(getContentResolver().openInputStream(uriImg1)), votacao),
                            new Foto(getBytes(getContentResolver().openInputStream(uriImg2)), votacao),
                            new Foto(getBytes(getContentResolver().openInputStream(uriImg3)), votacao),
                            new Foto(getBytes(getContentResolver().openInputStream(uriImg4)), votacao)
                    );

                    for(Foto f : fotosVotacao) {
                        fotoService.save(f);
                    }

                    redirecionarMInhasVotacoes();

                    Toast.makeText(getApplicationContext(), "Votação criada com sucesso!", Toast.LENGTH_LONG + 10).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG + 5).show();
                }
            }
        });
    }

    private void redirecionarMInhasVotacoes() {
        intent = new Intent(this, MinhasVotacoesActivity.class);
        startActivity(intent);
    }

    public void setupImageViewsClickListener() {
        img1 = (ImageView) findViewById(R.id.imgNovaFoto1);
        img2 = (ImageView) findViewById(R.id.imgNovaFoto2);
        img3 = (ImageView) findViewById(R.id.imgNovaFoto3);
        img4 = (ImageView) findViewById(R.id.imgNovaFoto4);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(img1);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(img2);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(img3);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(img4);
            }
        });
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private void showFileChooser(ImageView imgView) {
        currentImgViewClicked = imgView;

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void setImgPathUri(ImageView iv, Uri filePath) {
        if(iv == this.img1) {
            this.uriImg1 = filePath;
        } else if (iv == this.img2) {
            this.uriImg2 = filePath;
        } else if (iv == this.img3) {
            this.uriImg3 = filePath;
        } else if (iv == this.img4) {
            this.uriImg4 = filePath;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                setImgPathUri(currentImgViewClicked, filePath);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                currentImgViewClicked.setBackground(null);
                currentImgViewClicked.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
