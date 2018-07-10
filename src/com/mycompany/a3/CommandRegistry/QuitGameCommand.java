package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld; 


public final class QuitGameCommand extends Command  {
	private GameWorld gw;
	public QuitGameCommand(GameWorld gw) {
		super("quit Game");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		boolean b = Dialog.show("QUIT pressed", "do you really want to quit this amazing game?", "yes?","no");
		if(b)
			Display.getInstance().exitApplication();
		//gw.quitGame();
		//System.out.println("goodbye!");
	}
	
}
