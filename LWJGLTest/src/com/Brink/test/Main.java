package com.Brink.test;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class Main {
	
	public static void main(String[] args){
		
		
		
		
		try {
			Display.setDisplayMode(new DisplayMode(1600, 850));
			Display.create();
			Display.setResizable(true);
			Display.setTitle("Test Game");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		System.out.println("Working");
		while(!Display.isCloseRequested()){
			
		}
		Display.destroy();
	}
	
}