package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public final class AddFlyingSaucerCommand extends Command {
	private GameWorld gw;
	public AddFlyingSaucerCommand(GameWorld gw) {
		
		super("Add flying saucer");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		gw.addFlyingSaucer();
		System.out.println("flying Saucer has been added");
	}
	
}
