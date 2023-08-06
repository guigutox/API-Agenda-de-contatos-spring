package com.example.springboot.utils;

import java.util.ArrayList;
import java.util.List;

public class Response <T>{
    private T data;
    private List<String> errors = new ArrayList<>();

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

}
