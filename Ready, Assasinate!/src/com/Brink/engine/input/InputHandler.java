package com.Brink.engine.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler extends GLFWKeyCallback {
	
	public static boolean[] keys = new boolean[65536];
	
	public InputHandler(){
		
	}
	
	public void invoke(long window, int key, int scancode, int action, int mods){
		keys[key] = action != GLFW_RELEASE;
	}
	
}
