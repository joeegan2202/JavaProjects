package com.Brink.Test;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.File;
import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GLContext;

public class Main extends GLFWKeyCallback implements Runnable {
	
	private boolean running = false;
	
	public static boolean[] keys = new boolean[65536];
	
	private int testID;
	
	private int width = 1280;
	private int height = 720;
	
	private Thread thread;
	
	private long window;
	
	public void run(){
		init();
		while(running){
			tick();
			render();
			
			if(glfwWindowShouldClose(window) == GL_TRUE)
				running = false;
		}
	}
	
	private void init(){
		if(glfwInit() != GL_TRUE){
			return;
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width, height, "Test", NULL, NULL);
		if(window == NULL){
			// TODO: Handle
			return;
		}
		
		ByteBuffer vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (GLFWvidmode.width(vidMode) - width) / 2, (GLFWvidmode.height(vidMode) - height) / 2);
		
		glfwSetKeyCallback(window, this);
		
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
		
		glClearColor(0, 1, 1, 1);
		glColor4f(1, 1, 1, 1);
		glDisable(GL_DEPTH_TEST);
		System.out.println("OpenGl: " + glGetString(GL_VERSION));
		
		testID = TextureHandler.load("res" + File.separator + "Test.png");
	}
	
	public void start(){
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
	
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action != GLFW.GLFW_RELEASE;
		
	}
	
	private void tick(){
		glfwPollEvents();
		
		if(keys[GLFW_KEY_SPACE]){
			System.out.println("Space");
		}
		
	}
	
	private void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		
		glColor4f(1, 0, 1, 1);
		glBegin(GL_QUADS);
		{
			glVertex2f(0, 0);
			glVertex2f(0, 50);
			glVertex2f(50, 50);
			glVertex2f(50, 0);
		}
		glEnd();
		
		glColor4f(1, 1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, testID);
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
	
}