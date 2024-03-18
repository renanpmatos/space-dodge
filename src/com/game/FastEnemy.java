package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

// inigmigo rápido
public class FastEnemy extends GameObject{
	
	private Image fastEnemy;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		velX = 4;
		velY = 9;
		
		// carrega a imagem para mudança conforme a colisao
		fastEnemy = loadImage();
		imageDirection = 64;
		
	}
	
	// colisao do fastEnemy
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32); 
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// colisao com a janela, muda a direcao da imagem
		if(y <= 0 || y >= Game.HEIGHT) {
			velY *= -1;
			imageDirection *= -1;
			
		}
		if(x <= 0 || x >= Game.WIDTH - 60) velX *= -1; 
		
	}
	
	// carrega imagem
	private Image loadImage() {
		ImageIcon iid = new ImageIcon("src/resources/fast-enemy.gif");
        return fastEnemy = iid.getImage();
	}

	public void render(Graphics g) {
		g.drawImage(fastEnemy, Math.round(x), Math.round(y), 64, imageDirection, null);
	    Toolkit.getDefaultToolkit().sync();
	}
	
}

