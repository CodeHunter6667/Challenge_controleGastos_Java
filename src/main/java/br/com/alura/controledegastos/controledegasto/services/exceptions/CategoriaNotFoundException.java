package br.com.alura.controledegastos.controledegasto.services.exceptions;

public class CategoriaNotFoundException extends RuntimeException{
    public CategoriaNotFoundException(String msg){
        super(msg);
    }
}
