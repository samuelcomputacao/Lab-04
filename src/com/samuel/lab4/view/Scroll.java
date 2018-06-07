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
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * @author Samuel Pereira de Vasconcelos
 *
 */
public class Scroll extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Container painelPrincipal = getContentPane();
	private JButton botaoSair;
	
	public Scroll(List<String> lista,String titulo) {
		setSize(500, 300);
		setLayout(new BorderLayout());
		setDefaultCloseOperation();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();

		Point centro = new Point(Integer.valueOf((int) ((scrnsize.getWidth() - getWidth()) / 2)),
				Integer.valueOf((int) ((scrnsize.getHeight() - getHeight()) / 2)));

		setLocation(centro);
		
		JLabel agendaLabel = new JLabel(titulo);
		agendaLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 28));
		
		JPanel jPanelTitulo = new JPanel();
		jPanelTitulo.setLayout(new FlowLayout());
		jPanelTitulo.add(agendaLabel);

		getContentPane().add(jPanelTitulo, BorderLayout.NORTH);
		
		JPanel painelScroll = new JPanel();
		BoxLayout layout = new BoxLayout(painelScroll, BoxLayout.Y_AXIS);
		painelScroll.setLayout(layout);
		painelScroll.setSize(new Dimension(500, 100));

		preencherScroll(painelScroll,lista);

		JScrollPane jScrollPane = new JScrollPane(painelScroll);

		painelPrincipal.add(jScrollPane,BorderLayout.CENTER);
		
		JPanel painelBotao = new JPanel();
		painelBotao.setLayout(new FlowLayout());
		
		botaoSair = new JButton("Sair");
		painelBotao.add(botaoSair);
		
		painelPrincipal.add(painelBotao,BorderLayout.SOUTH);
		
		setClicks();
//		pack();
		setVisible(true);
		
	}

	private void setDefaultCloseOperation() {
		finalizar();
		
	}

	private void setClicks() {
		botaoSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				finalizar();
			}

					});
		
	}
	
	private void finalizar() {
		dispose();
	}


	private void preencherScroll(JPanel painelScroll, List<String> lista) {
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 16);
		for(String item : lista ) {
			JLabel label = new JLabel(item);
			label.setFont(font);
			painelScroll.add(label);
		}
			
		
	}
}
