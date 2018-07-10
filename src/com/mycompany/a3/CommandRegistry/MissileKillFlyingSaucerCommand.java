package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public class MissileKillFlyingSaucerCommand extends Command  {
	private GameWorld gw;
	public MissileKillFlyingSaucerCommand(GameWorld gw) {
		super("SAUCER kill");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.saucerDestroyed();
		System.out.println("UFO Destroyed!");
	}
	
}