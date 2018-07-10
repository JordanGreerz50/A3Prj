package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public final class SpaceStation extends FixedObject implements IDrawable, ICollider {
	private static int stationID;
	private int stationSize;
	private int stationBlinkRate,currentX,currentY;
	private Point2D stationLocation, screenDimension= new Point2D(0,0);
	private int gameSizeX = (int) GameWorld.getWidth(),gameSizeY = (int) GameWorld.getHeight();

	private static int missileMax = 12;
	private boolean light;
	
	public SpaceStation() {
		//needs:
		//random location & random blink rate
		stationSize = 20;
		super.setLocation(RandomFLocation(stationLocation));
		stationLocation = super.getLocation();
		super.setBlinkRate(RandomBlinkRate(stationBlinkRate));
		stationBlinkRate = super.getBlinkRate();
		//its fixed id must be made
		super.setFixedID(stationID);
		stationID = super.getFixedID();
		//also color
	}
	public boolean getLight() {
		return light;
	}
	public void setLight(boolean light) {
		this.light = light;
	}
	public int getStationBlinkRate() {
		return stationBlinkRate;
	}
	public void setStationBlinkRate(int stationBlinkRate) {
		this.stationBlinkRate = stationBlinkRate;
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
		
		/*Random  randomX = new Random();
		Random randomY = new Random();
		int x, y;
		x = randomX.nextInt(1024);
		y = randomY.nextInt(768);
		Point2D randomLocation = new Point2D(x,y);
		return randomLocation;*/
	}
	public int RandomBlinkRate(int b) {
		Random r = new Random();
		int blink = r.nextInt(5) +1;
		return blink;
		
	}
	
	public  int SetMissileCount(int m) {
		m = missileMax;
		return m;
	}
	
	@Override
	public String toString() {
		String myColorDescription = "color: " + "[" + ColorUtil.red(super.getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + " " +stationSize+" "+ "]";
		
		return "SpaceStation: loc=" + super.getLocation() + "," + myColorDescription + " rate=" + stationBlinkRate;
	}
	@Override
	public boolean collidesWith(ICollider anotherObject){
		// TODO Auto-generated method stub
		boolean result = false;
		int thisCenterX = (int) (getLocation().getX() + (stationSize/2));
		int thisCenterY = (int) (getLocation().getY() + (stationSize/2));
		int otherCenterX = (int)(((GameObject) anotherObject).getLocation().getX());
		int otherCenterY = (int)(((GameObject) anotherObject).getLocation().getY());
		
		//now find distance between centers(use square, to avoid taking roots.
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterX - otherCenterY;
		int distBetweenCenterSqr = (dx*dx + dy*dy);
		
		//find square sum of radii     
		int thisRadius = stationSize/2;
		int otherRadius = ((GameObject) anotherObject).getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+otherRadius*otherRadius);
		if(distBetweenCenterSqr<=radiiSqr) {
			result = true;
		}
		return result;
	}/*{
		// TODO Auto-generated method stub
		boolean result = false;
		int thisCenterX = (int) (getLocation().getX() + (stationSize/2));
		int thisCenterY = (int) (getLocation().getY() + (stationSize/2));
		int otherCenterX = (int)(anotherObject.getLocation().getX());
		int otherCenterY = (int)(anotherObject.getLocation().getY());
		
		//now find distance between centers(use square, to avoid taking roots.
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterX - otherCenterY;
		int distBetweenCenterSqr = (dx*dx + dy*dy);
		
		//find square sum of radii
		int thisRadius = stationSize/2;
		int otherRadius = anotherObject.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+otherRadius*otherRadius);
		if(distBetweenCenterSqr<=radiiSqr) {
			System.out.println("station collision");
			result = true;
		}
		
		return result;
	}*/
	@Override
	public void handleCollision(ICollider anotherObject) {
		System.out.println("station collided with ship");
	}
	
	@Override
	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		// TODO Auto-generated method stub
		currentX = (int) getLocation().getX();
		currentY = (int) getLocation().getY();
		if(light == false) {
			//super.setColor(ColorUtil.MAGENTA);
			g.setColor(ColorUtil.MAGENTA);
			g.drawArc((int)pCmpRelPrnt.getX()+(int)currentX, (int)pCmpRelPrnt.getY()+(int)currentY, stationSize, stationSize, 0, 360);
		}if(light == true) {
			g.setColor(ColorUtil.MAGENTA);
			g.fillArc((int)pCmpRelPrnt.getX()+(int)currentX, (int)pCmpRelPrnt.getY()+(int)currentY, stationSize, stationSize, 0, 360);
		}
		
	}
	
	

}
