package com.Brink.engine;

public abstract class GameObject {
	
	protected float x, y, w, h;
	protected ObjectId id;
	
	public GameObject(float x, float y, float w, float h, ObjectId id){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
	}

	public abstract void tick();
	public abstract void render();
	
	protected void setId(ObjectId id) {
		this.id = id;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setW(float w) {
		this.w = w;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getW() {
		return w;
	}

	public float getH() {
		return h;
	}

	public ObjectId getId() {
		return id;
	}
	
}
