package com.samuel.lab4.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.model.Aluno;

/**
 * Classe responsável por testar a classe Aluno
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class AlunoTest {
	
	/**
	 * Aluno que será utilizado como base para os testes
	 */
	private Aluno aluno;

	/**
	 * Testa o contrutor e inicializa o atribulto aluno
	 */
	@Before
	public void TestConstrutor(){
		aluno = new Aluno("111", "Samuel", "Computacao");
	}
	
	/**
	 * Testa o construtor de aluno recebendo uma matrícula nula 
	 */
	@Test(expected = CampoVazioException.class)
	public void TestContrutorMatriculaNull(){
		aluno = new Aluno(null, "Samuel", "Computacao");
	}

	/**
	 * Testa o contrutor de aluno recebendo um nome nulo
	 */
	@Test(expected = CampoVazioException.class)
	public void TestContrutorNomeNull(){
		aluno = new Aluno("111", null, "Computacao");
	}

	/**
	 * Testa o construtor de aluno recebendo o curso nulo
	 */
	@Test(expected = CampoVazioException.class)
	public void TestContrutorCursoNull(){
		aluno = new Aluno("111", "Samuel", null);
	}

	/**
	 * Testa o hashcode de aluno, verificando o inteiro resultante
	 */
	@Test
	public void testHashCode() {
		int hashCode = 31 * 1 + aluno.getMatricula().hashCode();
		assertEquals(hashCode, aluno.hashCode());
	}
	
	/**
	 * Testa o equals quando se verifica a matrícula como comparação
	 */
	@Test
	public void testEquals(){
		Aluno alunoTest = new Aluno("111", "Samuel", "Computacao");
		assertTrue(alunoTest.equals(aluno));
	}

	/**
	 * Testa o equals de aluno quando apenas a matrícula muda
	 */
	@Test
	public void testEqualsMatricula(){
		Aluno alunoTest = new Aluno("222", "Samuel", "Computacao");
		assertFalse(alunoTest.equals(aluno));

	}
	
	/**
	 * Testa o equals de aluno quando apenas o nome muda
	 */
	@Test
	public void testEqualsNome(){
		Aluno alunoTest = new Aluno("111", "Antônio", "Computacao");
		assertTrue(alunoTest.equals(aluno));
	}

	/**
	 * Testa o equals de aluno quando apenas o curso muda
	 */
	@Test
	public void testEqualsCurso(){
		Aluno alunoTest = new Aluno("111", "Samuel", "Eng. Elétrica");
		assertTrue(alunoTest.equals(aluno));
	}

	/**
	 * Testa a geração da representação textual de aluno
	 */
	@Test
	public void testToString() {
		String str = "111 - Samuel - Computacao";
		assertEquals(str, aluno.toString());
	}

	/**
	 * Testa o método acessível para a matrícula do aluno
	 */
	@Test
	public void testGetMatricula() {
		assertEquals("111", aluno.getMatricula());
	}

	/**
	 * Testa o metodo acessível para nome de aluno
	 */
	@Test
	public void testGetNome() {
		assertEquals("Samuel", aluno.getNome());
	}

}
