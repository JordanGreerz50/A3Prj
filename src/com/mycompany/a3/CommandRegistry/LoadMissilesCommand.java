package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public class LoadMissilesCommand extends Command  {
	private GameWorld gw;
	public LoadMissilesCommand(GameWorld gw) {
		super("refueled missile");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.loadMissile();
		System.out.println("ship refueled!");
	}
	
}
