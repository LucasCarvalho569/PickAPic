package com.lucas.pickapic.model;

public class Usuario {
    private Integer id;
    private String facebookId;

    public Usuario() {}

    public Usuario(String facebookId) {
        this.facebookId = facebookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}