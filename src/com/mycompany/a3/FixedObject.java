package com.mycompany.a3;

public abstract class FixedObject extends GameObject {

	private static int fixedID;
	private int blinkRate;
	private double locationX,locationY;
	
	
	
	public FixedObject() {
		
		// TODO Auto-generated constructor stub
	}


	public static int getFixedID() {
		return fixedID;
	}


	public static void setFixedID(int fixedID) {
		FixedObject.fixedID = fixedID;
	}


	public int getBlinkRate() {
		return blinkRate;
	}


	public void setBlinkRate(int blinkRate) {
		this.blinkRate = blinkRate;
	}
	public void setLocationX(double LocX) {
		locationX = LocX;
	}
	public void setLocationY(double locY) {
		locationY = locY;
	}


}
