package com.samuel.lab4.exception;

public class AlunoJaCadastradoException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public AlunoJaCadastradoException() {
		super("ALUNO JÁ CADASTRADO!");
	}
}
