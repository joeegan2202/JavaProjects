package com.Brink.engine;

public abstract class Entity {
	
	protected double w, h, x, y;
	protected EntityID id;
	
	public Entity(double w, double h, double x, double y, EntityID id){
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render();
	
}
