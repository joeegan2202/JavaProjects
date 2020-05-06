package com.Brink.engine.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Draw {
	
	public Draw(){
		
	}
	
	public static void drawQuad(Texture tex, double x, double y, double w, double h, int rot){
		glBindTexture(GL_TEXTURE_2D, tex.ID);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 1); glVertex2f((float)x, (float)y);
			glTexCoord2f(1, 1); glVertex2f((float)x + (float)w, (float)y);
			glTexCoord2f(1, 0); glVertex2f((float)x + (float)w, (float)y + (float)h);
			glTexCoord2f(0, 0); glVertex2f((float)x, (float)y + (float)h);
		}
		glEnd();
	}
	
}
