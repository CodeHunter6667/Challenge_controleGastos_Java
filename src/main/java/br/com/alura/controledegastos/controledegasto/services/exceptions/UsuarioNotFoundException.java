package br.com.alura.controledegastos.controledegasto.services.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
	public UsuarioNotFoundException(String msg) {
		super(msg);
	}
}
