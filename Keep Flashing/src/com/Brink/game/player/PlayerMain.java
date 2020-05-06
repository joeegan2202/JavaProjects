package com.Brink.game.player;

import com.Brink.engine.*;

public class PlayerMain extends GameObject {
	
	double tKey;
	
	public PlayerMain(float x, float y, float w, float h, ObjectId id, double tkey) {
		super(x, y, w, h, id);
		tKey = tkey;
	}
	
	public void tick(){
		
	}
	
	public void render(){
		GraphicsHandler.draw(tKey, x, y, w, h);
	}
	
}
