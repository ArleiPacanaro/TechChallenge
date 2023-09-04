package com.fiap.techchallenge.energia.exception.service;

public class ServiceNotFoundedException extends RuntimeException{

    public ServiceNotFoundedException(String message){
        super(message);
    }

}
