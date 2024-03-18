package com.game;

import java.util.Random;

import com.game.Game.STATE;

// classe de spawn, cria inimigos de acordo com o nivel do jogo
public class Spawn {

	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep++;
		
		// aumenta o nivel a cada 500 ticks
		if(scoreKeep >= 500) {	
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			//dificuldade normal
			if(game.diff == 0) {
			
				if(hud.getLevel() == 2) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 3) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 4) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.BasicEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.FastEnemy, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel() == 10) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -160,  ID.EnemyBoss, handler));
				}
				else if(hud.getLevel() == 14) {
					Game.gameState = STATE.Win;
					handler.clearEnemys();
					handler.clearBoss();
					handler.clearPlayer();
				}	
				
			// dificuldade dificil	
			}else if(game.diff == 1) {
				if(hud.getLevel() == 2) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.HardEnemy, handler));
				}
				else if(hud.getLevel() == 3) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.HardEnemy, handler));
				}
				else if(hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.FastEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 5) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.BasicEnemy, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.FastEnemy, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel() == 10) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -160,  ID.EnemyBoss, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.SmartEnemy, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel() == 15) {
					Game.gameState = STATE.Win;
					handler.clearEnemys();
					handler.clearBoss();
					handler.clearPlayer();
				}
			}
			
			
		}
	}
	
}
