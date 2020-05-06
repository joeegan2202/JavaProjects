package com.Brink.engine.input;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyHandler extends GLFWKeyCallback {
	
	public static boolean[] keys = new boolean[65536];
	
	public KeyHandler(){
		System.out.println("Initing");
	}
	
	public static boolean isKeyDown(int key){
		return keys[key];
	}
	
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action != GLFW_RELEASE;
	}
	
}
