package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{

	Random r = new Random();
	private Color col;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		// velocidade alatoria
		velX = (r.nextInt(7 - -7) + -7);
		velY = (r.nextInt(7 - -7) + -7);
		
		if(velX == 0) velX = 1;
		if(velY == 0) velY = 1;
		
		// cor aleatoria
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	// define um retangulo
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16); 
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// colisao com a janela
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1; 
	}
	
	// carrega o retangulo
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
}

