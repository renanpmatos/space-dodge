package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

// parecido com o basicEnemy, mas com uma velocidade e direção aleatória
public class HardEnemy extends GameObject{

	private Random r = new Random();
	private Image enemy;
	
	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		velX = 5;
		velY = 5;
	}
	
	//colisao do hardEnemy
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32); 
	}
	
	//carregar imagem
	private void loadImage() {
		ImageIcon iid = new ImageIcon("src/resources/hard-enemy.gif");
        enemy = iid.getImage();
	}
	public void tick() {
		x += velX;
		y += velY;
		 
		// evita a colisao com a janela e define uma velocidade nova
		if(y <= 0 || y >= Game.HEIGHT - 32) { 
			if(velY < 0) velY = -(r.nextInt(7)+1)*-1; 
			else velY = (r.nextInt(7)+1)*-1;
		} 
		if(x <= 0 || x >= Game.WIDTH - 16) {  
			if(velX < 0) velX = -(r.nextInt(7)+1)*-1;
			else velX = (r.nextInt(7)+1)*-1;
		}
		
	}
	
	public void render(Graphics g) {
		loadImage();
		g.drawImage(enemy, Math.round(x), Math.round(y), null);
        Toolkit.getDefaultToolkit().sync();
	}
	
}
