package com.mycompany.a3.CommandRegistry;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command {
	
	private GameWorld gw;
	public SoundCommand(GameWorld gw) {
		super("Sound Option");
		this.gw = gw;
	}


	@Override
	public void actionPerformed(ActionEvent evt){
	  if (((CheckBox)evt.getComponent()).isSelected())//getComponent() returns the component 					        //that generated the event
	    gw.soundOn();
	  else
	    gw.soundOff();
	  }//actionPerformed
}