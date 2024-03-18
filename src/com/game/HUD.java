package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// inteface do jogador (vida, nivel e score)
public class HUD {

	public int bounds = 0;
	public static float HEALTH = 100;
	private float greenValue = 255; 
	
	private int score = 0;
	private int auxScore = 0;
	private int level = 1;
	
	public void tick() {
		
		HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds/2));
		
		// muda o valor conforme a vida
		greenValue = HEALTH*2;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		// aumenta o score depois de 50 ticks
		if(auxScore == 50) {
			score++;
			auxScore = 0;
		}
		
		auxScore++;
	}
	
	
	// renderizar a hud
	public void render(Graphics g){
		Font fnt1 = new Font("arial", 1, 20);
		
		g.setColor(Color.white);
		
		g.fillRect(15,5,210 + bounds,42);
		g.setColor(new Color(150, (int)greenValue, 0));
		g.fillRect(20,10, (int)HEALTH*2,32);
		g.setColor(Color.white);
		
		g.setFont(fnt1);
		g.drawString("Score: " + score, 35, 80);
		g.drawString("Level: " + level, 35, 110);
		g.drawString("Space for Shop ", 35, 142);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score; 
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
