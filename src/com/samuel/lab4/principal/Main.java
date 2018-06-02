package com.samuel.lab4.principal;


import java.io.FileNotFoundException;

import com.samuel.lab4.model.ControleAcademico;
import com.samuel.lab4.view.Menu;

/**
 * Classe responsável por iniciar o controle acadêmico
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class Main {

	/**
	 * Método pricipal e que é chamado para iniciar o controle acadêmico
	 */
	public static void main(String[] args) {
		ControleAcademico academico = new ControleAcademico();
		try {
			academico.uploadData();
			new Menu(academico);
		}catch (FileNotFoundException e) {
			Menu.mensagemException(e.getMessage());
		}
		
	}

}
