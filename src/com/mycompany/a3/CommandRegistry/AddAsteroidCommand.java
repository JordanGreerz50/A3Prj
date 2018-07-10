package com.mycompany.a3.CommandRegistry;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public final class AddAsteroidCommand extends Command {
	private GameWorld gw;
	public AddAsteroidCommand(GameWorld gw2) {
		
		super("Add Asteroid");
		this.gw = gw2;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		gw.addAsteroid();
		System.out.println("asteroid has been added");
	}
	
}
