package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnRightCommands extends Command  {
	private GameWorld gw;
	public TurnRightCommands(GameWorld gw) {
		super("rightturn");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.rightTurn();
		System.out.println("turned right");
	}
	
}