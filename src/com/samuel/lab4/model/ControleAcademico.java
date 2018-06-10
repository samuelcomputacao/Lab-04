package com.samuel.lab4.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.samuel.lab4.exception.AlunoJaCadastradoException;
import com.samuel.lab4.exception.AlunoNaoCadastradoException;
import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.exception.GrupoJaCadastradoException;
import com.samuel.lab4.exception.GrupoNaoCadastradoException;

/**
 * Esta é a classe principal do sistema. Ela é reponsável por realizar todas as funcionalizades.
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class ControleAcademico {
	
	/**
	 * Representa todos os alunos que estã cadastrados no sistema 
	 */
	private Map<String, Aluno> alunos;
	
	/**
	 * Representa todos os grupos que estão cadastrados no sistema 
	 */
	private Set<Grupo> grupos;
	
	/**
	 * Representa um registro de todos os alunos que responderam questões no quadro
	 */
	private List<Aluno> registros;

	/**
	 * Representa o caminho do arquivo onde estão salvo todos os alunos cadastrados
	 */
	private final String PATH_ALUNOS = new File("").getAbsolutePath() + File.separator + "files" + File.separator
			+ "alunos.json";
	
	/**
	 * Representa o caminho do arquivo onde estão salvo todos os grupos cadastrados
	 */
	private final String PATH_GRUPOS = new File("").getAbsolutePath() + File.separator + "files" + File.separator
			+ "grupos.json";
	
	/**
	 * Representa o caminho dos arquivo onde estão salvo todos os registros de repostas cadastrados
	 */
	private final String PATH_RESPOSTAS = new File("").getAbsolutePath() + File.separator + "files" + File.separator
			+ "respostas.json";

	/**
	 * Método responsável por inicializar o processo de persistir os dados que estão sendo usados pelo sistema
	 * @throws IOException : Uma exceção que é lançada quando ocorre algum erro no processo
	 */
	public void persistir() throws IOException {
		this.persistirAlunos();
		this.persistirGrupos();
		this.persistirRespostas();
	}
	
	/**
	 * Método responsável por salvar todas as respostas em memória no arquivo de respostas 
	 * @throws IOException : Uma exceção que é lançada quando ocorre algum erro no processo de escrita do arquivo
	 */
	private void persistirRespostas() throws IOException {
		if (this.registros != null) {
			Gson gson = new Gson();
			File file = new File(PATH_RESPOSTAS);
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(gson.toJson(convertToarray(this.registros)));
			bufferedWriter.close();
			fileWriter.close();
		}

	}

	/**
	 * Método responsável por salvar todas os grupos em memória no arquivo de grupos 
	 * @throws IOException : Uma exceção que é lançada quando ocorre algum erro no processo de escrita do arquivo
	 */
	private void persistirGrupos() throws IOException {
		if (this.grupos != null) {
			Gson gson = new Gson();
			File file = new File(PATH_GRUPOS);
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(gson.toJson(convertToarray(this.grupos)));
			bufferedWriter.close();
			fileWriter.close();
		}

	}

	/**
	 * Método responsável por converter um Conjunto de grupos em um array de grupos 
	 * @param setGrupos : Um conjunto de grupos
	 * @return Uma Array de grupos
	 */
	private Grupo[] convertToarray(Set<Grupo> setGrupos) {
		Grupo[] grupos = new Grupo[setGrupos.size()];
		int i = 0;
		for (Grupo grupo : setGrupos) {
			grupos[i++] = grupo;
		}
		return grupos;
	}
	
	/**
	 * Método responsável por salvar todas os alunos em memória no arquivo de alunos 
	 * @throws IOException : Uma exceção que é lançada quando ocorre algum erro no processo de escrita do arquivo
	 */
	private void persistirAlunos() throws IOException {
		if (this.alunos != null) {
			Gson gson = new Gson();
			File file = new File(PATH_ALUNOS);
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(gson.toJson(convertToarray(this.alunos.values())));
			bufferedWriter.close();
			fileWriter.close();
		}

	}

	
	/**
	 * Método responsável por converter uma coleção de aluno em um array de aluno
	 * @param alunos : Uma coleção de alunos
	 * @return Um Array de alunos
	 */
	private Aluno[] convertToarray(Collection<Aluno> alunos) {
		Aluno[] result = new Aluno[alunos.size()];
		Iterator<Aluno> iterator = alunos.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			result[i++] = iterator.next();
		}
		return result;
	}
	
	/**
	 * Método responsável por cadastrar um aluno no sistema
	 * @param matricula : Uma String representando a matrícula do aluno
	 * @param nome : Uma String representando o nome do aluno
	 * @param curso : Uma String representando O curso feito pelo aluno
	 * @return Um valor bolleano indicando se o aluno foi cadastrado ou não
	 */
	public boolean cadastrarAluno(String matricula, String nome, String curso){
		if (matricula == null || matricula.trim().length() == 0) {
			throw new CampoVazioException("MATRÍCULA NÃO ESPECIFICADA");
		}
		if (this.alunos.containsKey(matricula)) {
			throw new AlunoJaCadastradoException();
		}
		Aluno aluno = new Aluno(matricula, nome, curso);
		this.alunos.put(matricula, aluno);
		return true;
	}
	
	/**
	 * Método responsável por criar um conjunto de string representando todos os alunos que estão cadastrados no sistema 
	 * @return Um conjunto de String representando todos os alunos do sistema
	 */
	public Set<String> listarAlunos() {
		Set<String> alunos = new HashSet<>();
		for (Aluno aluno : this.alunos.values()) {
			alunos.add(aluno.getMatricula() + " - " + aluno.getNome());
		}
		return alunos;
	}
	
	/**
	 * Método responsável por carregar todos os alunos que estão salvos em arquivos
	 * @throws FileNotFoundException : Uma exceção que é lançada quando o arquivo de alunos não é encontrado 
	 */
	public void carregarAlunos() throws FileNotFoundException {
		this.alunos = new HashMap<String, Aluno>();
		Gson gson = new Gson();
		File file = new File(PATH_ALUNOS);
		if (file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Aluno[] alunos = gson.fromJson(bufferedReader, Aluno[].class);
			if (alunos != null)
				for (Aluno aluno : alunos) {
					this.alunos.put(aluno.getMatricula(), aluno);
				}
		}

	}
	
	/**
	 * Método responsável por consultar um aluno a partir de sua matrícula
	 * @param matricula : Uma String representando a matrícula do aluno
	 * @return Uma String com a representação textual do aluno
	 */
	public String consultar(String matricula) {
		if(matricula==null) {
			throw new CampoVazioException("CAMPO MATRÍCULA VAZIO");
		}
		Aluno aluno = this.alunos.get(matricula);
		if (aluno == null) {
			throw new AlunoNaoCadastradoException();
		}
		return aluno.toString();
	}
	
	/**
	 * Método responsável por cadastrar um grupo no sistema
	 * @param nome : Uma String representando o nome do grupo 
	 * @return Um valor bolleano representando de foi possível cadastrar o grupo ou não
	 */
	public boolean cadastrarGrupo(String nome){
		if(nome==null) {
			throw new CampoVazioException("CAMPO GRUPO VAZIO");
		}
		Grupo grupo = new Grupo(nome);
		boolean cadastrou = this.grupos.add(grupo);
		if (!cadastrou) {
			throw new GrupoJaCadastradoException();
		}
		return true;
	}
	
	/**
	 * Método responsável por inicializar o upload de todos os dados que fazem parte do sistema
	 * @throws FileNotFoundException : Uma exceção que é lançada quando ocorre um erro na busca dos arquivos
	 */
	public void uploadData() throws FileNotFoundException {
		this.carregarAlunos();
		this.carregarGrupos();
		this.carregarRespostas();
	}
	
	/**
	 * Método responsável por carregar todas as respostas do sistema que estão salvos em arquivo
	 * @throws FileNotFoundException : Uma exceção que é lançada quando o arquivo de respostas não pode ser encontrado
	 */
	private void carregarRespostas() throws FileNotFoundException {
		this.registros = new ArrayList<Aluno>();
		Gson gson = new Gson();

		File file = new File(PATH_RESPOSTAS);
		if (file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Aluno[] alunos = gson.fromJson(bufferedReader, Aluno[].class);
			if (alunos != null) {
				for (Aluno aluno : alunos) {
					this.registros.add(aluno);
				}
			}
		}

	}
	
	/**
	 * Método responsável por carregar todos os grupos do sistema que estão salvos em arquivo
	 * @throws FileNotFoundException : Uma exceção que é lançada quando o arquivo dos grupos não pode ser encontrado
	 */
	private void carregarGrupos() throws FileNotFoundException {
		this.grupos = new HashSet<Grupo>();
		Gson gson = new Gson();

		File file = new File(PATH_GRUPOS);
		if (file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Grupo[] grupos = gson.fromJson(bufferedReader, Grupo[].class);
			if (grupos != null) {
				for (Grupo grupo : grupos) {
					this.grupos.add(grupo);
				}
			}
		}
	}
	
	/**
	 * Método responsável por criar um array de String representando todos os nomes dos grupos que estão cadastrados no sistema
	 * @return Um array de String
	 */
	public String[] nomeGrupos() {
		String[] result = new String[this.grupos.size()];
		int i = 0;
		for (Grupo grupo : this.grupos) {
			result[i++] = grupo.getNome();
		}
		return result;
	}
	
	/**
	 * Método responsável por alocar um aluno a determinado grupo
	 * @param matricula : Uma String representando a matrícula do aluno que será alocado
	 * @param nomeGrupo : Uma String representando o nome do grupo onde o aluno será alocado
	 * @return Um valor bolleano representando se foi possível alocar o aluno ou não
	 */
	public boolean alocarAluno(String matricula, String nomeGrupo){
		if(matricula==null) throw new CampoVazioException("CAMPO MATRÍCULA VAZIO");
		if(nomeGrupo==null) throw new CampoVazioException("CAMPO NOME DO GRUPO VAZIO");
		
		if (!this.alunos.containsKey(matricula)) {
			throw new AlunoNaoCadastradoException();
		}

		Aluno aluno = this.alunos.get(matricula);

		Grupo grupo = buscaGrupo(nomeGrupo);

		if (grupo == null) {
			throw new GrupoNaoCadastradoException();
		}

		return grupo.alocar(aluno);
	}
	
	/**
	 * Método reponsável por fazaer uma busca nos grupos cadastrados no sistema
	 * @param nomeGrupo : Uma String representando o nome do grupo que erá buscado
	 * @return Um grupo de nome igual ao recebido como parâmetro
	 */
	private Grupo buscaGrupo(String nomeGrupo) {
		if(nomeGrupo==null) throw new CampoVazioException("CAMPO NOME DO GRUPO VAZIO");
		for (Grupo grupo : grupos) {
			if (grupo.getNome().equals(nomeGrupo))
				return grupo;
		}
		return null;
	}
	
	/**
	 * Método responsável por indicar se o sistema tem algum grupo cadastrado
	 * @return Um balor bolleano
	 */
	public boolean temGrupos() {
		if (this.grupos.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Método responsável por indicar se o sistema tem algum aluno cadastrado
	 * @return Um balor bolleano
	 */
	public boolean temAlunos() {
		if (this.alunos.size() == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Método responsável por criar uma lista de String representado todos os alunos alocados a um grupo
	 * @param nomeGrupo : Uma String representando o nome do grupo que será analizado
	 * @return Uma lista de String com todos os alunos alocados ao grupo
	 */
	public List<String> listarGrupo(String nomeGrupo){
		if(nomeGrupo==null) {
			throw new CampoVazioException("CAMPO NOME DO GRUPO VAZIO");
		}
		Grupo grupo = buscaGrupo(nomeGrupo);
		if (grupo == null) {
			throw new GrupoNaoCadastradoException();
		}
		return grupo.listaAlocados();
	}
	
	/**
	 * Método responsável por realizar o registro de que um aluno respondeu uma questão 
	 * @param matricula : Uma String representando a matrícula do aluno que respondeu a questão
	 * @return Um valor bolleano indicando se foi possível realizar o registro
	 */
	public boolean registrarAlunoResposta(String matricula){
		if(matricula==null) {
			throw new CampoVazioException("CAMPO MATRÍCULA VAZIO");
		}
		if (!this.alunos.containsKey(matricula)) {
			throw new AlunoNaoCadastradoException();
		}
		Aluno aluno = this.alunos.get(matricula);
		this.registros.add(aluno);
		return true;

	}
	/**
	 * Método responsável por criar uma lista de String representando todos registros de respostas que estão no sistema
	 * @return Uma Lista de String
	 */
	public List<String> listarRegistros() {
		List<String> registros = new ArrayList<>();
		for (int i = 0; i < this.registros.size(); i++) {
			registros.add(String.format("%d. %s", i + 1, this.registros.get(i).toString()));
		}
		return registros;
	}
	
	/**
	 * Método reponsável por limpar todos os dados que estão associados ao controle acadêmico
	 */
	public void limparDados() {
		this.alunos = new HashMap<>();
		this.grupos = new HashSet<>();
		this.registros = new ArrayList<>();
	}

	/**
	 * Método responsável por indicar se existem algum registri de respostas salvo no sistema
	 * @return
	 */
	public boolean temRespostas() {
		return !registros.isEmpty();
	}

}
