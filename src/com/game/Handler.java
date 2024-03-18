package com.game;

import java.awt.Graphics;
import java.util.LinkedList;

// classe para manipular os objetos do jogo
public class Handler {

	// lista de objetos adicionados durante jodo
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	// velocidade padrao
	public int spd = 5;
	
	// executa o tick em todos os objetos
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	// executa o render em todos os objetos
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	// remove todos os inimigos (exceto o boss), adcionando um novo player
	public void clearEnemys() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				object.clear();
				if (Game.gameState != Game.STATE.End)
				addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
			}
		}
	}
	
	// remove o boss
	public void clearBoss() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				object.clear();
			}
		}
	}
	
	// remove o player
	public void clearPlayer() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player || Game.gameState == Game.STATE.Win)
			{
				object.clear();
			}
		}
	}
	
	// adiciona um GameObject na lista
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	// remove um GameObject 
	@SuppressWarnings("unlikely-arg-type")
	public void removeObject(GameObject obeject) {
		this.object.remove(object);
	}
}
