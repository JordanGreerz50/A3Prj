package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

//import com.codename1.ui.geom.Point2D;

public abstract class MoveableObject extends GameObject implements IMoveable,ISelectable {

	private  int speed;
	private  int direction;
	private int size,color;
	private double gameSizeWidth = GameWorld.getWidth();
	private double gameSizeHeight = GameWorld.getHeight();
	private int revertX = 1,revertY = 1;
	private Random rand;
	private boolean selected;
	
	public MoveableObject() {
		// TODO Auto-generated constructor stub
		//color = super.setColor();
		gameSizeWidth= GameWorld.getWidth();
		gameSizeHeight=GameWorld.getHeight();
	}
	public void move(int elapsedTime) {
		 gameSizeWidth= GameWorld.getWidth();
		 gameSizeHeight=GameWorld.getHeight();
		 //double distance = (getSpeed())*(elapsedTime/1000); 
		 double theta = (90 - getDirection());
		 double deltaX = ((elapsedTime/50)*Math.cos(Math.toRadians(theta))*getSpeed());
		 double deltaY = ((elapsedTime/50)*Math.sin(Math.toRadians(theta)) *getSpeed());
		 deltaX = Math.round(deltaX);
		 deltaY = Math.round(deltaY);
		 double newLocationX = (getLocation().getX()+deltaX);
		 double newLocationY = (getLocation().getY()+deltaY);
		 double finalValueX = newLocationX *revertX;
		 double finalValueY = newLocationY *revertY;
		 Point2D finalCoordinate = new Point2D(finalValueX, finalValueY);
		 setLocation(finalCoordinate);

		
		//checks boundaries so it will bounce off the borders of the map
			
		 if(( getLocation().getX()+getSize()>= gameSizeHeight-10)|| (getLocation().getX() <0))
		 	revertX = -1 * revertX;
		 if((getLocation().getY()>= gameSizeWidth-70)|| (getLocation().getY() <0))
			 revertY = -1 * revertY;
		 
		 //Set location to the middle of object.
			if( newLocationX < (gameSizeHeight-getSize()/2) && newLocationX > 0 && newLocationY < gameSizeWidth-(getSize()) && newLocationY > 0){
				Point2D newPoint = new Point2D(newLocationX,newLocationY);
				setLocation(newPoint);
			}
			else {
				setDirection(new Random().nextInt(360));
			}
		
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	// this function will allow the integer value for direction to stay with an angle of -179 < direction < 180
	public int normalizeAngle(int angle)
	{
	   
	    while (angle < 0) 
	    	angle += 360;
	    while (angle > 360) 
	    	angle -= 360;
	    if(angle == 360)
	    	angle = 0;
	    return angle;
	}
	@Override
	public void setSelected(boolean select) {
		//selected = select;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point2D pPtrRelPrnt, Point2D pCmpRelPrnt) {
		/*int px = (int) pPtrRelPrnt.getX(); // pointer location relative to
		int py = (int) pPtrRelPrnt.getY(); // parents origin
		int xLoc = (int) (pCmpRelPrnt.getX()+ getLocation().getX());// shape location relative
		int yLoc = (int) (pCmpRelPrnt.getY()+ getLocation().getY());// to parents origin
		if ( (px >= xLoc) && (px <= xLoc+gameSizeWidth) 
			&& (py >= yLoc) && (py <= yLoc+gameSizeHeight) )
			return true; 
		else */
			return false;
	}

	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}
}
