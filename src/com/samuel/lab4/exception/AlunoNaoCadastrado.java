package com.samuel.lab4.exception;

public class AlunoNaoCadastrado extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;
	
	public AlunoNaoCadastrado() {
		super("Aluno não cadastrado.");
	}

}
