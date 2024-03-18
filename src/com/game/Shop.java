package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// classe da loja, executada durante o jogo
public class Shop extends MouseAdapter {

	Handler handler;
	HUD hud;

	private int B1 = 10; // preço botão 1
	private int B2 = 5; // preço botão 2
	private int B3 = 10; // preço botão 3

	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	// desenha um retângulo
	public void drawRectangle(Graphics g, int x, int y, int width, int height, int thick, Color borderColor, Color fillColor) {

		// borda do retângulo
		g.setColor(borderColor);

		// top
		g.fillRect(x, y, width, thick);

		// left
		g.fillRect(x, y, thick, height);

		// right
		g.fillRect(x + width - thick, y, thick, height);

		// bottom
		g.fillRect(x, y + height - thick, width, thick);

		// dentro do retângulo
		g.setColor(fillColor);
		g.fillRect(x + thick, y + thick, width - (2 * thick), height - (2 * thick));

	}

	public void render(Graphics g) {
		
		// título
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 48));
		g.drawString("SHOP", Game.WIDTH / 2 - 80, 130);

		// ---------------------------------------------------------

		// Opção 1 - Upgrade vida
		drawRectangle(g, 100, 200, 180, 140, 5, Color.white, Color.black);
		g.setColor(Color.white);

		g.setFont(new Font("arial", 0, 22));
		g.drawString("Upgrade Health", 115, 260);

		g.setFont(new Font("arial", 0, 20));
		g.drawString(B1 + " Score Points", 120, 290);

		// Opção 2 - Upgrade velocidade
		drawRectangle(g, 310, 200, 180, 140, 5, Color.white, Color.BLACK);
		g.setColor(Color.white);

		g.setFont(new Font("arial", 0, 22));
		g.drawString("Upgrade Speed", 325, 260);

		g.setFont(new Font("arial", 0, 20));
		g.drawString(B2 + " Score Points", 330, 290);

		// Opção 3 - Recuperar vida
		drawRectangle(g, 520, 200, 180, 140, 5, Color.white, Color.black);
		g.setColor(Color.white);

		g.setFont(new Font("arial", 0, 22));
		g.drawString("Refill Health", 550, 260);

		g.setFont(new Font("arial", 0, 20));
		g.drawString(B3 + " Score Points", 535, 290);

		// ---------------------------------------------------------

		g.setColor(Color.white);

		g.setFont(new Font("arial", 0, 30));
		g.drawString("SCORE: " + hud.getScore(), Game.WIDTH / 2 - 70, Game.HEIGHT - 200);

		g.setFont(new Font("arial", 0, 24));
		g.drawString("Press Space to go back", Game.WIDTH / 2 - 130, Game.HEIGHT - 150);
	}

	// Evento de click nos botões
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		// Opção 1 - Upgrade vida
		if (mx >= 100 && mx <= 280) {
			if (my >= 200 && my <= 340) {
				if (hud.getScore() >= B1) {
					hud.setScore(hud.getScore() - B1);
					B1 += 10;
					hud.bounds += 20;
					HUD.HEALTH = (100 + (hud.bounds / 2));
				}
			}
		}

		// Opção 2 - Upgrade velocidade
		if (mx >= 310 && mx <= 490) {
			if (my >= 200 && my <= 340) {
				if (hud.getScore() >= B2) {
					hud.setScore(hud.getScore() - B2);
					B2 += 10;
					handler.spd++;
				}
			}
		}

		// Opção 3 - Recuperar vida
		if (mx >= 520 && mx <= 700) {
			if (my >= 200 && my <= 340) {
				if (hud.getScore() >= B3) {
					hud.setScore(hud.getScore() - B3);
					B1 += 10;
					B3 += 10;
					HUD.HEALTH = (100 + (hud.bounds / 2));
				}
			}
		}

	}
}
