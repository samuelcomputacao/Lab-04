package com.samuel.lab4.exception;

public class AlunoNaoCadastradoException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;
	
	public AlunoNaoCadastradoException() {
		super("Aluno não cadastrado.");
	}
}
