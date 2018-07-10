package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public class AddSpeedCommand extends Command {
	private GameWorld gw;
	public AddSpeedCommand(GameWorld gw) {
		
		super("Add Speed");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		gw.increaseSpeed();
		System.out.println("speed increased");
	}
	
}
