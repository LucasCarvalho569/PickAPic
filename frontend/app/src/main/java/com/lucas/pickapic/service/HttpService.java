package com.lucas.pickapic.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class HttpService<T> extends RestTemplate {
    private final String endPoint = "http://10.17.10.104:8080/";
    private final Class<T> clazz;

    public HttpService(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    public T get(String url, String urlParam) {
        return super.getForObject(endPoint + url + "/" + urlParam, clazz);
    }

    public T post(String url, T bodyParam) {
        return super.postForObject(endPoint + url, bodyParam, clazz);
    }

    public T put(String url, T bodyParam) {
        HttpEntity<T> httpEntity = new HttpEntity<>(bodyParam);
        return super.exchange(endPoint + url, HttpMethod.PUT, httpEntity, clazz).getBody();
    }

    public void delete(String url, String urlParam) {
        super.delete(endPoint + url, urlParam);
    }
}
