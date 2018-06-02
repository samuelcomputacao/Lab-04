package com.samuel.lab4.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.samuel.lab4.exception.CampoVazioException;

/**
 * Classe que representa um grupo no sistema
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class Grupo {
	
	/**
	 * Representa o nome do grupo
	 */
	private String nome;
	
	/**
	 * Represeta todos os alunos que estão alocados neste grupo 
	 */
	private Set<Aluno> alunos;

	/**
	 * Método responsável por inicializar um grupo no sistema
	 * @param nome : Uma String representando o nome do grupo
	 * @throws CampoVazioException : Uma exceção que é lançada quando o nome do grupo recebido é nulo
	 */
	public Grupo(String nome) throws CampoVazioException {
		if(nome==null)throw new CampoVazioException("CAMPO NOME DO GRUPO VAZIO");
		this.nome = nome;
		this.alunos= new HashSet<Aluno>();
	}

	/**
	 * Método reponsável por criar um inteiro que represente o grupo de maneira única no sistema a partir do seu nome
	 * @return Um inteiro representando o grupo
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.toLowerCase().hashCode());
		return result;
	}

	/**
	 * Método responsável por comparar dois Grupos a partir de seus nomes
	 * @return Retorna um valor bolleano que indica se eles tem o mesmo nome ou não
	 */
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
	
	/**
	 * Método acessível para pegar o nome do grupo 
	 * @return Uma String representando o nome do grupo
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Método responsável por alocar um aluno ao grupo
	 * @param aluno : Um aluno que será alocado no grupo
	 * @return Um valor bolleano que indica se foi possível alocar o aluno ou não
	 * @throws CampoVazioException : Retorna esta exceção se o aluno passado como parâmetro for nulo
	 */
	public boolean alocar(Aluno aluno) throws CampoVazioException {
		if(aluno==null) throw new CampoVazioException("CAMPO ALUNO VAZIO");
		return alunos.add(aluno);
	}
	
	/**
	 * Método reponsável por criar uma lista de String que representa todo os alunos alocados no grupo
	 * @return Uma lista de String que representa todos os alunos que estão alocados no grupo
	 */
	public List<String> listaAlocados() {
		List<String> result = new ArrayList<>();
		for(Aluno aluno:alunos) {
			result.add(" * "+aluno.toString());
		}
		return result;
	}
	
}
