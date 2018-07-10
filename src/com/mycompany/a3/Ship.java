package com.mycompany.a3;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;
import java.lang.Math;

public  class Ship extends MoveableObject implements IMoveable, ISteerable, IDrawable, ICollider {

	
	private int missileCount = 12;	
	//private Point2D center = new Point2D(512,384);
	private int shipSpeed = 0;
	private int shipDirection = 0;
	private int shipSize;
	private boolean collision;
	private int currentX = 0, currentY = 0;
	private int incX, incY;
	private Point2D shipLocation = new Point2D(512,384);
	public Ship() {
		
		// within the ship constructor u will need
		//1.) the ships location at the center of the world(512,385)
		super.setLocation(shipLocation);
		shipLocation = getLocation();
		//2.) a speed of zero
		super.setSpeed(shipSpeed);
		shipSpeed =getSpeed();
	
		//3.)a heading of zero(pointing North)
		super.setDirection(shipDirection);
		shipDirection =getDirection();
		//and a color.
		super.setColor(ColorUtil.GREEN);
		shipSize = 16;
		
	}

	public void turnLeft() {
		// TODO Auto-generated method stub
		int newAngle = super.getDirection();
		newAngle = newAngle + 20;
		super.normalizeAngle(newAngle);
		super.setDirection(newAngle);
		
		

	}
	public void turnRight() {
		int newAngle = super.getDirection();
		newAngle = newAngle - 20;
		super.normalizeAngle(newAngle);
		super.setDirection(newAngle);
		
	} 

	
	
	public int getMissileCount() {
		return missileCount;
	}

	public void setMissileCount(int missileCount) {
		this.missileCount = missileCount;
	}

	@Override
	public String toString() {
		String myColorDescription = "color: " + "[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";

		return "Ship: loc= " + super.getLocation() + "," + myColorDescription + " speed=" + super.getSpeed() + " dir=" + super.getDirection() + " missiles=" + missileCount ;
	}

	public int getShipDirection() {
		return super.getDirection();
	}

	public void setShipDirection(int shipDirection) {
		this.shipDirection = shipDirection;
	}

	public int getShipSpeed() {
		return super.getSpeed();
	}

	public void setShipSpeed(int sSpeed) {
		this.shipSpeed = sSpeed;
	}
	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		
		currentX = (int) getLocation().getX();
		currentY = (int) getLocation().getY();
		incX = getSpeed();
		incY=getSpeed();
		super.setColor(ColorUtil.GREEN);
		g.setColor(ColorUtil.GREEN);
		Point2D top      = new Point2D(pCmpRelPrnt.getX()+currentX, pCmpRelPrnt.getY()+currentY + (shipSize/2)); 

	    Point2D bottomLeft  = new Point2D(pCmpRelPrnt.getX()+currentX - (shipSize/2), pCmpRelPrnt.getY()+currentY  - (shipSize/2)); 

	    Point2D bottomRight = new Point2D(pCmpRelPrnt.getX()+currentX + (shipSize/2), pCmpRelPrnt.getY()+currentY  - (shipSize/2)); 

	     

	      

	    int [] xPts = new int [] {(int) top.getX(), (int) bottomLeft.getX(), (int) bottomRight.getX()} ; 

	    int [] yPts = new int [] {(int) top.getY(), (int) bottomLeft.getY(), (int) bottomRight.getY()} ; 

	  
	    g.fillPolygon(xPts, yPts, 3); 

		/*Point2D top      = new Point2D(pCmpRelPrnt.getX()+currentX-(getSize()/2), pCmpRelPrnt.getY()+currentY + (getSize()/2)); 
	    Point2D bottomLeft  = new Point2D(pCmpRelPrnt.getX()+currentX - (getSize()/2), pCmpRelPrnt.getY()+currentY  - (getSize()/2)); 
	    Point2D bottomRight = new Point2D(pCmpRelPrnt.getX()+currentX + (getSize()/2), pCmpRelPrnt.getY()+currentY  - (getSize()/2));
	    
	   // int [] xPts = new int [] {(int) top.getX(), (int) bottomLeft.getX(), (int) bottomRight.getX()} ; 
	    //int [] yPts = new int [] {(int) top.getY(), (int) bottomLeft.getY(), (int) bottomRight.getY()} ; 
	    g.fillTriangle((int)top.getX(),(int)top.getY(),(int)bottomLeft.getX(),(int)bottomLeft.getY(),(int)bottomRight.getX(),(int)bottomRight.getY()); 
		//g.fillTriangle(x1, y1, x2, y2, x3, y3);
	    // TODO Auto-generated method stub*/
	}
	
	public void move(Point2D dCmpSize) {
		// we need the ship to take its old direction into account when making a  decision in its course.
		//double theta = (90 - super.getDirection());
		 //double deltaX = Math.cos(theta)*super.getSpeed();
		 //double deltaY = Math.sin(theta) * super.getSpeed();
		// Point2D oldLocation = super.getLocation();
		// double oldX = oldLocation.getX();
		 //double oldY = oldLocation.getY();
		 //Point2D newLocation = new Point2D((oldX+deltaX),(oldY+deltaY));
		// super.setLocation(newLocation);
		//currentX += incX;
		 //currentY += incY;
		 //Point2D newLocation = new Point2D((currentX+deltaX),(oldY+deltaY));
		 //if(( currentX+shipSize>= dCmpSize.getX())|| (currentX <0))
			//incX = -incX;
		// if(( currentY+shipSize>= dCmpSize.getY())|| (currentY <0))
			//incY =-incY;
	}
	@Override
	public boolean collidesWith(ICollider anotherObject) {
		// TODO Auto-generated method stub
		boolean result = false;
		int thisCenterX = (int) (getLocation().getX() + (shipSize/2));
		int thisCenterY = (int) (getLocation().getY() + (shipSize/2));
		int otherCenterX = (int)(((GameObject) anotherObject).getLocation().getX());
		int otherCenterY = (int)(((GameObject) anotherObject).getLocation().getY());
		
		//now find distance between centers(use square, to avoid taking roots.
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterX - otherCenterY;
		int distBetweenCenterSqr = (dx*dx + dy*dy);
		
		//find square sum of radii
		int thisRadius = shipSize/2;
		int otherRadius = ((GameObject) anotherObject).getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+otherRadius*otherRadius);
		if(distBetweenCenterSqr<=radiiSqr) {
			System.out.println("Ship collision");
			result = true;
		}
		return result;
       /* float p1x = (float) getLocation().getX(); 
        float p2x = (float) (getLocation().getX() + getSize()); 
        float p3x = (float) getLocation().getX(); 
        float p4x = (float) (getLocation().getX() + getSize()); 
        float p1y = (float) getLocation().getY(); 
        float p2y = (float) getLocation().getY(); 
        float p3y = (float) (getLocation().getY() + getSize()); 
        float p4y = (float) (getLocation().getY() + getSize()); 

        GameObject mover = (GameObject) anotherObject; 

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
	}

	@Override
	public void handleCollision(ICollider anotherObject) {
		//Case 1: ship hits asteroid
		// 	explode the ship
		if(anotherObject instanceof Asteroid) {
			this.setFlag(true);
		}
		//Case 3: ship hits Missile
		//	explode the ship		
		//	explode the missile
		if(anotherObject instanceof Missile) {
			this.setFlag(false);
		}
		//Case 4: ship hits SpaceStation
		//	do not explode ship
		//	do not explode space station.
		if(anotherObject instanceof SpaceStation) {
			System.out.println("space ship hit!!");
			this.setFlag(false);
			this.setMissileCount(10);
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
