package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;

import java.util.Random;
public final class Asteroid extends MoveableObject implements IMoveable, IDrawable, ICollider,ISelectable {

	private int aSize, aSpeed, aDirection,incX,incY;
	private double  currentX,currentY;
	private Point2D aLocation= new Point2D(0,0);
	private int gameSizeX = (int) GameWorld.getWidth(),gameSizeY = (int) GameWorld.getHeight();
	private boolean selected;

	//static int gameSizeWidth = Display.getInstance().getDisplayWidth();
	//static int gameSizeHeight = Display.getInstance().getDisplayHeight();
	
	
	
	public Asteroid() {
		// Things asteroid must start with:
		
		//random location set and placed within gameObjects
		
		super.setLocation(RandomLocation(aLocation));
		aLocation = super.getLocation();
		currentX = (int) aLocation.getX();
		currentY = (int)aLocation.getY();
		//will have a random speed, direction, when created
		super.setSpeed(RandomSpeed(aSpeed));
		aSpeed = super.getSpeed();
		super.setDirection(RandomDirection(aDirection));
		aDirection = super.getDirection();
		
		//fixed size(small 10, large 20)
		
		super.setSize(RandomSize(aSize));
		aSize = super.getSize();
		
		//plus color.
		//super.setColor(ColorUtil.CYAN);
	}
	
	public Point2D RandomLocation(Point2D l) {
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
	
	public int RandomSpeed(int s) {
		int min = 1;
		int max = 6;
		int rSpeed;
		Random r = new Random();
		rSpeed = r.nextInt((max-min+1) + min);
		return rSpeed;
	}
	
	public int RandomDirection(int d) {
		Random rand = new Random();
		int rDirection= rand.nextInt(359);
		return rDirection;
	}
	
	public int RandomSize(int rZ) {
		int min = 6;
		int max = 30;
		int rSize;
		Random r = new Random();
		rSize = r.nextInt((max-min+1) + min);
		return rSize;
		
	}

	@Override
	public String toString() {
		//String parentDescription = super.toString();
		String myColorDescription = "color: " + "[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		
		return "Asteroid: loc=" + super.getLocation() + ", " + myColorDescription + " speed=" +  super.getSpeed() +" dir=" + super.getDirection() + " size=" + super.getSize() + "]";
	}
	@Override
	public boolean collidesWith(ICollider anotherObject) {
		// TODO Auto-generated method stub
		boolean result = false;
		int thisCenterX = (int) (getLocation().getX() + (aSize/2));
		int thisCenterY = (int) (getLocation().getY() + (aSize/2));
		int otherCenterX = (int)(((GameObject) anotherObject).getLocation().getX());
		int otherCenterY = (int)(((GameObject) anotherObject).getLocation().getY());
		
		//now find distance between centers(use square, to avoid taking roots.
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterX - otherCenterY;
		int distBetweenCenterSqr = (dx*dx + dy*dy);
		
		//find square sum of radii
		int thisRadius = aSize/2;
		int otherRadius = ((GameObject) anotherObject).getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+otherRadius*otherRadius);
		if(distBetweenCenterSqr<=radiiSqr) {
			System.out.println("aster collision");
			result = true;
		}
			

		return result;
	}
	@Override
	public void handleCollision(ICollider anotherObject) {

		//Case 1: asteroid hits ship

		//	ship loses life

		// 	asteroid explodes

		if(anotherObject instanceof Ship) {

			this.setFlag(true);

			//((SpaceShip)otherObject).setFlag(true);

		}

		//Case 2: asteroid hits asteroid

		// 	explode the asteroids

		if(anotherObject instanceof Asteroid) {

			this.setFlag(true);

		}

		//Case 3: Asteroid hits Missile

		//	explode the asteroid		

		//	explode the missile

		if(anotherObject instanceof Missile) {

			this.setFlag(true);

		}

		//Case 4: Asteroid hits SpaceStation

		//	do not explode asteroid

		//	do not explode space station.

		if(anotherObject instanceof SpaceStation) {

			this.setFlag(false);

		}

	}
	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		// TODO Auto-generated method stub
			aSize = getSize();
			currentX = (int) getLocation().getX();

			currentY = (int) getLocation().getY();
			incX = getSpeed();
			incY = getSpeed();
			
			
			if(isSelected()) {
				g.setColor(ColorUtil.CYAN);
				g.drawArc((int)pCmpRelPrnt.getX()+(int)currentX, (int)pCmpRelPrnt.getY()+(int)currentY, aSize, aSize, 0, 360);
				
			} else {
				g.setColor(ColorUtil.GRAY);
				g.fillArc((int)pCmpRelPrnt.getX()+(int)currentX, (int)pCmpRelPrnt.getY()+(int)currentY, aSize, aSize, 0, 360);
				//move();
			}
		}
	public void move(Point2D dCmpSize) {
		
		//super.move();
		 //Point2D oldLocation = super.getLocation();
		 //double oldX = oldLocation.getX();
		 //double oldY = oldLocation.getY();
		// currentX += incX;
		// currentY += incY;
		 //Point2D newLocation = new Point2D((currentX+deltaX),(oldY+deltaY));
		// if(( currentX+aSize>= dCmpSize.getX())|| (currentX <0))
		//	incX = -incX;
		// if(( currentY+aSize>= dCmpSize.getY())|| (currentY <0))
		//	incY =-incY;

		 //super.setLocation(newLocation);
			   

	}

	@Override
	public void setSelected(boolean select) {
		selected = select;
		// TODO Auto-generated method stub
		
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
