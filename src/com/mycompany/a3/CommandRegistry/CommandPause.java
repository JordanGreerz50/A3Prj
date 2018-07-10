package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public final class CommandPause extends Command {
	private Game game;
	public CommandPause(Game gw) {
		super("Paused Game");
		this.game = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		 if(game.isPaused()){ 

             game.resumeGame(); 

         } 

         else if(!game.isPaused()){ 

             game.PauseGame(); 

         } 
	}
}
