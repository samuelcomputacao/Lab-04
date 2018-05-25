package com.samuel.lab4.exception;

public class GrupoNaoCadastrado  extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public GrupoNaoCadastrado() {
		super("Grupo não cadastrado.");
	}
	
	
	

}
