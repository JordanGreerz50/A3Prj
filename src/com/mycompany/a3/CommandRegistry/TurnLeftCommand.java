package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public final class TurnLeftCommand extends Command  {
	private GameWorld gw;
	public TurnLeftCommand(GameWorld gw) {
		super("left turn");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.leftTurn();
		System.out.println("turned left");
	}
	
}