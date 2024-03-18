package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

// inimigo básico do jogo
public class BasicEnemy extends GameObject{

	private Image enemy;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		velX = 5;
		velY = 5;
		
	}
	
	// carrega imagem do basicEnemy
	private void loadImage() {
		ImageIcon iid = new ImageIcon("src/resources/normal-enemy.gif");
        enemy = iid.getImage();
	}
	
	// colisao do inimigo
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32); 
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// colisao com a janela
		if(y <= 0 || y >= Game.HEIGHT - 85) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 60) velX *= -1; 
	}
	
	public void render(Graphics g) {
		loadImage();
		g.drawImage(enemy, Math.round(x), Math.round(y), null);
        Toolkit.getDefaultToolkit().sync();

	}
	
}
