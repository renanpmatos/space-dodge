package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

// inimigo inteligente, segue o player
public class SmartEnemy extends GameObject{

	private GameObject player;
	private Image smartEnemy;
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		// pega apenas o player na lista
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}
		
	}
	
	// define a colisao do smartEnemy
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32); 
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// funcao para perseguir o player conforme sua posicao
		float diffX = x - player.getX() - 12;
		float diffY = y - player.getY() - 12;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		velX = ((-1/distance) * diffX);
		velY = ((-1/distance) * diffY); 
				
		// colisao com a janela
		if(y <= 0 || y >= Game.HEIGHT - 85) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 60) velX *= -1; 
	}
	
	// carrega a imagem do SmartEnemy
	private void loadImage() {
		ImageIcon iid = new ImageIcon("src/resources/smart-enemy.gif");
        smartEnemy = iid.getImage();
	}
	
	// renderiza o SmartEnemy
	public void render(Graphics g) {
		loadImage();
		g.drawImage(smartEnemy, Math.round(x), Math.round(y), null);
        Toolkit.getDefaultToolkit().sync();
	}
	
}

