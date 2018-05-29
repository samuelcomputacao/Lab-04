package com.samuel.lab4.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.samuel.lab4.exception.CampoVazioException;

public class Grupo {
	
	private String nome;
	private Set<Aluno> alunos;

	public Grupo(String nome) {
		this.nome = nome;
		this.alunos= new HashSet<Aluno>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.toLowerCase().hashCode());
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
		Grupo other = (Grupo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.toLowerCase().equals(other.nome.toLowerCase()))
			return false;
		return true;
	}

	public String getNome() {
		return this.nome;
	}

	public boolean alocar(Aluno aluno) throws CampoVazioException {
		if(aluno==null) throw new CampoVazioException("CAMPO ALUNO VAZIO");
		return alunos.add(aluno);
	}

	public List<String> listaAlocados() {
		List<String> result = new ArrayList<>();
		for(Aluno aluno:alunos) {
			result.add(" * "+aluno.toString() + System.lineSeparator());
		}
		return result;
	}
	
	

}
