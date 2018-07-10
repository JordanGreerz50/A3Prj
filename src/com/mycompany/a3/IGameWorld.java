package com.mycompany.a3;
//code here to accept and hold a GameWorld, provide implementations of all the public methods in
//a GameWorld, forward allowed calls to the actual GameWorld, and reject calls to methods which
// the outside should no be able to access in the GameWorld.
public interface IGameWorld {
	//specifications here for all GameWorld Methods
	public int getPoints();
	public int getMissiles();
	public int getElapsedTime();
	public boolean getSound();
	public int getLives();
	public GameObjectsCollection getObjects();
}
