package com.Brink.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GLContext;

import com.Brink.engine.input.InputHandler;
import com.Brink.game.ReadyAssasinate;
import com.Brink.game.Variables;

public class Game implements Runnable {
	
	public static ArrayList<Entity> active;
	public static ArrayList<Entity> menu;
	public static ArrayList<Entity> game;
	
	private boolean running = false;
	
	private Thread thread;
	
	private long window;
	
	public Game(){
		running = true;
		active = new ArrayList<Entity>();
		menu = new ArrayList<Entity>();
		game = new ArrayList<Entity>();
		thread = new Thread(this, "Ready, Assasinate!");
		thread.start();
	}
	
	public void run(){
		init();
		ReadyAssasinate.init();
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
				tick();
				if(glfwWindowShouldClose(window) == GL_TRUE)
					running = false;
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
	
	public void init(){
		if(glfwInit() != GL_TRUE){
			return;
		}
		
		ByteBuffer vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(Variables.width, Variables.height, "Test", NULL, NULL);
		if(window == NULL){
			return;
		}
		
		glfwSetKeyCallback(window, new InputHandler());
		
		glfwSetWindowPos(window, (GLFWvidmode.width(vidMode) - Variables.width) / 2, (GLFWvidmode.height(vidMode) - Variables.height) / 2);
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		GLContext.createFromCurrent();
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Variables.width, 0, Variables.height, -1, 1);
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_ALPHA_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glClearColor(0, 0, 0, 1);
		glDisable(GL_DEPTH_TEST);
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		
		glfwSetFramebufferSizeCallback(window,
	         Variables.fbCallback = new GLFWFramebufferSizeCallback() {
	         public void invoke(long window, int w, int h) {
	             if (w > 0 && h > 0) {
	                 Variables.width = w;
	                 Variables.height = h;
	             }
	         }
	     });
		
		swapLists(menu);
		
	}
	
	public void tick(){
		glfwPollEvents();
		
		for (Entity entity : active) {
			entity.tick();
		}
		
	}
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		Variables.fbCallback.invoke(window, Variables.width, Variables.height);
		
		for (Entity entity : active) {
			entity.render();
		}
		
		glfwSwapBuffers(window);
	}
	
	public void swapLists(ArrayList<Entity> list){
		active = list;
	}
	
}
