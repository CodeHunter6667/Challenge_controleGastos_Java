package br.com.alura.controledegastos.controledegasto.services.exceptions;

public class DespesaNotFoundException extends RuntimeException{
    public DespesaNotFoundException(String msg) {
        super(msg);
    }
}
