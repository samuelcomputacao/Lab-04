package com.samuel.lab4.tests;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab4.exception.AlunoNaoCadastrado;
import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.exception.GrupoNaoCadastrado;
import com.samuel.lab4.model.ControleAcademico;

public class ControleAcademicoTest {
	
	private ControleAcademico controleAcademico;

	@Before
	public void testControleAcademico() throws FileNotFoundException {
		controleAcademico = new ControleAcademico();
		controleAcademico.limparDados();
	}

	@Test
	public void testCadastrarAluno(){
		assertTrue(controleAcademico.cadastrarAluno("111", "Samuel", "Computacao"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarAlunoRepetido(){
		assertTrue(controleAcademico.cadastrarAluno("111", "Samuel", "Computacao"));
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
	}

	
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoMatriculaNull(){
		controleAcademico.cadastrarAluno(null, "Samuel", "Computacao");
	}
	
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoNomeNull(){
		controleAcademico.cadastrarAluno("111", null, "Computacao");
	}
	
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoCursoNull(){
		controleAcademico.cadastrarAluno("111", "Samuel", null);
	}

	@Test
	public void testListarAlunos(){
		controleAcademico.cadastrarAluno("250","Mei-Ling Zhou","computacao");
		Set<String> lista  = new HashSet<String>();
		lista.add("250 - Mei-Ling Zhou");
		assertEquals(lista, controleAcademico.listarAlunos());
		
	}

	@Test
	public void testConsultarExistente(){
		String aluno = "111 - Samuel - Computacao";
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		assertEquals(aluno, controleAcademico.consultar("111"));
	}
	
	@Test(expected = AlunoNaoCadastrado.class)
	public void testConsultarInexistente(){;
		controleAcademico.consultar("1000000");
	}

	@Test
	public void testCadastrarGrupo(){
		assertTrue(controleAcademico.cadastrarGrupo("Lista"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarGrupoRepetido(){
		controleAcademico.cadastrarGrupo("Lista");
		controleAcademico.cadastrarGrupo("Lista");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarGrupoRepetidoLowerCase(){
		controleAcademico.cadastrarGrupo("Lista");
		controleAcademico.cadastrarGrupo("lista");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarGrupoRepetidoUpperCase(){
		controleAcademico.cadastrarGrupo("lista");
		controleAcademico.cadastrarGrupo("LISTA");
	}
	
	@Test(expected=CampoVazioException.class)
	public void testCadastrarGrupoNomeNull(){
		controleAcademico.cadastrarGrupo(null);
	}

	@Test
	public void testNomeGrupos(){
		String[] grupos= {"Grupo1","Grupo2","Grupo3"};
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.cadastrarGrupo("Grupo2");
		controleAcademico.cadastrarGrupo("Grupo3");
		assertArrayEquals(grupos, controleAcademico.nomeGrupos());
	}

	@Test
	public void testAlocarAluno(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.cadastrarGrupo("Grupo1");
		assertTrue(controleAcademico.alocarAluno("111", "Grupo1"));
	}
	
	@Test
	public void testAlocarAlunoRepetido(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.alocarAluno("111", "Grupo1");
		assertFalse(controleAcademico.alocarAluno("111", "Grupo1"));
	}
	
	@Test(expected = CampoVazioException.class)
	public void testAlocarMatriculaNull(){
		controleAcademico.alocarAluno(null, "Grupo1");
	}
	
	@Test(expected = CampoVazioException.class)
	public void testAlocarGrupoNull(){
		controleAcademico.alocarAluno("111", null);
	}
	
	@Test(expected = AlunoNaoCadastrado.class)
	public void testAlocarAlunoInexistente(){
		controleAcademico.alocarAluno("111", "Grupo1");
	}
	
	@Test(expected = GrupoNaoCadastrado.class)
	public void testAlocarGrupoInexistente(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.alocarAluno("111", "Grupo1");
	}

	@Test
	public void testTemGruposTrue(){
		controleAcademico.cadastrarGrupo("Grupo1");
		assertTrue(controleAcademico.temGrupos());
	}
	
	@Test
	public void testTemGruposFalse() {
		assertFalse(controleAcademico.temGrupos());
	}

	@Test
	public void testTemAlunosTrue(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		assertTrue(controleAcademico.temAlunos());	
	}
	
	@Test
	public void testTemAlunosFalse() {
		assertFalse(controleAcademico.temAlunos());	
	}
	
	@Test
	public void temRespostasTrue(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.registrarAlunoResposta("111");
		assertTrue(controleAcademico.temRespostas());
	}
	
	@Test
	public void temRespostasFalse(){
		assertFalse(controleAcademico.temRespostas());
	}
	
	
	
	@Test
	public void testListarGrupo(){
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.alocarAluno("111", "Grupo1");
		List<String> lista = new ArrayList<>();
		lista.add(" * 111 - Samuel - Computacao");
		assertEquals(lista, controleAcademico.listarGrupo("Grupo1"));
	}
	
	@Test(expected = CampoVazioException.class)
	public void testListarGrupoNull(){
		controleAcademico.listarGrupo(null);
	}
	
	@Test(expected = GrupoNaoCadastrado.class)
	public void testListarGrupoInexistente(){
		controleAcademico.listarGrupo("Grupo10000");
	}

	@Test
	public void testRegistrarAlunoResposta(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		assertTrue(controleAcademico.registrarAlunoResposta("111"));	
	}
	
	@Test(expected = CampoVazioException.class)
	public void testRegistrarAlunoRespostaNull(){
		controleAcademico.registrarAlunoResposta(null);
	}
	
	@Test(expected = AlunoNaoCadastrado.class)
	public void testRegistrarAlunoRespostaInexistente(){
		controleAcademico.registrarAlunoResposta("8080");
	}

	@Test
	public void testListarRegistros(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.cadastrarAluno("121", "Pedro", "Computacao");
		
		controleAcademico.registrarAlunoResposta("111");
		controleAcademico.registrarAlunoResposta("121");
		controleAcademico.registrarAlunoResposta("111");
		
		List<String> lista = new ArrayList<>();
		lista.add("1. 111 - Samuel - Computacao");
		lista.add("2. 121 - Pedro - Computacao");
		lista.add("3. 111 - Samuel - Computacao");
		
		assertEquals(lista,controleAcademico.listarRegistros());
	}
	
	@Test
	public void testUploadDataAndPersistir() throws IOException {

		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.registrarAlunoResposta("111");
		
		assertTrue(controleAcademico.temGrupos());
		assertTrue(controleAcademico.temAlunos());
		assertTrue(controleAcademico.temRespostas());
		controleAcademico.persistir();
		
		controleAcademico.limparDados();
		
		controleAcademico.uploadData();
		
		assertTrue(controleAcademico.temGrupos());
		assertTrue(controleAcademico.temAlunos());

	}

}
