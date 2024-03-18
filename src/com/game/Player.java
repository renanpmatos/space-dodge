package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	private Image player;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	// define colisão do player
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16); 
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		//colisao com a janela
		x = Game.clamp(x, 0, Game.WIDTH - 70);
		y = Game.clamp(y, 0, Game.HEIGHT - 100);
		
		collision();
	}
	
	// carrega imagem do player
	private void loadImage() {
		ImageIcon iid = new ImageIcon("src/resources/main-ship.gif");
        player = iid.getImage();
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);

			// colisao BsicEnemy e FastEnemy
			if(tempObject.getId() == ID.BasicEnemy  || tempObject.getId() == ID.FastEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 3;
				}
			}
			// colisao SmartEnemy e HardEnemy
			if(tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.HardEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 6;
				}
			}
			// colisao boss 
			if(tempObject.getId() == ID.EnemyBoss) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 9;
				}
			}
			
		}
	}
	
	public void render(Graphics g) {
		loadImage();
		g.drawImage(player, Math.round(x), Math.round(y), null);
        Toolkit.getDefaultToolkit().sync();

	}
	

}
