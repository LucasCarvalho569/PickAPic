package com.lucas.pickapic.model;

import java.io.Serializable;

public class Votacao implements Serializable {

    private Integer id;
    private String descricao;
    private Usuario usuario;

    public Votacao() {};

    public Votacao(String descricao, Usuario us) {
        this.descricao = descricao;
        this.usuario = us;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Descrição: " + descricao;
    }
}