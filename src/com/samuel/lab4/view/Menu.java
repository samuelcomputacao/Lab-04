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
import java.io.IOException;
import java.util.Set;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.samuel.lab4.exception.AlunoNaoCadastradoException;
import com.samuel.lab4.exception.CampoVazioException;
import com.samuel.lab4.model.ControleAcademico;

/**
 * 
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class Menu extends JFrame {

	private final Container painelPrincipal = getContentPane();

	private JMenuItem novoAluno;
	private JMenuItem novoGrupo;
	private JMenuItem alocarAluno;
	private JMenuItem consultarAluno;
	private JMenuItem registrarAluno;
	private JMenuItem imprimirRegistro;
	private JButton botaoSair;
	private JButton botaoAtualizar;

	private Point centro;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControleAcademico controleAcademico;

	public Menu(ControleAcademico academico) {
		this.controleAcademico = academico;
		setTitle("Controle Acadêmico");
		setDefaultCloseOperation();
		setSize(500, 300);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();

		centro = new Point(Integer.valueOf((int) ((scrnsize.getWidth() - getWidth()) / 2)),
				Integer.valueOf((int) ((scrnsize.getHeight() - getHeight()) / 2)));

		setLocation(centro);
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);

		JMenu aluno = new JMenu("Aluno");

		bar.add(aluno);

		novoAluno = new JMenuItem("Novo");
		consultarAluno = new JMenuItem("consultar");

		aluno.add(novoAluno);
		aluno.add(consultarAluno);

		JMenu grupo = new JMenu("Grupo");

		novoGrupo = new JMenuItem("Novo");
		alocarAluno = new JMenuItem("Alocar");

		grupo.add(novoGrupo);
		grupo.add(alocarAluno);
		bar.add(grupo);

		JMenu respostas = new JMenu("Respostas");
		registrarAluno = new JMenuItem("Registrar Aluno");
		imprimirRegistro = new JMenuItem("Imprimir Registro");

		respostas.add(registrarAluno);
		respostas.add(imprimirRegistro);

		bar.add(respostas);

		JLabel agendaLabel = new JLabel("Controle Acadêmico");
		agendaLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 28));

		JPanel jPanelTitulo = new JPanel();
		jPanelTitulo.setLayout(new FlowLayout());
		jPanelTitulo.add(agendaLabel);

		getContentPane().add(jPanelTitulo, BorderLayout.NORTH);

		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout());

		botaoSair = new JButton("Sair");
		botaoAtualizar = new JButton("Atualizar");

		painelBotoes.add(botaoAtualizar);
		painelBotoes.add(botaoSair);

		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

		JPanel painelScroll = new JPanel();
		BoxLayout layout = new BoxLayout(painelScroll, BoxLayout.Y_AXIS);
		painelScroll.setLayout(layout);
		painelScroll.setSize(new Dimension(500, 100));

		preencherScroll(painelScroll);

		JScrollPane jScrollPane = new JScrollPane(painelScroll);

		painelPrincipal.add(jScrollPane);

		setClicks();
		setResizable(false);
		setVisible(true);
	}

	private void preencherScroll(JPanel painelScroll) {
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 16);
		Set<String> alunos = this.controleAcademico.listarAlunos();
		if (alunos != null && alunos.size() > 0) {
			for (String aluno : alunos) {
				JLabel alunoLabel = new JLabel(aluno);
				alunoLabel.setFont(font);
				painelScroll.add(alunoLabel);
			}
		}

	}

	private void setDefaultCloseOperation() {
		finalizarFrame();
	}

	private void setClicks() {
		setClickSair();
		setClickNovo();
		setClickConsultar();
		setClickAtualizar();
		setClickAlocar();
		setClickRegistrar();
		setClickImprimir();

	}

	private void setClickImprimir() {

		this.imprimirRegistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (controleAcademico.temRespostas()) {
					List<String> registros = controleAcademico.listarRegistros();
					new Scroll(registros, "Registro de Respostas");
				} else {
					mensagemException("Não há registros realizados!");
				}
			}
		});

	}

	private void setClickRegistrar() {
		this.registrarAluno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula: ", "Matrícula",
						JOptionPane.QUESTION_MESSAGE);
				if (matricula != null) {
					try {
						controleAcademico.registrarAlunoResposta(matricula);
						JOptionPane.showMessageDialog(null, "ALUNO REGISTRADO!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (RuntimeException e) {
						mensagemException(e.getMessage());
					}
				}

			}
		});

	}

	private void setClickAlocar() {
		alocarAluno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String[] opcoes = { "Alocar Aluno", "Imprimir Grupo" };
				int i = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Opção", 0,
						JOptionPane.QUESTION_MESSAGE, null, opcoes, 0);
				switch (i) {
				case 0:
					if (!controleAcademico.temAlunos()) {
						mensagemException("Não há alunos cadastrados");
						return;
					}
					if (!controleAcademico.temGrupos()) {
						mensagemException("Não há Grupos cadastrados");
						return;
					}
					String matricula = null;
					do {
						matricula = JOptionPane.showInputDialog(null, "Digite a matrícula: ", "Matrícula",
								JOptionPane.QUESTION_MESSAGE);
					} while (matricula != null && matricula.trim().length() == 0);

					if (matricula != null) {
						String[] grupos = controleAcademico.nomeGrupos();
						String grupo = (String) JOptionPane.showInputDialog(null, "Escolha um grupo:", "Grupos",
								JOptionPane.QUESTION_MESSAGE, null, grupos, grupos[0]);
						if (grupo == null)
							return;
						try {
							boolean alocou = controleAcademico.alocarAluno(matricula, grupo);
							if (alocou) {
								JOptionPane.showMessageDialog(null, "ALUNO ALOCADO!", "Sucesso",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								mensagemException("O aluno já está alocado ao grupo");
							}
						} catch (RuntimeException e) {
							mensagemException(e.getMessage());
						}
					}
					break;
				case 1:
					if (!controleAcademico.temGrupos()) {
						mensagemException("Não há Grupos cadastrados");
						return;
					}
					String[] grupos = controleAcademico.nomeGrupos();
					String grupo = (String) JOptionPane.showInputDialog(null, "Escolha um grupo:", "Grupos",
							JOptionPane.QUESTION_MESSAGE, null, grupos, grupos[0]);

					if (grupo == null)
						return;
					try {
						List<String> impressao = controleAcademico.listarGrupo(grupo);
						if (impressao == null || impressao.isEmpty()) {
							mensagemException("Não há alunos alocados ao grupo: " + grupo);
						} else {
							new Scroll(impressao, "Alunos alocados");
						}
					} catch (CampoVazioException e) {
						mensagemException(e.getMessage());
					}

					break;
				}
			}
		});
	}

	private void setClickAtualizar() {
		botaoAtualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finalizarFrame();
				new Menu(controleAcademico);
			}
		});

	}

	private void setClickConsultar() {
		consultarAluno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String matricula = JOptionPane.showInputDialog(null, "Digite a matrícula do Aluno", "Consultar",
						JOptionPane.QUESTION_MESSAGE);
				if (matricula != null && matricula.length() > 0) {
					try {
						String aluno = controleAcademico.consultar(matricula);
						JOptionPane.showMessageDialog(null, aluno, "Consutar", JOptionPane.INFORMATION_MESSAGE);
					} catch (AlunoNaoCadastradoException e) {
						mensagemException(e.getMessage());
					}
				}
			}
		});
	}

	private void setClickNovo() {
		novoAluno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NovoAluno(controleAcademico);
			}
		});

		novoGrupo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String nome = JOptionPane.showInputDialog(null, "Digite o nome do grupo:", "Novo",
						JOptionPane.QUESTION_MESSAGE);
				if (nome != null && nome.trim().length() > 0) {
					try {
						controleAcademico.cadastrarGrupo(nome);
						JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (RuntimeException e) {
						mensagemException(e.getMessage());
					}
				}else {
					mensagemException("Nome do grupo vazio!!");
				}
			}
		});

	}

	public static void mensagemException(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	private void setClickSair() {
		botaoSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finalizarFrame();
			}
		});
	}

	private void finalizarFrame() {
		try {
			controleAcademico.persistir();
		} catch (IOException e) {

			e.printStackTrace();
		}
		this.dispose();
	}

}
