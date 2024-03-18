package com.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.Game.STATE;

// input do teclado do player
public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4]; //para saber se a tecla foi apertada
	
	Game game;
	
	public KeyInput(Handler handler, Game game ) {
		this.handler = handler;
		this.game = game;
		
		keyDown[0]=false;
		keyDown[1]=false;
		keyDown[2]=false;
		keyDown[3]=false;
	} 
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // pega a tecla apertada
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//eventos de teclado pro player
				
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-handler.spd); keyDown[0]=true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(handler.spd); keyDown[1]=true; }
				if(key == KeyEvent.VK_D) { tempObject.setVelX(handler.spd); keyDown[2]=true; }
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-handler.spd); keyDown[3]=true; }

				if(key == KeyEvent.VK_UP) { tempObject.setVelY(-handler.spd); keyDown[0]=true; }
				if(key == KeyEvent.VK_DOWN) { tempObject.setVelY(handler.spd); keyDown[1]=true; }
				if(key == KeyEvent.VK_RIGHT) { tempObject.setVelX(handler.spd); keyDown[2]=true; }
				if(key == KeyEvent.VK_LEFT) { tempObject.setVelX(-handler.spd); keyDown[3]=true; }
				
			}
		}
		
		// evento para pausar
		if(key == KeyEvent.VK_P) {
			if(Game.gameState == STATE.Game) {
				if(Game.paused)Game.paused = false;
				else Game.paused = true;
			}
		}

		// evento para loja
		if(key == KeyEvent.VK_SPACE) {
			if(Game.gameState == STATE.Game) Game.gameState = STATE.Shop;
			else if(Game.gameState == STATE.Shop) Game.gameState = STATE.Game;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode(); // pega a tecla que foi soltada
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//eventos de teclado pro player
				if(key == KeyEvent.VK_W) keyDown[0]=false;
				if(key == KeyEvent.VK_S) keyDown[1]=false; 
				if(key == KeyEvent.VK_D) keyDown[2]=false; 
				if(key == KeyEvent.VK_A) keyDown[3]=false; 
				

				if(key == KeyEvent.VK_UP) { keyDown[0]=false; }
				if(key == KeyEvent.VK_DOWN) {  keyDown[1]=false; }
				if(key == KeyEvent.VK_RIGHT) { keyDown[2]=false; }
				if(key == KeyEvent.VK_LEFT) { keyDown[3]=false; }
				
				//moveimento vertical
				if(!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);
				}
				//movimento horizontal 
				if(!keyDown[2] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			}
			
		}
		
	}
}
