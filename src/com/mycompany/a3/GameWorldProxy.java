package com.mycompany.a3;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {
private GameWorld gw;

	public GameWorldProxy(GameWorld gameWorld) {
		gw = gameWorld;
	}
	public int getPoints() {
		return gw.getPoints();
	}
	@Override
	public int getMissiles() {
		// TODO Auto-generated method stub
		return gw.getMissiles();
	}
	@Override
	public int getElapsedTime() {
		// TODO Auto-generated method stub
		return gw.getElapsedTime();
	}
	@Override
	public boolean getSound() {
		// TODO Auto-generated method stub
		return gw.getSound();
	}
	@Override
	public int getLives() {
		return gw.getLives();
	}
	@Override
	public GameObjectsCollection getObjects() {
		return gw.getObjects();
	}
}
