package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;

public final class FlyingSaucer extends MoveableObject implements IMoveable, IDrawable, ICollider {

	private int fSize, fSpeed, fDirection;
	double currentX,currentY;
	private int gameSizeX = (int) GameWorld.getWidth(),gameSizeY = (int) GameWorld.getHeight();
	private Point2D fLocation;
	private int incX;
	private int incY;
	private int color;
	private boolean collision;
	
	public FlyingSaucer() {
		
		// TODO Auto-generated constructor stub
		//1.) random location, speed, direction, & size
		super.setLocation(RandomFLocation(fLocation));
		fLocation = super.getLocation();
		currentX = fLocation.getX();
		currentY = fLocation.getY();
		super.setSpeed(RandomFSpeed(fSpeed));
		fSpeed = super.getSpeed();
		super.setDirection(RandomFDirection(fDirection));
		fDirection = super.getDirection();
		super.setSize(RandomFSize(fSize));
		fSize= super.getSize();
		//2.)choose color
		 super.setColor(ColorUtil.GREEN);
		 color = super.getColor();
	}

	
	public Point2D RandomFLocation(Point2D l) {
		/*Random  randomX = new Random();
		Random randomY = new Random();
		int minX=(1024-gameSizeX),minY=768-gameSizeY,maxX=1024, maxY=768;
		double x, y;
		x = randomX.nextInt(maxX-minX+1)+minX;
		y = randomY.nextInt(maxY=minY)+minY;
		Point2D randomLocation = new Point2D(x,y);
		return randomLocation;*/
		Random  randomX = new Random();
		Random randomY = new Random();
		int x, y;
		x = randomX.nextInt(gameSizeX);
		y = randomY.nextInt(gameSizeY);
		Point2D randomLocation = new Point2D(x,y);
		return randomLocation;
	}
	
	public int RandomFSpeed(int s) {
		int min = 3;
		int max = 9;
		int rSpeed;
		Random r = new Random();
		rSpeed = r.nextInt((max-min+1) + min);
		return rSpeed;
	}
	
	public int RandomFDirection(int d) {
		Random rand = new Random();
		int rDirection= rand.nextInt(359);
		return rDirection;
	}
	
	public int RandomFSize(int rZ) {
		int min = 10;
		int max = 20;
		int rSize;
		Random r = new Random();
		rSize = r.nextInt((max-min+1) + min);
		return rSize;
		
	}

	@Override
	public String toString() {
		String myColorDescription = "color: " + "[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		return "Flying Saucer: loc=" + super.getLocation() + ", " + myColorDescription + " speed=" +  super.getSpeed() +" dir=" + super.getDirection() + " size=" + super.getSize() + "]";

	}
	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		// TODO Auto-generated method stub
				fSize = getSize();
				currentX = (int) getLocation().getX();

				currentY = (int) getLocation().getY();
				//incX = getSpeed();
				//incY = getSpeed();
			
				g.setColor(ColorUtil.BLUE);
				g.drawRect((int)pCmpRelPrnt.getX()+(int)currentX, (int)pCmpRelPrnt.getY()+(int)currentY, fSize, fSize);
	}

	public void move(Point2D dCmpSize) {
		/* double theta = (90 - super.getDirection())
		 double deltaX = Math.cos(theta)*super.getSpeed();
		 double deltaY = Math.sin(theta) * super.getSpeed();
		 Point2D oldLocation = super.getLocation();
		 double oldX = oldLocation.getX();
		 double oldY = oldLocation.getY();
		 Point2D newLocation = new Point2D((oldX+deltaX),(oldY+deltaY));
		 super.setLocation(newLocation);*/
		/*super.move();
		currentX += incX;
		 currentY += incY;
		 //Point2D newLocation = new Point2D((currentX+deltaX),(oldY+deltaY));
		 if(( currentX+fSize>= dCmpSize.getX())|| (currentX <0))
			incX = -incX;
		 if(( currentY+fSize>= dCmpSize.getY())|| (currentY <0))
			incY =-incY;*/
			   

	}


	@Override
	public boolean collidesWith(ICollider anotherObject) {

		 /*float p1x = (float) getLocation().getX(); 
	        float p2x = (float) (getLocation().getX() + getSize()); 
	        float p3x = (float) getLocation().getX(); 
	        float p4x = (float) (getLocation().getX() + getSize()); 
	        float p1y = (float) getLocation().getY(); 
	        float p2y = (float) getLocation().getY(); 
	        float p3y = (float) (getLocation().getY() + getSize()); 
	        float p4y = (float) (getLocation().getY() + getSize()); 

	        GameObject mover = anotherObject; 

	        float a1x = (float) mover.getLocation().getX(); 
	        float a2x = (float) (mover.getLocation().getX() + mover.getSize()); 
	        float a3x = (float) mover.getLocation().getX(); 
	        float a4x = (float) (mover.getLocation().getX() + getSize()); 
	        float a1y = (float) mover.getLocation().getY(); 
	        float a2y = (float) mover.getLocation().getY(); 
	        float a3y = (float) (mover.getLocation().getY() + mover.getSize()); 
	        float a4y = (float) (mover.getLocation().getY() + mover.getSize()); 



	        if ((p1x > a1x && p1x < a2x) && (p1y > a1y && p1y < a3y) ||//good 

	        	(p2x > a1x && p2x < a2x) && (p2y > a1y && p2y < a3y) ||//good 

	        	(p3x > a1x && p3x < a2x) && (p3y > a1y && p3y < a3y) ||//good 

	        	(p4x > a1x && p4x < a2x) && (p4y > a1y && p4x < a3x)) { //good 

	        	 System.out.println("collision"); 
	        	collision = true; 
	        	return true; 
	        } else { 
	        	collision = false; 
	        	return false; 
	        }*/
	
		boolean result = false; 
        float thisCenterX = (float) (this.getLocation().getX() + (fSize/2)); // find centers 
        float thisCenterY = (float) (this.getLocation().getY() + (fSize/2)); 

         float otherCenterX = (float) (((GameObject) anotherObject).getLocation().getX() + (((GameObject) anotherObject).getSize()/2)); 
         float otherCenterY = (float) (((GameObject) anotherObject).getLocation().getY() + (((GameObject) anotherObject).getSize()/2)); 

 
         float dx = thisCenterX - otherCenterX; 
         float dy = thisCenterY - otherCenterY; 
         float distBetweenCentersSqr = (dx*dx + dy*dy); 

 
         int thisRadius = fSize/2; 
         int otherRadius = ((GameObject) anotherObject).getSize()/2; 
         
         int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius); 
         if (distBetweenCentersSqr <= radiiSqr) {
        	 System.out.println("Saucer Collision");
        	 result = true;
        	 } 

         return result; 
	}


	@Override
	public void handleCollision(ICollider anotherObject) {


		if(anotherObject instanceof Ship) {
			this.setFlag(true);
		}
		if(anotherObject instanceof Asteroid) {
			this.setFlag(true);
		}

		if(anotherObject instanceof Missile) {
			this.setFlag(true);
		}
		
		if(anotherObject instanceof SpaceStation) {
			this.setFlag(false);
		}

}


	@Override
	public void setSelected(boolean select) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean contains(Point2D pPtrRelPrnt, Point2D pCmpRelPrnt) {
		// TODO Auto-generated method stub
		return false;
	}

}
