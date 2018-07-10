package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;

import java.util.Random;

public final class Missile extends MoveableObject implements IMoveable, IDrawable, ICollider,ISelectable {

	

	private int mFuel = 10;
	private Random r = new Random();
	private int x = r.nextInt(5);
	private int newSpeed,newDirection, currentX=0,currentY =0,incX,incY;
	private Point2D newLocation;
	private int missileSize;
	private int gameSizeX = (int) GameWorld.getWidth(),gameSizeY = (int) GameWorld.getHeight();
	private boolean selected;

	public Missile(int mSpeed, int mDirection, Point2D mLocation ) { 
		// 1.) set color
		super.setColor(ColorUtil.YELLOW);
		//2.) set speed to ships speed + rand(value), including the ships followed direction.
		
		newSpeed = (mSpeed + x);
		// if the speed value was already pretty high, set it to its max speed w/o going over the cap
		if(newSpeed >= 15) {
			super.setSpeed(15);
			newSpeed = super.getSpeed();
			super.setDirection(mDirection);
			newDirection = super.getDirection();
			
			
		}
		else { // just do the regular thing..
			super.setSpeed(newSpeed);
			newSpeed = super.getSpeed();
			super.setDirection(mDirection);
			newDirection = super.getDirection();

		}
		missileSize = 6;
		
		//3.) also needs matching  location with ship
		super.setLocation(mLocation);
		newLocation = super.getLocation();
	}
	
	public int getmFuel() {
		return mFuel;
	}

	public void setmFuel(int mFuel) {
		this.mFuel = mFuel;
	}

	@Override
	public String toString() {
		String myColorDescription = "color: " + "[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";

		return "Missile: loc=" + super.getLocation() + "," + myColorDescription + " speed=" + super.getSpeed() + "dir=" + super.getDirection() + " fuel=" + mFuel ;
	}
	

	public void move(Point2D dCmpSize) {
		/* double theta = (90 - super.getDirection());
		 double deltaX = Math.cos(theta)*super.getSpeed();
		 double deltaY = Math.sin(theta) * super.getSpeed();
		 Point2D oldLocation = super.getLocation();
		 double oldX = oldLocation.getX();
		 double oldY = oldLocation.getY();
		 Point2D newLocation = new Point2D((oldX+deltaX),(oldY+deltaY))
		 super.setLocation(newLocation);*/
		/*currentX += incX;
		 currentY += incY;
		 if(( currentX+newSize>= dCmpSize.getX())|| (currentX <0))
			incX = -incX;
		 if(( currentY+newSize>= dCmpSize.getY())|| (currentY <0))
			incY =-incY;*/
	}
	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		//newSize = getSize();
		currentX = (int)getLocation().getX();
		currentY = (int)getLocation().getY();
		incX = getSpeed();
		incY = getSpeed();
		//super.setColor(ColorUtil.YELLOW);
		if(isSelected()) {
			g.setColor(ColorUtil.BLACK);
			g.drawRect((int)pCmpRelPrnt.getX()+(int)currentX, (int)pCmpRelPrnt.getY()+(int)currentY, missileSize, missileSize);
			
		} else { 
			g.setColor(ColorUtil.YELLOW);
			g.fillRect((int)pCmpRelPrnt.getX()+(int)currentX, (int)pCmpRelPrnt.getY()+(int)currentY, missileSize, missileSize);
		}
		
		
	}

	@Override
	public boolean collidesWith(ICollider anotherObject) {
		boolean result = false; 
        float thisCenterX = (float) (this.getLocation().getX() + (getSize()/2)); // find centers 
        float thisCenterY = (float) (this.getLocation().getY() + (getSize()/2)); 

         float otherCenterX = (float) (((GameObject) anotherObject).getLocation().getX() + (((GameObject) anotherObject).getSize()/2)); 
         float otherCenterY = (float) (((GameObject) anotherObject).getLocation().getY() + (((GameObject) anotherObject).getSize()/2)); 

 
         float dx = thisCenterX - otherCenterX; 
         float dy = thisCenterY - otherCenterY; 
         float distBetweenCentersSqr = (dx*dx + dy*dy); 

 
         int thisRadius = getSize()/2; 
         int otherRadius = ((GameObject) anotherObject).getSize()/2; 
         
         int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius); 
         if (distBetweenCentersSqr <= radiiSqr) { result = true ; } 
         System.out.println("missile Collision");
         return result; 
	}

	@Override
	public void handleCollision(ICollider anotherObject) {
		//Case 1: missile hits ship
		//	do not explode the ship
		//	do not explode the missile
		if(anotherObject instanceof Ship) {
			this.setFlag(false);
		}
		//Case 2: missile hits asteroid
		// 	explode the asteroid
		//	explode the missile
		if(anotherObject instanceof Asteroid) {
			this.setFlag(true);
		}
		//Case 3: missile hits Missile
		//	do not explode the missiles
		if(anotherObject instanceof Missile) {
			this.setFlag(false);
		}
		//Case 4: Missile hits SpaceStation
		//	do not explode Missile
		//	do not explode space station.
		if(anotherObject instanceof SpaceStation) {
			this.setFlag(false);
		}
	}

	@Override
	public void setSelected(boolean select) {
		// TODO Auto-generated method stub
		selected = select;
		
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public boolean contains(Point2D pPtrRelPrnt, Point2D pCmpRelPrnt) {
		int px = (int) pPtrRelPrnt.getX(); // pointer location relative to
		int py = (int) pPtrRelPrnt.getY(); // parents origin
		int xLoc = (int) (pCmpRelPrnt.getX()+ getLocation().getX());// shape location relative
		int yLoc = (int) (pCmpRelPrnt.getY()+ getLocation().getY());// to parents origin
		if ( (px >= xLoc) && (px <= xLoc+gameSizeX) 
			&& (py >= yLoc) && (py <= yLoc+gameSizeY) )
			return true; 
		else 
			return false;
	}

}
