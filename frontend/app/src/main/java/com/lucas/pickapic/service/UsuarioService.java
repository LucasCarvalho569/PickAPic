package com.lucas.pickapic.service;

import android.os.AsyncTask;

import com.lucas.pickapic.model.Parametro;
import com.lucas.pickapic.model.Usuario;

import java.util.concurrent.ExecutionException;

public class UsuarioService {

    private HttpService<Usuario> http;
    private Parametro parametro = new Parametro();

    private void recreateTask() {
        this.http = new HttpService<>(Usuario.class);
    }

    public Usuario salvarUsuario(Usuario usuario) throws ExecutionException, InterruptedException {
        this.recreateTask();
        parametro.setMetodo("POST");
        parametro.setUrl("usuario");
        parametro.setObjeto(usuario);
        return http.execute(parametro).get();
    }
}
