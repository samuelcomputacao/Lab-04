package com.samuel.lab4.exception;

public class AlunoJaCadastrado extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public AlunoJaCadastrado() {
		super("ALUNO JÁ CADASTRADO!");
	}

}
