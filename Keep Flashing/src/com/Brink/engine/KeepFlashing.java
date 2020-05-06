package com.Brink.engine;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

import com.Brink.game.GameMain;

public class KeepFlashing {
	
	private boolean running = false;
	
	public static ArrayList<GameObject> objects;
	
	public static GraphicsHandler gHand;
	public Camera cam;
	
	public boolean isRunning(){
		return running;
	}
	
	public static void main(String[] args){
		KeepFlashing game = new KeepFlashing();
		GameMain gMain = new GameMain();
		gHand = new GraphicsHandler();
		game.running = true;
		objects = new ArrayList<GameObject>();
		game.cam = new Camera();
		game.initSystem();
		gMain.init();
		game.gameLoop();
	}
	
	private void initSystem(){
		try {
			Display.setDisplayMode(new DisplayMode(1600, 800));
			Display.setResizable(true);
			Display.setLocation(0, 0);
			Display.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glClearColor(0,0,0,1);

		glDisable(GL_DEPTH_TEST);
	}
	
	private void gameLoop(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				if(Display.isCloseRequested()){
					shutDown();
				}
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick(){
		cam.update();
		for (GameObject go : cam.active) {
			go.tick();
		}
	}
	
	private void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		for (GameObject go : cam.active) {
			go.render();
		}
		Display.update();
	}
	
	private void shutDown(){
		Display.destroy();
		Keyboard.destroy();
	}
	
}
