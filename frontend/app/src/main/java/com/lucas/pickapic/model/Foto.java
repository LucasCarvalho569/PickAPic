package com.lucas.pickapic.model;

public class Foto {
    private Integer id;
    private byte[] arquivo;
    private Integer votos;
    private Votacao votacao;

    public Foto() {}

    public Foto(byte[] arquivo, Votacao votacao) {
        this.arquivo = arquivo;
        this.votos = 0;
        this.votacao = votacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public Votacao getVotacao() {
        return votacao;
    }

    public void setVotacao(Votacao votacao) {
        this.votacao = votacao;
    }
}