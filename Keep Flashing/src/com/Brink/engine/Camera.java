package com.Brink.engine;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public class Camera {
	
	private int x, y;
	
	public ArrayList<GameObject> inactive;
	public ArrayList<GameObject> active;
	
	public Camera(){
		inactive = new ArrayList<GameObject>();
		active = new ArrayList<GameObject>();
		update();
	}
	
	public void update(){
		inactive.clear();
		active.clear();
		x = Display.getWidth();
		y = Display.getHeight();
		if(KeepFlashing.objects != null){
			for (GameObject go : KeepFlashing.objects) {
				if((go.x >= 0 && go.x < x) && (go.y >= 0 && go.y < y)){
					active.add(go);
				} else {
					inactive.add(go);
				}
			}
		} else {
			System.out.println("objects is null");
			return;
		}
	}
	
}
