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

public class GrupoTest {
	
	private Grupo grupo;
	
	@Before
	public void testGrupo() throws CampoVazioException {
		grupo = new Grupo("Grupo1");
	}
	
	@Test(expected = CampoVazioException.class)
	public void testGrupoNull() throws CampoVazioException {
		grupo = new Grupo(null);
	}
	
	@Test
	public void testHashCode() {
		int hashcode = (31)+grupo.getNome().toLowerCase().hashCode();
		assertEquals(hashcode, grupo.hashCode());
	}

	
	@Test
	public void testEqualsObjectLower() throws CampoVazioException {
		Grupo grupo1= new Grupo("grupo1");
		assertEquals(grupo1, grupo);
	}
	
	@Test
	public void testEqualsObjectUpper() throws CampoVazioException {
		Grupo grupo1= new Grupo("GRUPO1");
		assertEquals(grupo1, grupo);
	}

	@Test
	public void testGetNome() {
		assertEquals("Grupo1", grupo.getNome());
	}

	@Test
	public void testAlocarTrue() throws CampoVazioException {
		Aluno aluno = new Aluno("111","Samuel","Computacao");
		assertTrue(grupo.alocar(aluno));
	}
	
	@Test
	public void testAlocarFalse() throws CampoVazioException {
		Aluno aluno = new Aluno("111","Samuel","Computacao");
		assertTrue(grupo.alocar(aluno));
		assertFalse(grupo.alocar(aluno));
	}
	
	@Test(expected = CampoVazioException.class)
	public void testAlocarNull() throws CampoVazioException {
		grupo.alocar(null);
	}
	

	@Test
	public void testListaAlocados() throws CampoVazioException {
		Aluno aluno = new Aluno("111","Samuel","Computacao");
		List<String> lista = new ArrayList<>();
		lista.add(" * 111 - Samuel - Computacao");
		grupo.alocar(aluno);
		assertEquals(lista,grupo.listaAlocados());
	}

}
