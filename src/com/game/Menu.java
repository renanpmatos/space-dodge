package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.Game.STATE;


// todos os menus do jogo, variando de acordo com o Game.STATE
public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random(); 
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	// Evento de click do mouse
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(); 
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, 290, 150, 200, 64)) {
				Game.gameState = STATE.Select;
				return;
			}
			
			//help button
			if(mouseOver(mx, my, 290, 250, 200, 64)) {
				Game.gameState = STATE.Help;
			}
			
			//exit button
			if(mouseOver(mx, my, 290, 350, 200, 64)) {
				System.exit(1);
			}	
		}
		
		if(Game.gameState == STATE.Select) {
			//normal button
			if(mouseOver(mx, my, 290, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));	
				
				game.diff = 0;
			}
			
			//hard button
			if(mouseOver(mx, my, 290, 250, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));	
				
				game.diff = 1;
			}
			
			//back button
			if(mouseOver(mx, my, 290, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			}	
		}
		
		//back button for help
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 290, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			}
		}
		//try again button
		if(Game.gameState == STATE.End || Game.gameState == STATE.Win) {
			if(mouseOver(mx, my, 290, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				
			}
		}
	}
	
	// testa se o click do mouse foi na area esfecificada (nos botoes)
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else return false;
		}else
			return false;
	}
	
	public void tick()
	{	
		
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
		g.fillRect(x+width-thick, y ,thick, height);
		
		// bottom
		g.fillRect(x, y + height - thick, width, thick);
		
		// dentro do retângulo
		g.setColor(fillColor);
		g.fillRect(x + thick, y + thick, width - (2*thick), height - (2*thick));
		
	}
	
	public void render(Graphics g) {
		
		if(Game.gameState == STATE.Menu) {
			Font fnt = new Font("Verdana", Font.BOLD, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SPACE DODGE", 210, 80);

			drawRectangle(g, 290, 150, 200, 64, 5, Color.white, Color.black);
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Play", 350, 190);
			

			drawRectangle(g, 290, 250, 200, 64, 5, Color.white, Color.black);
			g.setColor(Color.white);
			g.drawString("Help", 350, 290);

			drawRectangle(g, 290, 350, 200, 64, 5, Color.white, Color.white);
			g.setColor(Color.black);
			g.drawString("Exit", 350, 390);
			
		}
		else if(Game.gameState == STATE.Help)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", Game.WIDTH/2 - 80, 80);

			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Use AWSD or Arrow Keys to move player", 200, 200);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Press P for pause the game", 200, 240);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Press SPACE to access the Shop menu ", 200, 280);


			drawRectangle(g, 290, 350, 200, 64, 5, Color.white, Color.white);
			g.setColor(Color.black);
			g.setFont(fnt2);
			g.drawString("Back", 350, 390);	
		}
		else if(Game.gameState == STATE.End)
		{
			Font fnt = new Font("verdana", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 270, 80);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Your Score: "+hud.getScore(), Game.WIDTH /2 - 135, 250);


			drawRectangle(g, 290, 350, 200, 64, 5, Color.white, Color.white);
			g.setColor(Color.black);
			g.setFont(fnt2);
			g.drawString("Try Again", 320, 390);	
		}
		else if(Game.gameState == STATE.Win)
		{	
			Font fnt = new Font("verdana", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("You Win!!", 270, 80);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Your Score: "+hud.getScore(), Game.WIDTH /2 - 135, 250);


			drawRectangle(g, 290, 350, 200, 64, 5, Color.white, Color.white);
			g.setColor(Color.black);
			g.setFont(fnt2);
			g.drawString("Play Again", 320, 390);	
		}
		else if(Game.gameState == STATE.Select) {
			Font fnt = new Font("verdana", Font.BOLD, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 180, 80);

			drawRectangle(g, 290, 150, 200, 64, 5, Color.white, Color.black);
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Normal", 335, 190);
			

			drawRectangle(g, 290, 250, 200, 64, 5, Color.white, Color.black);
			g.setColor(Color.white);
			g.drawString("Hard", 350, 290);

			drawRectangle(g, 290, 350, 200, 64, 5, Color.white, Color.white);
			g.setColor(Color.black);
			g.drawString("Back", 350, 390);	
		}
		
	}
	
}
