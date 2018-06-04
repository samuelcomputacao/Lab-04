package com.samuel.lab4.exception;

public class GrupoNaoCadastradoException  extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public GrupoNaoCadastradoException() {
		super("Grupo não cadastrado.");
	}
	
	
	

}
