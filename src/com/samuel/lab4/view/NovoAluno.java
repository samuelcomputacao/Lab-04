package com.samuel.lab4.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.samuel.lab4.model.ControleAcademico;

/**
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class NovoAluno extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton cancelar;
	private JButton salvar;
	private JTextField nomeField;
	private JTextField cursoField;
	private JTextField matriculaField;

	private final Container painelprincipal = getContentPane();
	private ControleAcademico controleAcademico;

	private Point centro;

	public NovoAluno(ControleAcademico controleAcademico) {
		this.controleAcademico = controleAcademico;
		setDefaultCloseOperation();
		setSize(300, 350);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		centro = new Point(Integer.valueOf((int) ((scrnsize.getWidth() - getWidth()) / 2)),
				Integer.valueOf((int) ((scrnsize.getHeight() - getHeight()) / 2)));
		setLocation(centro);
		setResizable(false);
		setLayout(new BorderLayout());

		JPanel titulo = new JPanel();
		titulo.setLayout(new FlowLayout());
		JLabel tituloLabel = new JLabel("Novo Aluno");
		tituloLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		titulo.add(tituloLabel);

		painelprincipal.add(titulo, BorderLayout.NORTH);

		JPanel formulario = new JPanel();
		formulario.setLayout(new FlowLayout());

		Font font = new Font(Font.SERIF, Font.PLAIN, 15);

		JPanel painelInicio = new JPanel();
		painelInicio.setLayout(new BorderLayout());

		JPanel painelMatricula = new JPanel();
		painelMatricula.setLayout(new FlowLayout());

		JLabel matricula = new JLabel("Matrícula:");
		matricula.setFont(font);
		matriculaField = new JTextField(15);
		matriculaField.setFont(font);

		painelMatricula.add(matricula);
		painelMatricula.add(matriculaField);

		JPanel panelNome = new JPanel();
		panelNome.setLayout(new FlowLayout());

		JLabel nome = new JLabel("Nome:     ");
		nome.setFont(font);
		nomeField = new JTextField(15);
		nomeField.setFont(font);

		panelNome.add(nome);
		panelNome.add(nomeField);

		JPanel panelCurso = new JPanel();
		panelCurso.setLayout(new FlowLayout());

		JLabel curso = new JLabel("Curso:     ");
		curso.setFont(font);
		cursoField = new JTextField(15);
		cursoField.setFont(font);

		painelMatricula.add(matricula);
		painelMatricula.add(matriculaField);

		panelCurso.add(curso);
		panelCurso.add(cursoField);

		painelInicio.add(painelMatricula, BorderLayout.NORTH);
		painelInicio.add(panelNome, BorderLayout.CENTER);
		painelInicio.add(panelCurso, BorderLayout.SOUTH);

		formulario.add(painelInicio);

		JPanel painelSalvar = new JPanel();
		painelSalvar.setLayout(new FlowLayout());

		salvar = new JButton("Salvar");
		salvar.setFont(font);

		cancelar = new JButton("Cancelar");
		cancelar.setFont(font);

		painelSalvar.add(salvar);
		painelSalvar.add(cancelar);

		painelprincipal.add(formulario);
		painelprincipal.add(painelSalvar, BorderLayout.SOUTH);
		pack();
		setVisible(true);

		setClicks();
	}

	private void setDefaultCloseOperation() {
		this.finalizarFrame();

	}

	private void setClicks() {
		setClickCancelar();
		setClickSalvar();
	}

	private void setClickSalvar() {
		salvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eVENT) {
				String matricula = matriculaField.getText();
				if (matricula == null || matricula.trim().length() == 0) {
					erro("CAMPO MATRICULA VAZIO");
					return;
				}
				String nome = nomeField.getText();
				if (nome == null || nome.trim().length() == 0) {
					erro("CAMPO NOME VAZIO");
					return;
				}
				String curso = cursoField.getText();
				if (curso == null || curso.trim().length() == 0) {
					erro("CAMPO CURSO VAZIO");
					return;
				}

				try {
					boolean cadastrou = controleAcademico.cadastrarAluno(matricula, nome, curso);
					if (cadastrou) {
						JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
					}
					finalizarFrame();
				} catch (RuntimeException e) {
					erro(e.getMessage());
				}

				finalizarFrame();
			}
		});

	}

	private void erro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "erro", JOptionPane.ERROR_MESSAGE);
	}

	private void setClickCancelar() {
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finalizarFrame();

			}
		});

	}

	private void finalizarFrame() {
		this.dispose();

	}

	public static void main(String[] args) {
		new NovoAluno(new ControleAcademico());
	}

}
