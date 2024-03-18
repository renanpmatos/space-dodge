package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

// classe para o tiro do boss
public class EnemyBossBullet extends GameObject{

	private Handler handler; 
	Random r = new Random();
	private Image bossBullet;
	
	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;
		
		// direção do tiro
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
		
	}
	
	// colisão do tiro
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16); 
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// caso saia da janela, remove o tiro
		if(y >= Game.HEIGHT) handler.removeObject(this); 
	}
	
	// carrega a imagem
	private void loadImage() {
		ImageIcon iid = new ImageIcon("src/resources/boss-bullet.gif");
        bossBullet = iid.getImage();
	}
	
	public void render(Graphics g) {
		loadImage();
		g.drawImage(bossBullet, Math.round(x), Math.round(y), null);
		Toolkit.getDefaultToolkit().sync();
	}
	
}
