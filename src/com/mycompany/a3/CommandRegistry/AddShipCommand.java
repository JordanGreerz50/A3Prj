package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public final class AddShipCommand extends Command  {
	private GameWorld gw;
	public AddShipCommand(GameWorld gw) {
		
		super("Add Ship");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		gw.addShip();
		System.out.println("ship has been added");
	}
	
}
