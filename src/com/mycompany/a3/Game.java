package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.CommandRegistry.AddAsteroidCommand;
import com.mycompany.a3.CommandRegistry.AddFlyingSaucerCommand;
import com.mycompany.a3.CommandRegistry.AddShipCommand;
import com.mycompany.a3.CommandRegistry.AddSpaceStationCommand;
import com.mycompany.a3.CommandRegistry.AddSpeedCommand;
import com.mycompany.a3.CommandRegistry.AsteroidCrashAsteroid;
import com.mycompany.a3.CommandRegistry.ClockCommand;
import com.mycompany.a3.CommandRegistry.CommandPause;
import com.mycompany.a3.CommandRegistry.DecreaseSpeedCommand;
import com.mycompany.a3.CommandRegistry.FireMissileCommand;
import com.mycompany.a3.CommandRegistry.HyperspaceJumpCommand;
import com.mycompany.a3.CommandRegistry.LoadMissilesCommand;
import com.mycompany.a3.CommandRegistry.MissileKillAsteroidCommand;
import com.mycompany.a3.CommandRegistry.MissileKillFlyingSaucerCommand;
import com.mycompany.a3.CommandRegistry.PrintMapCommand;
import com.mycompany.a3.CommandRegistry.QuitGameCommand;
import com.mycompany.a3.CommandRegistry.ShipCrashAsteroidCommand;
import com.mycompany.a3.CommandRegistry.ShipCrashFlyingSaucer;
import com.mycompany.a3.CommandRegistry.SoundCommand;
import com.mycompany.a3.CommandRegistry.TurnLeftCommand;
import com.mycompany.a3.CommandRegistry.TurnRightCommands;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Observable;
import java.util.Observer;


public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;

	private PointsView pv;
	UITimer timer;
	private boolean isGamePaused = false;
	//private boolean soundStatus = false;
	//private Label checkSoundVal = new Label("OFF");

	private AddAsteroidCommand myAddAsteroid;
	Button addAsteroid = new Button();
	
	 private AddShipCommand myAddShip;
	
	
	private AddSpaceStationCommand myStationCommand;

	
	private AddSpeedCommand mySpeedCommand;
	
	private CommandPause pauseButton;
	
	
	private AddFlyingSaucerCommand myFlyingSaucerCommand;
	
	private DecreaseSpeedCommand decSpeedCmd; 
	
	private FireMissileCommand myFireCommand;
	
	
	 private HyperspaceJumpCommand hyperCommand;
	
	
	 private LoadMissilesCommand loadingCommand;
	
	
	private MissileKillAsteroidCommand MissileKillAsteroidCommand;
	
	private MissileKillFlyingSaucerCommand MissileKillSaucerCommand;

	
	private QuitGameCommand gameQuitCommand;
	
	
	private ShipCrashAsteroidCommand ShipCrashRock;
	
	
	private ShipCrashFlyingSaucer ShipCrashUFO;
	
	
	private TurnLeftCommand LCommand;

	
	private PrintMapCommand mapCommand; 
	
	private TurnRightCommands RCommand;
	private Button addSpaceShip ;

	private Button addSpaceStation;
	
	
	private Button decreaseSpeed ;
	
	
	private Button printMap ;
	
	
	private Button MissileFire;
	
	
	private Button increaseSpeed;
	
	
	private Button asteroidKilled ;
	
	
	private 	Button missileLoaded ;
	
	
	private Button shipCrashedAsteroid;
	
	
	private Button hyperJump;
	
	
	private Button addFlyingSaucer;
	
	private Button pauseBtn ;

	
	private Button ShipCrashSaucerBtn;
	
	
	private Button MissileKillAsteroid;
	
	
	private Button MissileKillFlySaucerBtn ;
	
	private Button LTurnBtn;
	
	
	private Button RTurnBtn;
	
	
	private Button sideMenuExit;

	

	public Game() {
		gw = new GameWorld(); //create "Observable"
		mv = new MapView(gw); //create an "Observer" for the map
		pv = new PointsView(gw); // create an "Observer" for the Points
		gw.addObserver(mv); //Register the map Observer
		gw.addObserver(pv); //register the Points Observer.
		gw.init();
		//play();	// THIS WILL BE CHANGED AND MOVED TO THE EVENT DRIVEN INTERACTIONS 
		setLayout(new BorderLayout());
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		//make the box component box layout for the 12 buttons on gui
		leftContainer.getAllStyles().setPadding(Component.TOP, 10);
		String myTitle = ("Asteroid Game REMASTERED");
		add(BorderLayout.NORTH, pv);
		add(BorderLayout.CENTER, mv);
		
		
		
		//from here on will be the code to create menus, create command objects for each command, 
		//add commands to command menu, create a control panel for the buttons, add buttons to the control Panel,
		// add commands to the buttons, and add control panel, MapView Panel, and PointsView panel to the form.
		//left Container with the BoxLayout positioned on the west 
		

		
		addAsteroid = new Button("Add Asteroid Command");
		addAsteroid.getAllStyles().setBgTransparency(255);
		addAsteroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		addAsteroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addAsteroid.getAllStyles().setPadding(TOP, 5);
		addAsteroid.getAllStyles().setPadding(BOTTOM, 5);
		
		addSpaceShip = new Button("Add Space Ship Command");
		addSpaceShip.getAllStyles().setBgTransparency(255);
		addSpaceShip.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		addSpaceShip.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addSpaceShip.getAllStyles().setPadding(TOP, 5);
		addSpaceShip.getAllStyles().setPadding(BOTTOM, 5);
		
		addSpaceStation = new Button("Add Space Station Command");
		addSpaceStation.getAllStyles().setBgTransparency(255);
		addSpaceStation.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		addSpaceStation.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addSpaceStation.getAllStyles().setPadding(TOP, 5);
		addSpaceStation.getAllStyles().setPadding(BOTTOM, 5);
		
		decreaseSpeed = new Button("decrease Ship speed Command");
		decreaseSpeed.getAllStyles().setBgTransparency(255);
		decreaseSpeed.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		decreaseSpeed.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		decreaseSpeed.getAllStyles().setPadding(TOP, 5);
		decreaseSpeed.getAllStyles().setPadding(BOTTOM, 5);
		
		printMap = new Button("MAP");
		printMap.getAllStyles().setBgTransparency(255);
		printMap.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		printMap.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		printMap.getAllStyles().setPadding(TOP, 5);
		printMap.getAllStyles().setPadding(BOTTOM, 5);
		
		MissileFire = new Button("Fire ship Missile Command");
		MissileFire.getAllStyles().setBgTransparency(255);
		MissileFire.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		MissileFire.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		MissileFire.getAllStyles().setPadding(TOP, 5);
		MissileFire.getAllStyles().setPadding(BOTTOM, 5);
		
		increaseSpeed = new Button("increase Ship speed Command");
		increaseSpeed.getAllStyles().setBgTransparency(255);
		increaseSpeed.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		increaseSpeed.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		increaseSpeed.getAllStyles().setPadding(TOP, 5);
		increaseSpeed.getAllStyles().setPadding(BOTTOM, 5);
		
		asteroidKilled = new Button("Killed Asteroid Command");
		asteroidKilled.getAllStyles().setBgTransparency(255);
		asteroidKilled.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		asteroidKilled.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		asteroidKilled.getAllStyles().setPadding(TOP, 5);
		asteroidKilled.getAllStyles().setPadding(BOTTOM, 5);
		
		missileLoaded = new Button("New Missile Supply Command");
		missileLoaded.getAllStyles().setBgTransparency(255);
		missileLoaded.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		missileLoaded.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		missileLoaded.getAllStyles().setPadding(TOP, 5);
		missileLoaded.getAllStyles().setPadding(BOTTOM, 5);
		
		shipCrashedAsteroid = new Button("Ship Crashed Command");
		shipCrashedAsteroid.getAllStyles().setBgTransparency(255);
		shipCrashedAsteroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		shipCrashedAsteroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		shipCrashedAsteroid.getAllStyles().setPadding(TOP, 5);
		shipCrashedAsteroid.getAllStyles().setPadding(BOTTOM, 5);
		
		hyperJump = new Button("Ship Jump HyperSpace Commands");
		hyperJump.getAllStyles().setBgTransparency(255);
		hyperJump.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		hyperJump.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		hyperJump.getAllStyles().setPadding(TOP, 5);
		hyperJump.getAllStyles().setPadding(BOTTOM, 5);
		
		addFlyingSaucer =new Button("add flyingSaucer");
		addFlyingSaucer.getAllStyles().setBgTransparency(255);
		addFlyingSaucer.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		addFlyingSaucer.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addFlyingSaucer.getAllStyles().setPadding(TOP, 5);
		addFlyingSaucer.getAllStyles().setPadding(BOTTOM, 5);
		
		pauseBtn =new Button("game pause");
		pauseBtn.getAllStyles().setBgTransparency(255);
		pauseBtn.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		pauseBtn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		pauseBtn.getAllStyles().setPadding(TOP, 5);
		pauseBtn.getAllStyles().setPadding(BOTTOM, 5);
		
		ShipCrashSaucerBtn =new Button("ship Collided with UFO Command");
		ShipCrashSaucerBtn.getAllStyles().setBgTransparency(255);
		ShipCrashSaucerBtn.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		ShipCrashSaucerBtn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		ShipCrashSaucerBtn.getAllStyles().setPadding(TOP, 5);
		ShipCrashSaucerBtn.getAllStyles().setPadding(BOTTOM, 5);
		
		MissileKillAsteroid =new Button("Missile Killed Asteroid Command");
		MissileKillAsteroid.getAllStyles().setBgTransparency(255);
		MissileKillAsteroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		MissileKillAsteroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		MissileKillAsteroid.getAllStyles().setPadding(TOP, 5);
		MissileKillAsteroid.getAllStyles().setPadding(BOTTOM, 5);
		
		MissileKillFlySaucerBtn =new Button("Missile Killed Flying Saucer Command");
		MissileKillFlySaucerBtn.getAllStyles().setBgTransparency(255);
		MissileKillFlySaucerBtn.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		MissileKillFlySaucerBtn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		MissileKillFlySaucerBtn.getAllStyles().setPadding(TOP, 5);
		MissileKillFlySaucerBtn.getAllStyles().setPadding(BOTTOM, 5);
		
		LTurnBtn =new Button("Turn left Command");
		LTurnBtn.getAllStyles().setBgTransparency(255);
		LTurnBtn.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		LTurnBtn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		LTurnBtn.getAllStyles().setPadding(TOP, 5);
		LTurnBtn.getAllStyles().setPadding(BOTTOM, 5);
		
		RTurnBtn =new Button("Turn Right Command");
		RTurnBtn.getAllStyles().setBgTransparency(255);
		RTurnBtn.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		RTurnBtn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		RTurnBtn.getAllStyles().setPadding(TOP, 5);
		RTurnBtn.getAllStyles().setPadding(BOTTOM, 5);
		
		sideMenuExit = new Button("Exit");
		sideMenuExit.getAllStyles().setBgTransparency(255);
		sideMenuExit.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,150,150));
		sideMenuExit.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		sideMenuExit.getAllStyles().setPadding(TOP, 5);
		sideMenuExit.getAllStyles().setPadding(BOTTOM, 5);
		
		//now we must connect them with all of the commands
				myAddAsteroid = new AddAsteroidCommand(gw);
				addAsteroid.setCommand(myAddAsteroid);
				addKeyListener('a', myAddAsteroid);
				
				myAddShip = new AddShipCommand(gw);
				addSpaceShip.setCommand(myAddShip);
				addKeyListener('s', myAddShip);
				
				myStationCommand = new AddSpaceStationCommand(gw);
				addSpaceStation.setCommand(myStationCommand);
				addKeyListener('b', myStationCommand);
				
				mySpeedCommand = new AddSpeedCommand(gw);
				increaseSpeed.setCommand(mySpeedCommand);
				addKeyListener(-91, mySpeedCommand);
					
				pauseButton = new CommandPause(this);
				pauseBtn.setCommand(pauseButton);
				addKeyListener('x', pauseButton);
				
				myFlyingSaucerCommand = new AddFlyingSaucerCommand(gw);
				addFlyingSaucer.setCommand(myFlyingSaucerCommand);
				addKeyListener('f', myFlyingSaucerCommand);
				
				decSpeedCmd = new DecreaseSpeedCommand(gw);
				decreaseSpeed.setCommand(decSpeedCmd);
				addKeyListener(-92, decSpeedCmd);
				
				myFireCommand = new FireMissileCommand(gw);
				MissileFire.setCommand(myFireCommand);
				addKeyListener(-90, myFireCommand);
				
				hyperCommand = new HyperspaceJumpCommand(gw);
				hyperJump.setCommand(hyperCommand);
				addKeyListener('j', hyperCommand);
				
				loadingCommand = new LoadMissilesCommand(gw);
				missileLoaded.setCommand(loadingCommand);
				addKeyListener('n', loadingCommand);
				
				
				MissileKillAsteroidCommand = new MissileKillAsteroidCommand(gw);
				MissileKillAsteroid.setCommand(MissileKillAsteroidCommand);
				addKeyListener('k', MissileKillAsteroidCommand);
				
				MissileKillSaucerCommand = new MissileKillFlyingSaucerCommand(gw);
				MissileKillFlySaucerBtn.setCommand(MissileKillSaucerCommand);
				addKeyListener('e', MissileKillSaucerCommand);
				
				gameQuitCommand = new QuitGameCommand(gw);
				sideMenuExit.setCommand(gameQuitCommand);
				addKeyListener('q', gameQuitCommand);
				
				ShipCrashRock = new ShipCrashAsteroidCommand(gw);
				shipCrashedAsteroid.setCommand(ShipCrashRock);
				addKeyListener('c', ShipCrashRock);
				
				ShipCrashUFO = new ShipCrashFlyingSaucer(gw);
				ShipCrashSaucerBtn.setCommand(ShipCrashUFO);
				addKeyListener('h', ShipCrashUFO);
				
				LCommand  = new TurnLeftCommand(gw);
				LTurnBtn.setCommand(LCommand);
				addKeyListener(-93, LCommand);

				RCommand = new TurnRightCommands(gw);
				RTurnBtn.setCommand(RCommand);
				addKeyListener(-94, RCommand);
				
				mapCommand  = new PrintMapCommand(gw);
				printMap.setCommand(mapCommand);
				//addKeyListener('p', mapCommand);
				
//now that we made the button styles add into the container		
		leftContainer.add(addSpaceShip);
		addSpaceShip.setFocusable(false);
		leftContainer.add(addAsteroid);
		addAsteroid.setFocusable(false);
		leftContainer.add(addSpaceStation);
		addSpaceStation.setFocusable(false);
		leftContainer.add(decreaseSpeed);
		decreaseSpeed.setFocusable(false);
		leftContainer.add(printMap);
		printMap.setFocusable(false);
		//printMap.setFocusable(false);
		leftContainer.add(MissileFire);
		MissileFire.setFocusable(false);
		leftContainer.add(increaseSpeed);
		increaseSpeed.setFocusable(false);
		//leftContainer.add(asteroidKilled);
		leftContainer.add(missileLoaded);
		missileLoaded.setFocusable(false);
		//leftContainer.add(shipCrashedAsteroid);
		leftContainer.add(hyperJump);
		hyperJump.setFocusable(false);
		leftContainer.add(addFlyingSaucer);
		addFlyingSaucer.setFocusable(false);
		
		leftContainer.add(pauseBtn);
		pauseBtn.setFocusable(false);
		//leftContainer.add(MissileKillAsteroid);
		//leftContainer.add(MissileKillFlySaucerBtn);
		leftContainer.add(LTurnBtn);
		LTurnBtn.setFocusable(false);
		leftContainer.add(RTurnBtn);
		RTurnBtn.setFocusable(false);
		//leftContainer.add(ShipCrashSaucerBtn);
		leftContainer.setScrollableY(true);
		


		

		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4,
														ColorUtil.BLUE));
		
		add(BorderLayout.WEST,leftContainer);
		
		//now create side menu toolbar alongside its menu  options
		//new SideMenuItemCheckFormUsingCommand();
		Toolbar sideMenuToolbar = new Toolbar();
		sideMenuToolbar.getAllStyles().setPadding(20, 0, 0, 0);
		sideMenuToolbar.getAllStyles().setBgColor(ColorUtil.BLUE);
		sideMenuToolbar.getAllStyles().setFgColor(ColorUtil.WHITE);
		setToolbar(sideMenuToolbar);
		  //add the checkbox comp to the side menu, this places its side component (check box) 
		  //in the side menu
		CheckBox checkSideMenuComp = new CheckBox("Check Box for Sound");
		  checkSideMenuComp.getAllStyles().setBgTransparency(255);
		  checkSideMenuComp.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		  Command mySoundMenuItemCheck = new SoundCommand(gw);
		  checkSideMenuComp.setCommand(mySoundMenuItemCheck);
		 sideMenuToolbar.addComponentToSideMenu(checkSideMenuComp);
		 sideMenuToolbar.setTitle(myTitle);
		 //add a new file button
		  Command sideMenuNew = new Command("New");
		  sideMenuToolbar.addCommandToSideMenu(sideMenuNew);
		  
		  //add save button
		  Command sideMenuSave = new Command("save");
		  sideMenuToolbar.addCommandToSideMenu(sideMenuSave);
		  
		  //now add  quit game option
		 // Command sideMenuQuit = new Command("quit Game?");
		 // sideMenuToolbar.addCommandToSideMenu(sideMenuQuit);
		  
		  

		 


		  
		
	
		//now we add the about option to menu
		Command sideMenuAbout = new Command("About") {

			public void actionPerformed(ActionEvent e) {

				try {

					Dialog.show("ABOUT THIS PROJECT", " Author: Jordan Greer \n Class: CSC 133 \n Spring 2018 \n Version: 1.2", "OK", null);

				} catch (NullPointerException e1) {

					
				}
			}
		};
		sideMenuToolbar.addCommandToSideMenu(sideMenuAbout);
		//now we add exit option
		QuitGameCommand myQuitCommand = new QuitGameCommand(gw);
		sideMenuExit.setCommand(myQuitCommand);

		sideMenuToolbar.addComponentToSideMenu(sideMenuExit);
		//now we add second menu to help with game commands with the user
		Command sideMenuItemHelp = new Command("Help?") {

			public void actionPerformed(ActionEvent e) {

				try {

					Dialog.show("KEYBOARD COMMANDS", 

								" a - Add a new asteroid to the world \n"

								+ "b - Add a new Blinking Space Station to the world \n"

								+ "s - Add a new Ship to the world \n"

								+ "n - load a New Supply of missiles into the ship \n"

								+ "k - a missile has struck and killed an asteroid \n"

								+ "c - The ship has crashed into an asteroid \n"

								+ "x - two asteroid have struck and killed each other\n"

								+ "t - tell the gameworld that the 'game clock' has ticked \n"

								+ " q - quit program \n", 

								"PRESS RETURN TO QUIT", null);

				} catch (NullPointerException e1) {

				
				}
			}
		};

		sideMenuToolbar.addCommandToRightBar(sideMenuItemHelp);
		timer = new UITimer(this); // create timer and provide a runnable
		timer.schedule(100, true, this);
		
		this.show();
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
	} //end of Game Constructor
	
	public boolean isPaused() {
		return isGamePaused;
	}
	
	public void PauseGame() {
		System.out.println("game has paused!");
		timer.cancel();
		isGamePaused=true;
		//still need to make enabled set of buttons only available in a paused game
		LTurnBtn.setEnabled(false);
		removeKeyListener(-93, LCommand);
	
		RTurnBtn.setEnabled(false);
		removeKeyListener(-94,RCommand);
		
		
		missileLoaded.setEnabled(true);
		addKeyListener('n',loadingCommand);
		
		increaseSpeed.setEnabled(false);
		removeKeyListener(-91, mySpeedCommand);
		
		decreaseSpeed.setEnabled(false);
		removeKeyListener(-92, decSpeedCmd);
		
		printMap.setEnabled(true);
		addKeyListener('p',mapCommand);
	}
	public void resumeGame() {
		System.out.println("game is resuming!");
		timer.schedule(100, true, this);
		isGamePaused = false;
		
		missileLoaded.setEnabled(false);
		removeKeyListener('n', loadingCommand);

		LTurnBtn.setEnabled(true);
		addKeyListener(-93, LCommand);

		//LTurnBtn.getDisabledStyle();
		RTurnBtn.setEnabled(true);
		addKeyListener(-94,RCommand);
		
		increaseSpeed.setEnabled(true);
		addKeyListener(-91,mySpeedCommand);
		
		decreaseSpeed.setEnabled(true);
		addKeyListener(-92, decSpeedCmd);

		printMap.setEnabled(false);
		removeKeyListener('p',mapCommand);

	}
	
	@Override
	public void run() {
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		gw.clockTick();	
		mv.repaint();
		
		// TODO Auto-generated method stub
		
	}
	
	

}
