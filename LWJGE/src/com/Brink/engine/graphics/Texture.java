package com.Brink.engine.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	
	public String name;
	public int ID;
	
	public Texture(String name, String path){
		this.name = name;
		BufferedImage tempImage;
		int[] pixels;
		try {
			tempImage = ImageIO.read(new FileInputStream(new File(path)));
			pixels = new int[tempImage.getWidth() * tempImage.getHeight()];
			
			tempImage.getRGB(0, 0, tempImage.getWidth(), tempImage.getHeight(), pixels, 0, tempImage.getWidth());

			ByteBuffer buffer = BufferUtils.createByteBuffer(tempImage.getHeight() * tempImage.getWidth() * 4);

            for(int y = 0; y < tempImage.getHeight(); y++){
                for(int x = 0; x < tempImage.getWidth(); x++){
                    int pixel = pixels[y * tempImage.getWidth() + x];
                    buffer.put((byte) ((pixel >> 16) & 0xFF));
                    buffer.put((byte) ((pixel >> 8) & 0xFF));
                    buffer.put((byte) (pixel & 0xFF));
                    buffer.put((byte) ((pixel >> 24) & 0xFF));
                }
            }

            buffer.flip();
            
            ID = glGenTextures();
            
            glBindTexture(GL_TEXTURE_2D, ID);
            
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, tempImage.getWidth(), tempImage.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            
            glBindTexture(GL_TEXTURE_2D, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
