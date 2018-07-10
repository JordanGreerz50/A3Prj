package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public final class ClockCommand extends Command  {
	private GameWorld gw;
	public ClockCommand(GameWorld gw) {
		super("clock tick");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.clockTick();
		System.out.println("tock tick!");
	}
	
}