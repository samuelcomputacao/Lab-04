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
import com.samuel.lab4.exception.AlunoNaoCadastrado;
import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.exception.GrupoNaoCadastrado;

public class ControleAcademico {

	private Map<String, Aluno> alunos;
	private Set<Grupo> grupos;
	private List<Aluno> registros;

	private final String PATH_ALUNOS = new File("").getAbsolutePath() + File.separator + "files" + File.separator
			+ "alunos.json";
	private final String PATH_GRUPOS = new File("").getAbsolutePath() + File.separator + "files" + File.separator
			+ "grupos.json";
	private final String PATH_RESPOSTAS = new File("").getAbsolutePath() + File.separator + "files" + File.separator
			+ "respostas.json";

	public ControleAcademico() {
	}

	public void persistir() throws IOException {
		this.persistirAlunos();
		this.persistirGrupos();
		this.persistirRespostas();
	}

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

	private Grupo[] convertToarray(Set<Grupo> setGrupos) {
		Grupo[] grupos = new Grupo[setGrupos.size()];
		int i = 0;
		for (Grupo grupo : setGrupos) {
			grupos[i++] = grupo;
		}
		return grupos;
	}

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

	private Aluno[] convertToarray(Collection<Aluno> alunos) {
		Aluno[] result = new Aluno[alunos.size()];
		Iterator<Aluno> iterator = alunos.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			result[i++] = iterator.next();
		}
		return result;
	}

	public boolean cadastrarAluno(String matricula, String nome, String curso) throws CampoVazioException {
		if (matricula == null || matricula.trim().length() == 0) {
			throw new CampoVazioException("MATRÍCULA NÃO ESPECIFICADA");
		}
		if (this.alunos.containsKey(matricula)) {
			throw new IllegalArgumentException("MATRÍCULA JÁ CADASTRADA!");
		}
		Aluno aluno = new Aluno(matricula, nome, curso);
		this.alunos.put(matricula, aluno);
		return true;
	}

	public Set<String> listarAlunos() {
		Set<String> alunos = new HashSet<>();
		for (Aluno aluno : this.alunos.values()) {
			alunos.add(aluno.getMatricula() + " - " + aluno.getNome());
		}
		return alunos;
	}

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

	public String consultar(String matricula) {
		Aluno aluno = this.alunos.get(matricula);
		if (aluno == null) {
			throw new AlunoNaoCadastrado();
		}
		return aluno.toString();
	}

	public boolean cadastrarGrupo(String nome) throws CampoVazioException {
		if(nome==null) {
			throw new CampoVazioException("CAMPO GRUPO VAZIO");
		}
		Grupo grupo = new Grupo(nome);
		boolean cadastrou = this.grupos.add(grupo);
		if (!cadastrou) {
			throw new IllegalArgumentException("GRUPO JÁ CADASTRADO!");
		}
		return true;
	}

	public void uploadData() throws FileNotFoundException {
		this.carregarAlunos();
		this.carregargrupos();
		this.carregarRespostas();
	}

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

	private void carregargrupos() throws FileNotFoundException {
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

	public String[] nomeGrupos() {
		String[] result = new String[this.grupos.size()];
		int i = 0;
		for (Grupo grupo : this.grupos) {
			result[i++] = grupo.getNome();
		}
		return result;
	}

	public boolean alocarAluno(String matricula, String nomeGrupo) throws CampoVazioException {
		if(matricula==null) throw new CampoVazioException("CAMPO MATRÍCULA VAZIO");
		if(nomeGrupo==null) throw new CampoVazioException("CAMPO NOME DO GRUPO VAZIO");
		
		if (!this.alunos.containsKey(matricula)) {
			throw new AlunoNaoCadastrado();
		}

		Aluno aluno = this.alunos.get(matricula);

		Grupo grupo = buscaGrupo(nomeGrupo);

		if (grupo == null) {
			throw new GrupoNaoCadastrado();
		}

		return grupo.alocar(aluno);
	}

	private Grupo buscaGrupo(String nomeGrupo) {
		for (Grupo grupo : grupos) {
			if (grupo.getNome().equals(nomeGrupo))
				return grupo;
		}
		return null;
	}

	public boolean temGrupos() {
		if (this.grupos.size() == 0) {
			return false;
		}
		return true;
	}

	public boolean temAlunos() {
		if (this.alunos.size() == 0) {
			return false;
		}
		return true;
	}

	public List<String> listarGrupo(String nomeGrupo) {
		Grupo grupo = buscaGrupo(nomeGrupo);
		if (grupo != null) {
			return grupo.listaAlocados();
		}
		return null;
	}

	public void registrarAlunoResposta(String matricula) {
		if (!this.alunos.containsKey(matricula)) {
			throw new AlunoNaoCadastrado();
		}
		Aluno aluno = this.alunos.get(matricula);
		this.registros.add(aluno);

	}

	public List<String> listarRegistros() {
		List<String> registros = new ArrayList<>();
		for (int i = 0; i < this.registros.size(); i++) {
			registros.add(String.format("%d. %s", i + 1, this.registros.get(i).toString()));
		}
		return registros;
	}

}
