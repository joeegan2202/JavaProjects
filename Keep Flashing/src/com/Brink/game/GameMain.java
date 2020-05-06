package com.Brink.game;

import com.Brink.engine.*;
import com.Brink.game.player.PlayerMain;

public class GameMain {
	
	public GameMain(){
		
	}
	
	public void init(){
		KeepFlashing.objects.add(new PlayerMain(400, 400, 64, 64, ObjectId.Player, 0));
		KeepFlashing.objects.add(new PlayerMain(500, 500, 64, 64, ObjectId.Player, 0));
		KeepFlashing.gHand.init();
	}
	
}
