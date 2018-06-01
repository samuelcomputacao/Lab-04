package com.samuel.lab4.model;

import com.samuel.lab4.exception.CampoVazioException;

/**
 * A classe que representa um aluno no sistema
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class Aluno {
	
	/**
	 * A matrícula de um aluno é uma sequencia de números que o indentifica de maneira unica no sistema
	 */
	private String matricula;
	
	/**
	 * Representa o nome de um aluno específico
	 */
	private String nome;
	
	/**
	 * Representa o curso que é feito pelo aluno
	 */
	private String curso;

	/**
	 * Método que realiza a criação de um aluno no sistema
	 * @param matricula : Uma String representando a matrícula do aluno
	 * @param nome : Uma String representando o nome do aluno
	 * @param curso : Uma String representando o curso realizado pelo aluno
	 * @throws CampoVazioException : É lançada quando alguns dos parâmetros é recebido nulo ou com tamanho 0 
	 */ 
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

	/**
	 * Método que criador  de um número inteiro que identifica o aluno de maneia única de acordo com sua matrícula
	 * @return Um inteiro representando um número único ligado ao aluno 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/**
	 * Método que compara dois alunos de identifica se são iguais de acordo com sua matrícula
	 * @return Um bolleano representando se os dois objetos são iguais ou não
	 */
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

	/**
	 * Método responsável por criar uma representação textual para o aluno
	 * @return Uma String representado o aluno
	 */
	@Override
	public String toString() {
		return String.format("%s - %s - %s",this.matricula, this.nome, this.curso);
	}

	/**
	 * Método acessível para pegar a matrícula
	 * @return A matrícula do aluno
	 */
	public String getMatricula() {
		return this.matricula;
	}
	
	/**
	 * Método acessível para pegar o nome do aluno
	 * @return O nome do aluno
	 */
	public String getNome() {
		return this.nome;
	}

}
