package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

// classe do boss, a posicao não é aleatória
public class EnemyBoss extends GameObject{

	private Handler handler; 
	Random r = new Random();
	private Image enemyBoss;
	
	// eventos para quando o boss é iniciado
	private int timer = 80;
	private int timer2 = 50;
	private int timer3 = 30;
	
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		velX = 0;
		velY = 2;
	}
	
	// colisão do boss
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,128,128); 
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// timer 1
		if(timer <= 0)
		{
			velY = 0;
		}
		else timer--;
		
		
		// timer 2
		if(timer <= 0) {
			timer2--;
		}
		if(timer2 <= 0) 
		{
			// faz o boss descer e muda sua velocidade
			if(velX == 0) velX = 2;
			
			if(velX > 0) velX += 0.01f;
			
			else if(velX < 0) velX -= 0.01f;
			
			velX = Game.clamp(velX, -10, 10);
			
			// frequencia de spawn do tiro
			int spawn = r.nextInt(4);
			if(spawn == 0)
			{
				handler.addObject(new EnemyBossBullet((int)x + 80, (int)y + 80, ID.HardEnemy, handler));
				
			}
		}
		// timer 3
		if(timer2 <= 0) {
			timer3--;
		}
		
		// colisao horizontal com a janela
		if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1; 
	}
	
	// carrega a imagem
	private void loadImage() {
		ImageIcon iid = new ImageIcon("src/resources/boss.png");
        enemyBoss = iid.getImage();
	}
	
	public void render(Graphics g) {
		loadImage();
		g.drawImage(enemyBoss, Math.round(x), Math.round(y), 196,196,null);
        Toolkit.getDefaultToolkit().sync();
	}
	
}
