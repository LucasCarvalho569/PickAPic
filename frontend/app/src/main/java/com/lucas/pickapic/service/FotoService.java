package com.lucas.pickapic.service;

import com.lucas.pickapic.model.Foto;
import com.lucas.pickapic.model.Parametro;
import com.lucas.pickapic.model.Votacao;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FotoService {

    private HttpService<Foto> http;
    private HttpListService<Foto> httpList;
    private Parametro parametro = new Parametro();

    private void recreateTask() {
        this.http = new HttpService<>(Foto.class);
        this.httpList = new HttpListService<>(Foto[].class);
    }

    public Foto save(Foto foto) throws ExecutionException, InterruptedException {
        this.recreateTask();
        parametro.setMetodo("POST");
        parametro.setUrl("foto");
        parametro.setObjeto(foto);
        return http.execute(parametro).get();
    }

    public List<Foto> findAllByVotacao(Integer idVotacao) throws ExecutionException, InterruptedException {
        this.recreateTask();
        parametro.setMetodo("GET");
        parametro.setUrl("foto/"+ idVotacao.toString());
        return Arrays.asList(httpList.execute(parametro).get());
    }

    public Foto computarVoto(Integer fotoId) throws ExecutionException, InterruptedException {
        this.recreateTask();
        parametro.setMetodo("PUT");
        parametro.setUrl("foto/"+fotoId);
        return http.execute(parametro).get();
    }
}
