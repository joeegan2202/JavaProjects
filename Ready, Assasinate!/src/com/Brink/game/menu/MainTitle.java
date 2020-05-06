package com.Brink.game.menu;

import com.Brink.engine.Entity;
import com.Brink.engine.EntityID;
import com.Brink.engine.graphics.Draw;
import com.Brink.engine.graphics.Texture;
import com.Brink.game.Variables;

public class MainTitle extends Entity {
	Texture tex;
	public MainTitle(double w, double h, double x, double y, EntityID id, Texture tex){
		super(w, h, x, y, id);
		this.tex = tex;
	}
	
	public void tick(){
		y = Variables.height;
		x = (Variables.width / 2) - (w / 2);
	}
	
	public void render(){
		System.out.println("Rendering");
		Draw.drawQuad(tex, x, y, w, h, 0);
	}
	
}
