package com.game;

import java.awt.Graphics;
import java.awt.Rectangle;

// uma classe para criar objetos no jogo

public abstract class GameObject {

	protected float x, y; //usa protected para ser usado por outras classes (inheritence)
	protected ID id;
	protected float velX, velY;
	protected int imageDirection; // direcção da imagem (usado apenas no fastEnemy)
	
	//constructor
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(); // abstract para poder ser modificado nas outras classes
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	// gets e sets
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setId(ID id){
		this.id = id;
	}
	public ID getId(){
		return id;
	}
	public void setVelX(int velX){
		this.velX = velX;
	}
	public void setVelY(int velY){
		this.velY = velY;
	}
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
}
