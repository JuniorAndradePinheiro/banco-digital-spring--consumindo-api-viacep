package br.com.bancodigital.service.exception;

public class ElementNotFoundException extends RuntimeException {


    public ElementNotFoundException(String message){
        super(message);
    }
}
