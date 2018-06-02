package com.samuel.lab4.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.model.Aluno;
import com.samuel.lab4.model.Grupo;

/**
 * Classe responsável por testar a classe Grupo
 * 
 * @author Samuel Pereira de Vasconcelos 
 *
 */
public class GrupoTest {
	
	/**
	 * Grupo que será utilizado como base para os testes
	 */
	private Grupo grupo;
	
	/**
	 * Testa o contrutor de grupo e inicializa o atribulto grupo
	 */
	@Before
	public void testGrupo() {
		grupo = new Grupo("Grupo1");
	}
	
	/**
	 * Testa o construtor de grupo quando recebe um nome nulo
	 */
	@Test(expected = CampoVazioException.class)
	public void testGrupoNull() {
		grupo = new Grupo(null);
	}
	
	/**
	 * Testa o hashcode de grupo analizando o inteiro resultante
	 */
	@Test
	public void testHashCode() {
		int hashcode = (31)+grupo.getNome().toLowerCase().hashCode();
		assertEquals(hashcode, grupo.hashCode());
	}

	/**
	 * Testa o equals de aluno como nome em minúsculo
	 */
	@Test
	public void testEqualsObjectLower() {
		Grupo grupo1= new Grupo("grupo1");
		assertEquals(grupo1, grupo);
	}
	
	/**
	 * testa o equals de aluno como o nome em Maiúsculo
	 */
	@Test
	public void testEqualsObjectUpper() {
		Grupo grupo1= new Grupo("GRUPO1");
		assertEquals(grupo1, grupo);
	}

	/**
	 * Testa o método acessível para o nome do grupo
	 */
	@Test
	public void testGetNome() {
		assertEquals("Grupo1", grupo.getNome());
	}

	/**
	 * Testa a alocação de um aluno ao grupo
	 */
	@Test
	public void testAlocarTrue() {
		Aluno aluno = new Aluno("111","Samuel","Computacao");
		assertTrue(grupo.alocar(aluno));
	}
	
	/**
	 * Testa a alocação de um aluno quando ele ja está alocado
	 */
	@Test
	public void testAlocarFalse() {
		Aluno aluno = new Aluno("111","Samuel","Computacao");
		assertTrue(grupo.alocar(aluno));
		assertFalse(grupo.alocar(aluno));
	}
	
	/**
	 * Testa a alocação de um grupo quando ele recebe um aluno nulo
	 */
	@Test(expected = CampoVazioException.class)
	public void testAlocarNull() {
		grupo.alocar(null);
	}
	
	/**
	 * Testa a criação da lista de alunos alocados
	 */
	@Test
	public void testListaAlocados() {
		Aluno aluno = new Aluno("111","Samuel","Computacao");
		List<String> lista = new ArrayList<>();
		lista.add(" * 111 - Samuel - Computacao");
		grupo.alocar(aluno);
		assertEquals(lista,grupo.listaAlocados());
	}

}
