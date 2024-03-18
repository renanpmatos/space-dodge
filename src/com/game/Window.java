package com.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

// define propriedades da janela

public class Window extends Canvas{
	private static final long serialVersionUID = 1L;

	//Constructor
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //botao de fechar
		frame.setResizable(false); //pode dar problemas em caso true
		frame.setLocationRelativeTo(null);// janela no meio da tela
		frame.add(game);
		frame.setVisible(true); //ver o frame
		game.start();
	}

}
