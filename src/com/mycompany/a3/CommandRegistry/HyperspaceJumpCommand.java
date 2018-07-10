package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public final class HyperspaceJumpCommand extends Command  {
	private GameWorld gw;
	public HyperspaceJumpCommand(GameWorld gw) {
		super("hyperspace Jump");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.hyperJump();
		System.out.println("ship returned to center!");
	}
	
}
