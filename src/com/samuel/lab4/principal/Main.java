package com.samuel.lab4.principal;


import java.io.FileNotFoundException;

import com.samuel.lab4.model.ControleAcademico;
import com.samuel.lab4.view.Menu;

public class Main {
	
	public static void main(String[] args) {
		ControleAcademico academico = new ControleAcademico();
		try {
			academico.uploadData();
			new Menu(academico);
		}catch (FileNotFoundException e) {
			Menu.erro(e.getMessage());
		}
		
	}

}
