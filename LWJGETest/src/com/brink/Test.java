package com.brink;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GLContext;

import com.Brink.engine.graphics.Texture;
import com.Brink.engine.input.KeyHandler;

public class Test implements Runnable {
	
	public static boolean running = true;
	
	public static int width = 700, height = 400;
	
	private Thread thread;
	
	private Texture tex;
	
	private long window;
	
    GLFWFramebufferSizeCallback fbCallback;
	
	public static void main(String[] args){
		new Test().start();
	}
	
	public void run(){
		init();
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
	
	private void tick(){
		glfwPollEvents();
		
		if(KeyHandler.isKeyDown(GLFW_KEY_SPACE)){
			System.out.println("Space");
		}
	}
	
	private void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		
		glBindTexture(GL_TEXTURE_2D, tex.ID);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 1); glVertex2f(100, 100);
			glTexCoord2f(1, 1); glVertex2f(150, 100);
			glTexCoord2f(1, 0); glVertex2f(150, 150);
			glTexCoord2f(0, 0); glVertex2f(100, 150);
		}
		glEnd();
		
		glfwSwapBuffers(window);
	}
	
	public void init(){
		if(glfwInit() != GL_TRUE){
			return;
		}
		
		ByteBuffer vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width, height, "Test", NULL, NULL);
		if(window == NULL){
			return;
		}
		
		glfwSetKeyCallback(window, new KeyHandler());
		
		glfwSetWindowPos(window, (GLFWvidmode.width(vidMode) - width) / 2, (GLFWvidmode.height(vidMode) - height) / 2);
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		GLContext.createFromCurrent();
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, 0, height, -1, 1);
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_ALPHA_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glClearColor(1, 1, 1, 1);
		glColor4f(1, 1, 1, 1);
		glDisable(GL_DEPTH_TEST);
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		
		glfwSetFramebufferSizeCallback(window,
	         fbCallback = new GLFWFramebufferSizeCallback() {
	         public void invoke(long window, int w, int h) {
	             if (w > 0 && h > 0) {
	                 width = w;
	                 height = h;
	             }
	         }
	     });
		
		tex = new Texture("test", "res/Test.png");
	}
	
	public void start(){
		running = true;
		thread = new Thread(this, "Test");
		thread.start();
	}
	
}
