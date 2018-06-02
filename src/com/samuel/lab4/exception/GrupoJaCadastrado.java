package com.samuel.lab4.exception;

public class GrupoJaCadastrado extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public GrupoJaCadastrado() {
		super("GRUPO JÁ CADASTRADO!");
	}

}
