package com.Brink.game.player;

import static org.lwjgl.glfw.GLFW.*;

import com.Brink.engine.Entity;
import com.Brink.engine.EntityID;
import com.Brink.engine.graphics.Draw;
import com.Brink.engine.graphics.Texture;
import com.Brink.engine.input.InputHandler;

public class PlayerMain extends Entity {
	
	public Texture tex;
	
	public PlayerMain(double w, double h, double x, double y, EntityID id, Texture tex){
		super(w, h, x, y, id);
		this.tex = tex;
	}
	
	public void tick(){
		if(InputHandler.keys[GLFW_KEY_UP])
			y += 2;
		if(InputHandler.keys[GLFW_KEY_DOWN])
			y -= 2;
		if(InputHandler.keys[GLFW_KEY_LEFT])
			x -= 2;
		if(InputHandler.keys[GLFW_KEY_RIGHT])
			x += 2;
			
	}
	
	public void render(){
		Draw.drawQuad(tex, x, y, w, h, 0);
	}
	
}
