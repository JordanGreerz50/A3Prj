package com.mycompany.a3;
import com.codename1.ui.Display;
//import java.util.ArrayList;
//import java.util.Vector;
import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;




public abstract class GameObject implements ICollider{
	
	
	private int size;
	static double mapSizeY;
	static double mapSizeX;
	private Point2D location;
	private int  color;
	private boolean flag;
	
	
	
	
	public  double getMapSizeX() {
		return mapSizeX = GameWorld.getWidth();
	}

	public  double getMapSizeY() {
		return mapSizeY= GameWorld.getHeight();
	}

	

	public GameObject() {
		//setLocation(new Point2D(x, y));
		
		

	}

	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}



	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
}
