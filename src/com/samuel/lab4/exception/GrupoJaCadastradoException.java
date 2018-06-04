package com.samuel.lab4.exception;

public class GrupoJaCadastradoException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public GrupoJaCadastradoException() {
		super("GRUPO JÁ CADASTRADO!");
	}

}
