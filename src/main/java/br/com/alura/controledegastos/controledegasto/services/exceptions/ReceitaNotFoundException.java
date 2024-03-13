package br.com.alura.controledegastos.controledegasto.services.exceptions;

public class ReceitaNotFoundException extends RuntimeException{
    public ReceitaNotFoundException(String msg){
        super(msg);
    }
}
