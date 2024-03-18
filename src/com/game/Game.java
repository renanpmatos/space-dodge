package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.ImageIcon;

// classe principal do jogo
public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 840, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	public static boolean paused = false;
	
	// 0 = normal
	// 1 = hard
	public int diff = 0;

	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;

	// game states
	public enum STATE {
		Menu, Select, Help, Shop, Game, Win, End
	};

	public static STATE gameState = STATE.Menu;

	public Game() {

		// instancia as classes principais
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);

		new Window(WIDTH, HEIGHT, "Space Dodge", this);

		spawner = new Spawn(handler, hud, this);

		r = new Random();

		// adiociona os objetos basicos na fase 1
		if (gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 80), r.nextInt(Game.HEIGHT - 80), ID.BasicEnemy, handler));
		} else {
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
	}

	// começa o jogo
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// parar o jogo
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {

		// função básica de FPS para manter controle

		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	// contagem de ticks de acordo com o state
	private void tick() {

		if (gameState == STATE.Game) {
			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();

				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemys();
					for (int i = 0; i < 20; i++) {
						handler.addObject(
								new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}
		}

		else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select
				|| gameState == STATE.Win) {
			menu.tick();
			handler.tick();
		}
	}

	private void render() {
		
		// cria um strategy para o Graphics 
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// define o plano de fundo e o retangulo em volta da tela
		ImageIcon iid = new ImageIcon("src/resources/space-background.png");
		Image background = iid.getImage();
		g.drawImage(background, 0, 0, WIDTH, HEIGHT + 128, null);
		drawRectangle(g, 15, 0, Game.WIDTH - 50, Game.HEIGHT - 50, 5, Color.darkGray, new Color(0, 0, 0, 0));

		// jogo pausado
		if (paused) {
			g.setColor(Color.gray);
			g.setFont(new Font("arial", Font.BOLD, 48));
			g.drawString("PAUSED", Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 80);
		}

		// renderiza os objetos e eventos durante o jogo
		if (gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		}
		// renderiza a loja
		else if (gameState == STATE.Shop) {
			shop.render(g);
		}
		// renderiza os outros estados de jogo
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End
				|| gameState == STATE.Select || gameState == STATE.Win) {
			menu.render(g);
			handler.render(g);
		}

		g.dispose();
		bs.show();
	}

	// colisão da tela com o jogador
	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	// desenha um retângulo
	public void drawRectangle(Graphics g, int x, int y, int width, int height, int thick, Color borderColor,
			Color fillColor) {

		// borda do retângulo
		g.setColor(borderColor);

		// top
		g.fillRect(x, y, width, thick);

		// left
		g.fillRect(x, y, thick, height);

		// right
		g.fillRect(x + width - thick, y, thick, height);

		// bottom
		g.fillRect(x, y + height - thick, width, thick);

		// dentro do retângulo
		g.setColor(fillColor);
		g.fillRect(x + thick, y + thick, width - (2 * thick), height - (2 * thick));

	}

	// main function
	public static void main(String args[]) {
		new Game();
	}

}
