package com.Brink.game;

import java.io.File;

import com.Brink.engine.EntityID;
import com.Brink.engine.Game;
import com.Brink.engine.graphics.Texture;
import com.Brink.game.menu.MainTitle;
import com.Brink.game.player.PlayerMain;

public class ReadyAssasinate {
	
	public static void main(String[] args){
		new Game();
	}
	
	public static void init(){
		PlayerMain player = new PlayerMain(64, 64, 100, 100, EntityID.Player, new Texture("Player", "res" + File.separator + "Player.png"));
		MainTitle mainT = new MainTitle(512, 128, (Variables.width / 2) - 256, Variables.height, EntityID.Terrain, new Texture("Main Title", "res" + File.separator + "MainTitle.png"));
		
		Game.menu.add(mainT);
		Game.game.add(player);
	}
	
}
