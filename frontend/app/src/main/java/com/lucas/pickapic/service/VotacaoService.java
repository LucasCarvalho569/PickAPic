package com.lucas.pickapic.service;

import com.lucas.pickapic.model.Parametro;
import com.lucas.pickapic.model.Usuario;
import com.lucas.pickapic.model.Votacao;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class VotacaoService {

    private HttpService<Votacao> http;
    private HttpListService<Votacao> httpList;
    private Parametro parametro = new Parametro();

    private void recreateTask() {
        this.http = new HttpService<>(Votacao.class);
        this.httpList = new HttpListService<>(Votacao[].class);
    }

    public Votacao save(Votacao votacao) throws ExecutionException, InterruptedException {
        this.recreateTask();
        parametro.setMetodo("POST");
        parametro.setUrl("votacao");
        parametro.setObjeto(votacao);
        return http.execute(parametro).get();
    }

    public List<Votacao> findMyVotacoes(Long idUsuario) throws ExecutionException, InterruptedException {
        this.recreateTask();
        parametro.setMetodo("GET");
        parametro.setUrl("votacao/minhas-votacoes/"+idUsuario);
        return Arrays.asList(httpList.execute(parametro).get());
    }

    public List<Votacao> findTodasVotacoesExceptMy(Long idUsuario) throws ExecutionException, InterruptedException {
        this.recreateTask();
        parametro.setMetodo("GET");
        parametro.setUrl("votacao/todas-votacoes/"+idUsuario);
        return Arrays.asList(httpList.execute(parametro).get());
    }
}
