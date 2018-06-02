package com.samuel.lab4.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.model.Aluno;

public class AlunoTest {

	private Aluno aluno;

	@Before
	public void TestConstrutor(){
		aluno = new Aluno("111", "Samuel", "Computacao");
	}

	@Test(expected = CampoVazioException.class)
	public void TestContrutorMatriculaNull(){
		aluno = new Aluno(null, "Samuel", "Computacao");
	}

	@Test(expected = CampoVazioException.class)
	public void TestContrutorNomeNull(){
		aluno = new Aluno("111", null, "Computacao");
	}

	@Test(expected = CampoVazioException.class)
	public void TestContrutorCursoNull(){
		aluno = new Aluno("111", "Samuel", null);
	}

	@Test
	public void testHashCode() {
		int hashCode = 31 * 1 + aluno.getMatricula().hashCode();
		assertEquals(hashCode, aluno.hashCode());
	}

	@Test
	public void testEquals(){
		Aluno alunoTest = new Aluno("111", "Samuel", "Computacao");
		assertTrue(alunoTest.equals(aluno));
	}

	@Test
	public void testEqualsMatricula(){
		Aluno alunoTest = new Aluno("222", "Samuel", "Computacao");
		assertFalse(alunoTest.equals(aluno));

	}

	@Test
	public void testEqualsNome(){
		Aluno alunoTest = new Aluno("111", "Antônio", "Computacao");
		assertTrue(alunoTest.equals(aluno));
	}

	@Test
	public void testEqualsCurso(){
		Aluno alunoTest = new Aluno("111", "Samuel", "Eng. Elétrica");
		assertTrue(alunoTest.equals(aluno));
	}

	@Test
	public void testToString() {
		String str = "111 - Samuel - Computacao";
		assertEquals(str, aluno.toString());
	}

	@Test
	public void testGetMatricula() {
		assertEquals("111", aluno.getMatricula());
	}

	@Test
	public void testGetNome() {
		assertEquals("Samuel", aluno.getNome());
	}

}
