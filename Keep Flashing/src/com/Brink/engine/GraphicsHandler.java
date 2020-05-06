package com.Brink.engine;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.util.ArrayList;

import org.newdawn.slick.opengl.*;

public class GraphicsHandler {
	
	public static ArrayList<TexKey> keyReg;
	
	public GraphicsHandler(){
		keyReg = new ArrayList<TexKey>();
	}
	
	public void init(){
		GraphicsHandler.addRegistry(0, "res/PlayerFront.png");
	}
	
	public static void addRegistry(double key, String texPath){
		Texture tempTex = null;
		try {
			tempTex = TextureLoader.getTexture("PNG", new FileInputStream(new File(texPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		keyReg.add(new TexKey(key, tempTex));
	}
	
	public static void draw(double key, float x, float y, float w, float h){
		TexKey tTKey = null;
		for (TexKey texKey : keyReg) {
			if(texKey.key == key){
				tTKey = texKey;
			}
		}
		if(tTKey == null){
			System.out.println("Texture is null");
			return;
		}
		glTranslatef(x, y, 0);
		tTKey.tex.bind();
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 1); glVertex2f(0, 0);
			glTexCoord2f(0, 0); glVertex2f(0, h);
			glTexCoord2f(1, 0); glVertex2f(w, h);
			glTexCoord2f(1, 1); glVertex2f(w, 0);
		}
		glEnd();
	}
	
}
