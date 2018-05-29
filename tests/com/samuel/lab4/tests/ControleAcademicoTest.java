package com.samuel.lab4.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab4.exception.AlunoNaoCadastrado;
import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.exception.GrupoNaoCadastrado;
import com.samuel.lab4.model.Aluno;
import com.samuel.lab4.model.ControleAcademico;

public class ControleAcademicoTest {
	
	private ControleAcademico controleAcademico;

	@Before
	public void testControleAcademico() throws FileNotFoundException {
		controleAcademico = new ControleAcademico();
		controleAcademico.uploadData();
	}

	@Test
	public void testPersistir() {
	}

	@Test
	public void testCadastrarAluno() throws CampoVazioException {
		assertTrue(controleAcademico.cadastrarAluno("111", "Samuel", "Computacao"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarAlunoRepetido() throws CampoVazioException {
		assertTrue(controleAcademico.cadastrarAluno("111", "Samuel", "Computacao"));
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
	}

	
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoMatriculaNull() throws CampoVazioException {
		controleAcademico.cadastrarAluno(null, "Samuel", "Computacao");
	}
	
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoNomeNull() throws CampoVazioException {
		controleAcademico.cadastrarAluno("111", null, "Computacao");
	}
	
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoCursoNull() throws CampoVazioException {
		controleAcademico.cadastrarAluno("111", "Samuel", null);
	}

	@Test
	public void testListarAlunos() throws CampoVazioException {
		controleAcademico.cadastrarAluno("250","Mei-Ling Zhou","computacao");
		Set<String> lista  = new HashSet<String>();
		lista.add("250 - Mei-Ling Zhou");
		assertEquals(lista, controleAcademico.listarAlunos());
		
	}

	@Test
	public void testConsultarExistente() throws CampoVazioException {
		String aluno = "111 - Samuel - Computacao";
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		assertEquals(aluno, controleAcademico.consultar("111"));
	}
	
	@Test(expected = AlunoNaoCadastrado.class)
	public void testConsultarInexistente() throws CampoVazioException {;
		controleAcademico.consultar("1000000");
	}

	@Test
	public void testCadastrarGrupo() throws CampoVazioException {
		assertTrue(controleAcademico.cadastrarGrupo("Lista"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarGrupoRepetido() throws CampoVazioException {
		controleAcademico.cadastrarGrupo("Lista");
		controleAcademico.cadastrarGrupo("Lista");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarGrupoRepetidoLowerCase() throws CampoVazioException {
		controleAcademico.cadastrarGrupo("Lista");
		controleAcademico.cadastrarGrupo("lista");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarGrupoRepetidoUpperCase() throws CampoVazioException {
		controleAcademico.cadastrarGrupo("lista");
		controleAcademico.cadastrarGrupo("LISTA");
	}
	
	@Test(expected=CampoVazioException.class)
	public void testCadastrarGrupoNomeNull() throws CampoVazioException {
		controleAcademico.cadastrarGrupo(null);
	}

	@Test
	public void testNomeGrupos() throws CampoVazioException {
		String[] grupos= {"Grupo1","Grupo2","Grupo3"};
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.cadastrarGrupo("Grupo2");
		controleAcademico.cadastrarGrupo("Grupo3");
		assertEquals(Arrays.toString(grupos), Arrays.toString(controleAcademico.nomeGrupos()));
	}

	@Test
	public void testAlocarAluno() throws CampoVazioException {
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.cadastrarGrupo("Grupo1");
		assertTrue(controleAcademico.alocarAluno("111", "Grupo1"));
	}
	
	@Test
	public void testAlocarAlunoRepetido() throws CampoVazioException {
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.alocarAluno("111", "Grupo1");
		assertFalse(controleAcademico.alocarAluno("111", "Grupo1"));
	}
	
	@Test(expected = CampoVazioException.class)
	public void testAlocarMatriculaNull() throws CampoVazioException {
		controleAcademico.alocarAluno(null, "Grupo1");
	}
	
	@Test(expected = CampoVazioException.class)
	public void testAlocarGrupoNull() throws CampoVazioException {
		controleAcademico.alocarAluno("111", null);
	}
	
	@Test(expected = AlunoNaoCadastrado.class)
	public void testAlocarAlunoInexistente() throws CampoVazioException {
		controleAcademico.alocarAluno("111", "Grupo1");
	}
	
	@Test(expected = GrupoNaoCadastrado.class)
	public void testAlocarGrupoInexistente() throws CampoVazioException {
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.alocarAluno("111", "Grupo1");
	}

	@Test
	public void testTemGruposTrue() throws CampoVazioException {
		controleAcademico.cadastrarGrupo("Grupo1");
		assertTrue(controleAcademico.temGrupos());
	}
	
	@Test
	public void testTemGruposFalse() throws CampoVazioException {
		assertFalse(controleAcademico.temGrupos());
	}

	@Test
	public void testTemAlunosTrue() throws CampoVazioException {
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		assertTrue(controleAcademico.temAlunos());	
	}
	
	@Test
	public void testTemAlunosFalse() throws CampoVazioException {
		assertFalse(controleAcademico.temAlunos());
		
		
	}
	

	@Test
	public void testListarGrupo() {
	}

	@Test
	public void testRegistrarAlunoResposta() {
	}

	@Test
	public void testListarRegistros() {
	}
	
	@Test
	public void testCarregarAlunos() {
		
	}
	
	@Test
	public void testUploadData() {
	}

}
