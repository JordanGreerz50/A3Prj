package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public final class AddSpaceStationCommand extends Command  {
	private GameWorld gw;
	public AddSpaceStationCommand(GameWorld gw) {
		
		super("Add Space Station");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		gw.addBlinkingStation();
		System.out.println("space station has been added");
	}
	
}
