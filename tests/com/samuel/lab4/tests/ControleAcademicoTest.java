package com.samuel.lab4.tests;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.samuel.lab4.exception.AlunoJaCadastrado;
import com.samuel.lab4.exception.AlunoNaoCadastrado;
import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.exception.GrupoJaCadastrado;
import com.samuel.lab4.exception.GrupoNaoCadastrado;
import com.samuel.lab4.model.ControleAcademico;

/**
 * Classe responsável por testar a classe ControleAcademico
 * @author samue
 *
 */
public class ControleAcademicoTest {
	
	/**
	 * ControleAcademico utilizado como base para aos testes
	 */
	private ControleAcademico controleAcademico;
	
	/**
	 * Testa o construtor de ConstroleAcademico e inicializa o atribulto controleAcademico
	 */
	@Before
	public void testControleAcademico(){
		controleAcademico = new ControleAcademico();
		controleAcademico.limparDados();
	}
	
	/**
	 * Testa o cadastro de aluno quando ele deve acontecer
	 */
	@Test
	public void testCadastrarAluno(){
		assertTrue(controleAcademico.cadastrarAluno("111", "Samuel", "Computacao"));
	}
	
	/**
	 * Testa o cadastro de um aluno quando ele já esta cadastrado
	 */
	@Test(expected = AlunoJaCadastrado.class)
	public void testCadastrarAlunoRepetido(){
		assertTrue(controleAcademico.cadastrarAluno("111", "Samuel", "Computacao"));
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
	}

	/**
	 * Testa o cadastro de aluno quando recebe uma matrícula nula
	 */
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoMatriculaNull(){
		controleAcademico.cadastrarAluno(null, "Samuel", "Computacao");
	}
	
	/**
	 * Testa o cadastro de aluno quando recebe um nome nulo
	 */
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoNomeNull(){
		controleAcademico.cadastrarAluno("111", null, "Computacao");
	}
	
	/**
	 * Testa o cadastro de aluno quando recebe um curso nulo 
	 */
	@Test(expected = CampoVazioException.class)
	public void testCadastrarAlunoCursoNull(){
		controleAcademico.cadastrarAluno("111", "Samuel", null);
	}

	/**
	 * Testa a criação da lista de todos os alunos cadastrados
	 */
	@Test
	public void testListarAlunos(){
		controleAcademico.cadastrarAluno("250","Mei-Ling Zhou","computacao");
		Set<String> lista  = new HashSet<String>();
		lista.add("250 - Mei-Ling Zhou");
		assertEquals(lista, controleAcademico.listarAlunos());
		
	}

	/**
	 * Testa a consulta de um aluno existente
	 */
	@Test
	public void testConsultarExistente(){
		String aluno = "111 - Samuel - Computacao";
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		assertEquals(aluno, controleAcademico.consultar("111"));
	}
	
	/**
	 * Testa a consulta de um aluno não cadastrado	
	 */
	@Test(expected = AlunoNaoCadastrado.class)
	public void testConsultarInexistente(){;
		controleAcademico.consultar("1000000");
	}
	
	/**
	 * Testa a consulta de um aluno quando recebe a matrícula nula
	 */
	@Test(expected = CampoVazioException.class)
	public void testConsultarNulo() {
		controleAcademico.consultar(null);
	}

	/**
	 * Testa o cadastro de grupo quando ele deve acotecer
	 */
	@Test
	public void testCadastrarGrupo(){
		assertTrue(controleAcademico.cadastrarGrupo("Lista"));
	}
	
	/**
	 * Testa o cadastro de um grupo que já está cadastrado
	 */
	@Test(expected=GrupoJaCadastrado.class)
	public void testCadastrarGrupoRepetido(){
		controleAcademico.cadastrarGrupo("Lista");
		controleAcademico.cadastrarGrupo("Lista");
	}

	/**
	 * Testa o cadastro de um grupo com o nome igual, mas com o nome em minúsculo 
	 */
	@Test(expected=GrupoJaCadastrado.class)
	public void testCadastrarGrupoRepetidoLowerCase(){
		controleAcademico.cadastrarGrupo("Lista");
		controleAcademico.cadastrarGrupo("lista");
	}
	
	/**
	 * Testa o cadastro de um grupo com o nome igual, mas com o nome em maiúsculo
	 */
	@Test(expected=GrupoJaCadastrado.class)
	public void testCadastrarGrupoRepetidoUpperCase(){
		controleAcademico.cadastrarGrupo("lista");
		controleAcademico.cadastrarGrupo("LISTA");
	}
	
	/**
	 * Testa o cadastro de um grupo quando recebe um nome nulo
	 */
	@Test(expected=CampoVazioException.class)
	public void testCadastrarGrupoNomeNull(){
		controleAcademico.cadastrarGrupo(null);
	}
	
	/**
	 * Testa a criação do array com todos os nomes dos grupos
	 */
	@Test
	public void testNomeGrupos(){
		String[] grupos= {"Grupo1","Grupo2","Grupo3"};
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.cadastrarGrupo("Grupo2");
		controleAcademico.cadastrarGrupo("Grupo3");
		assertArrayEquals(grupos, controleAcademico.nomeGrupos());
	}
	
	/**
	 * Testa a alocação de um aluno a um grupo quando ela deve acontecer 
	 */
	@Test
	public void testAlocarAluno(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.cadastrarGrupo("Grupo1");
		assertTrue(controleAcademico.alocarAluno("111", "Grupo1"));
	}
	
	/**
	 * Testa a alocação de um aluno a um grupo quando ele já se encontra alocado nesse grupo
	 */
	@Test
	public void testAlocarAlunoRepetido(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.alocarAluno("111", "Grupo1");
		assertFalse(controleAcademico.alocarAluno("111", "Grupo1"));
	}
	
	/**
	 * Testa a alocação de um aluno a um grupo quando recebe uma matrícula nula
	 */
	@Test(expected = CampoVazioException.class)
	public void testAlocarMatriculaNull(){
		controleAcademico.alocarAluno(null, "Grupo1");
	}
	
	/**
	 * Testa a alocação de um aluno a um grupo quando recebe um grupo nulo
	 */
	@Test(expected = CampoVazioException.class)
	public void testAlocarGrupoNull(){
		controleAcademico.alocarAluno("111", null);
	}
	
	/**
	 * Testa a alocação de um aluno a um grupo quando o aluno ainda não está cadastrado
	 */
	@Test(expected = AlunoNaoCadastrado.class)
	public void testAlocarAlunoInexistente(){
		controleAcademico.alocarAluno("111", "Grupo1");
	}
	
	/**
	 * Testa a alocação de um aluno a um grupo que ainda não foi cadastrado
	 */
	@Test(expected = GrupoNaoCadastrado.class)
	public void testAlocarGrupoInexistente(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.alocarAluno("111", "Grupo1");
	}
	
	/**
	 * Testa a verificação dos grupos quando tem grupos
	 */
	@Test
	public void testTemGruposTrue(){
		controleAcademico.cadastrarGrupo("Grupo1");
		assertTrue(controleAcademico.temGrupos());
	}
	
	/**
	 * Testa a verificação dos grupos quando não se tem grupos
	 */
	@Test
	public void testTemGruposFalse() {
		assertFalse(controleAcademico.temGrupos());
	}
	
	/**
	 * Testa a verificação dos alunos quando tem alunos cadastrados
	 */
	@Test
	public void testTemAlunosTrue(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		assertTrue(controleAcademico.temAlunos());	
	}
	
	/**
	 * Testa a verificação dos aluno quando não tem alunos cadastrados
	 */
	@Test
	public void testTemAlunosFalse() {
		assertFalse(controleAcademico.temAlunos());	
	}
	
	/**
	 * Testa a verificação de respostas quando tem respostas cadastradas
	 */
	@Test
	public void temRespostasTrue(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.registrarAlunoResposta("111");
		assertTrue(controleAcademico.temRespostas());
	}
	
	/**
	 * Testa a verificação de respostas quando não tem respostas cadastradas
	 */
	@Test
	public void temRespostasFalse(){
		assertFalse(controleAcademico.temRespostas());
	}
	
	/**
	 * Testa o listamento de todos os grupos cadastrados
	 */
	@Test
	public void testListarGrupo(){
		controleAcademico.cadastrarGrupo("Grupo1");
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		controleAcademico.alocarAluno("111", "Grupo1");
		List<String> lista = new ArrayList<>();
		lista.add(" * 111 - Samuel - Computacao");
		assertEquals(lista, controleAcademico.listarGrupo("Grupo1"));
	}
	
	/**
	 * Testa o listamento de um grupo quando quando recebe o nome nulo
	 */
	@Test(expected = CampoVazioException.class)
	public void testListarGrupoNull(){
		controleAcademico.listarGrupo(null);
	}
	
	/**
	 * Testa o listamento de um grupo quando ele ainda não está cadastrado
	 */
	@Test(expected = GrupoNaoCadastrado.class)
	public void testListarGrupoInexistente(){
		controleAcademico.listarGrupo("Grupo10000");
	}
	
	/**
	 * Testa o registro de respostas quando ele deve acontecer
	 */
	@Test
	public void testRegistrarAlunoResposta(){
		controleAcademico.cadastrarAluno("111", "Samuel", "Computacao");
		assertTrue(controleAcademico.registrarAlunoResposta("111"));	
	}
	
	/**
	 * Testa o registro de respostas quando recebe uma matrícula nula
	 */
	@Test(expected = CampoVazioException.class)
	public void testRegistrarAlunoRespostaNull(){
		controleAcademico.registrarAlunoResposta(null);
	}
	
	/**
	 * Testa o registro de respostas a um aluno que ainda não foi cadastrados
	 */
	@Test(expected = AlunoNaoCadastrado.class)
	public void testRegistrarAlunoRespostaInexistente(){
		controleAcademico.registrarAlunoResposta("8080");
	}

	/**
	 * Testa o listamento de registros de respostas realizadas pelos alunos
	 */
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
	
	/**
	 * Testa a manipulação dos dados quando persiste e quando realiza o upload 
	 * @throws IOException : Uma exceção que ocorre quando os arquivos não foram bem lidos ou escritos 
	 */
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
		
		assertFalse(controleAcademico.temGrupos());
		assertFalse(controleAcademico.temAlunos());
		
		controleAcademico.uploadData();
		
		assertTrue(controleAcademico.temGrupos());
		assertTrue(controleAcademico.temAlunos());

	}

}
