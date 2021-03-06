package com.lucas.pickapic.service;

import android.os.AsyncTask;

import com.lucas.pickapic.model.Parametro;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class HttpListService<T> extends AsyncTask<Parametro, Void, T[]> {
    private final String endPoint = "http://10.17.10.104:8080/";
    //private final String endPoint = "http://192.168.0.102:8080/";
    private final Class<T[]> clazz;
    private final RestTemplate restTemplate;

    public HttpListService(Class<T[]> clazz) {
        super();
        this.clazz = clazz;
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public T[] get(String url) {
        T[] lista = restTemplate.getForObject(endPoint + url , clazz);
        return lista;
    }

    public T[] post(final String url, final T bodyParam) {
        return restTemplate.postForObject(endPoint + url, bodyParam, clazz);
    }

    public T[] put(String url, T bodyParam) {
        HttpEntity<T> httpEntity = new HttpEntity<>(bodyParam);
        return restTemplate.exchange(endPoint + url, HttpMethod.PUT, httpEntity, clazz).getBody();
    }

    public void delete(String url, String urlParam) {
        restTemplate.delete(endPoint + url, urlParam);
    }

    @Override
    protected T[] doInBackground(Parametro... parametros) {
        if(parametros[0].getMetodo().equals("POST"))
            return post(parametros[0].getUrl(), (T) parametros[0].getObjeto());
        else if(parametros[0].getMetodo().equals("GET")) {
            return get(parametros[0].getUrl());
        }
        return null;
    }
}
