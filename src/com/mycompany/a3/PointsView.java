package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.A2.Game.IGameWorld;

public class PointsView extends Container implements Observer {
	Label pointsTextLabel = new Label("Points:");
	Label livesTextLabel = new Label("lives:");
	Label missileTextLabel = new Label("missile:");
	Label soundTextLabel = new Label("sound:");
	Label timeTextLabel = new Label("time:"); 
	
	private Label pointValuesLabel;
	private Label liveValuesLabel;
	private Label missileValuesLabel;
	private Label soundValueLabel = new Label("OFF");
	private Label timeValueLabel;


	public PointsView(GameWorld gw) {
		//Instantiate text Labels

		//Instantiating value labels
		pointValuesLabel = new Label(""+gw.getPoints());
		liveValuesLabel = new Label(""+gw.getLives());
		missileValuesLabel = new Label(""+gw.getMissiles());
		//soundValueLabel = new Label(""+gw.getSound());
		timeValueLabel = new Label(""+gw.getElapsedTime());
		
		//set color
		pointsTextLabel.getAllStyles().setFgColor(ColorUtil.rgb(0,0,255));
		
		//Adding a container with a boxLayout
		Container myContainer = new Container();
		myContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		//adding all labels in order
		myContainer.add(pointsTextLabel);
		myContainer.add(pointValuesLabel);
		myContainer.add(livesTextLabel);
		myContainer.add(liveValuesLabel);
		myContainer.add(missileTextLabel);
		myContainer.add(missileValuesLabel);
		myContainer.add(soundTextLabel);
		myContainer.add(soundValueLabel);
		myContainer.add(timeTextLabel);
		myContainer.add(timeValueLabel);		
		this.add(myContainer);
		// TODO Auto-generated constructor stub
	}
	// code here to update labels from data in the Observable( a GameWorldPROXY)
	//updates the contents of the labels displaying the game states(use Label method setText(String)
	//to update the label)
	public void update (Observable o, Object arg) {
		
		//casting o as a GameWorld
		IGameWorld gw = (IGameWorld) arg;
		
		//Getting Player Score
		int score = gw.getPoints();
		pointValuesLabel.setText("" + (score > 99 ? "" : "0")+(score > 9 ? "" : "0")+ score);
		this.repaint();
		
		//getting player lives
		int ShipLives = gw.getLives();
		liveValuesLabel.setText(""+ShipLives);
		this.repaint();
		
		//getting missiles
		int missileLeft = gw.getMissiles();
		missileValuesLabel.setText(""+missileLeft);
		this.repaint();
		//getting sound
		boolean soundStatus = gw.getSound();
		  if (soundStatus) {
			   soundValueLabel.setText("ON");
			   this.repaint();
		   }
			   
		   		
		   else {
			   soundValueLabel.setText("OFF");
			   this.repaint();
		   }
		 
		
		int currTime = gw.getElapsedTime();
		timeValueLabel.setText("" + (currTime > 99 ? "" : "0")+(currTime > 9 ? "" : "0")+ currTime);
		this.repaint();
			
	}
}