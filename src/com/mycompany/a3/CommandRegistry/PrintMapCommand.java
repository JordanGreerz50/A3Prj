package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public final class PrintMapCommand extends Command {
	private GameWorld gw;
	public PrintMapCommand(GameWorld gw) {
		super("view Map");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.printMap();
		System.out.println("heres the map!");
	}
	
}
