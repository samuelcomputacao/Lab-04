package com.samuel.lab4.model;

import com.samuel.lab4.exception.CampoVazioException;

public class Aluno {
	
	private String matricula;
	private String nome;
	private String curso;

	public Aluno(String matricula,String nome, String curso) throws CampoVazioException {
		
		if(nome==null || nome.trim().length()==0) {
			throw new CampoVazioException("NOME NÃO ESPECIFICADO");
		}
		if(curso==null || curso.trim().length()==0) {
			throw new CampoVazioException("CURSO NÃO ESPECIFICADO");
		}
		if(matricula==null || matricula.trim().length()==0) {
			throw new CampoVazioException("MATRÍCULA NÃO ESPECIFICADA");
		}
		this.nome = nome;
		this.curso = curso;
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s",this.matricula, this.nome, this.curso);
	}

	public String getMatricula() {
		return this.matricula;
	}

	public String getNome() {
		return this.nome;
	}

}
