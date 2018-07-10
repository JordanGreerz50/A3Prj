package com.mycompany.a3;

import java.io.IOException;
import java.util.Collection;
//import java.util.Vector;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Point2D;


public class GameWorld extends Observable implements IGameWorld {	
	private int gameClock=0;
	private int gameScore =0;
	private int shipLivesLeft =3;
	private int initMissiles;
	private static double mapWidth;
	private  static double mapHeight;
	private static Point2D mapDimensions = new Point2D(0,0);
	private GameObjectsCollection currentGameObjects;
	private boolean sound = false;
	//private Label checkStatusVal = new Label("OFF");
	private Collection start;
	private double minX, minY, maxX,maxY;
	private int curTime;
	private Vector<GameObject> collisionArray = new Vector<>();
	private Sound asterSound,saucerSound,missileSound,gameOverSound;
	private BGSound bgSound;
	
	
	
		public GameWorld()  {
		currentGameObjects = new GameObjectsCollection();
		this.init();
		
		bgSound= new BGSound("beat.wav");
		asterSound = new Sound("asteroidCrash.wav");
		saucerSound = new Sound("saucer.wav");
		gameOverSound = new Sound("gameOver.wav");
		missileSound = new Sound("missile.wav");
		
		bgSound.play();
	}

		public void init() {
		mapWidth =getWidth();
		mapHeight= getHeight();
		minX=(int) (1024-mapWidth);
		minY=(int) (768-mapHeight);
		maxX=1024;
		maxY=768;

			
	}
		
	
	public void addAsteroid() {
		Asteroid gameAsteroid = new Asteroid();
		//IIterator theElements = currentGameObjects.getIterator();
		currentGameObjects.add(gameAsteroid);
		System.out.println("congrats! ASter is in the vector");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void   addFlyingSaucer() {
		FlyingSaucer gameSaucer = new FlyingSaucer();
		currentGameObjects.add(gameSaucer);
		System.out.println("congrats! Saucer is in the vector");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	public void  addBlinkingStation() {
		SpaceStation gameStation = new SpaceStation();
		currentGameObjects.add(gameStation);
		System.out.println("blinking station added");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void  addShip() {
		
		Ship gameShip = new Ship();
		currentGameObjects.add(gameShip);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		//currentGameObjects.add(gameShip);
	}
	
	public  void increaseSpeed() {
		IIterator theElements = currentGameObjects.getIterator();
		Ship ship = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Ship)
				ship =(Ship) o;				
		}
		if(ship != null) {
			int shipSpeed = ship.getSpeed();
			if(shipSpeed !=10)
				ship.setSpeed(++shipSpeed);
			else 
				System.out.println("already at max speed");
			System.out.println("speed Increased");
		}
		else
			System.out.println("no ship exists yet!");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public  void decreaseSpeed() {
		IIterator theElements = currentGameObjects.getIterator();
		Ship ship = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Ship)
				ship =(Ship) o;				
		}
		if(ship != null) {
			int shipSpeed = ship.getSpeed();
			if(shipSpeed !=0)
				ship.setSpeed(--shipSpeed);
			else 
				System.out.println("already at zero speed");
			System.out.println("speed decreased");
		}
		else
			System.out.println("no ship exists yet!");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void fireMissile() {
		
		IIterator theElements = currentGameObjects.getIterator();
		Ship ship = null;
		int shipSpeed=0, shipDirection=0;
		int leftMCount=0;
		Point2D shipLocation = new Point2D(0,0);
		//get the ship object to be changed
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Ship)
				ship =(Ship) o;	
		}
		if(ship != null) {
			shipSpeed = ship.getShipSpeed();
			shipDirection = ship.getDirection();
			shipLocation = ship.getLocation();
			leftMCount = ship.getMissileCount();
			
		}
		else {
			System.out.println("no ship exists yet!");
		}
		if(leftMCount >= 0) {
			initMissiles = ship.getMissileCount();
			ship.setMissileCount(--leftMCount);
			Missile curMissile = new Missile(shipSpeed,shipDirection,shipLocation);	
			missileSound.play();
			currentGameObjects.add(curMissile);
		}
		else 
			System.out.println("your out of missiles!");
		
		//after missile is discharged, add a missile moving object into vector
		
		

		
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void hyperJump() { // places the ship at the center of the world
		
		IIterator theElements = currentGameObjects.getIterator();
		Ship ship = null;
		int shipSpeed=0, shipDirection=0;
		int leftMCount=0;
		Point2D shipLocation = new Point2D(0,0);
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Ship)
				ship =(Ship) o;	
		}
		if(ship != null) {
			shipSpeed = ship.getShipSpeed();
			shipDirection = ship.getDirection();
			shipLocation = ship.getLocation();
			leftMCount = ship.getMissileCount();
		}
		else 
			System.out.println("no ship exists yet!");
		
		Point2D newLocation = new Point2D(512,384);
		shipLocation = newLocation;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	public void loadMissile() {
		IIterator theElements = currentGameObjects.getIterator();
		Missile missile = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Missile) {
				missile =(Missile) o;
				missile.setmFuel(10);
			}		
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
			
		/*for(int i =0; i <currentGameObjects.size(); i++) {
			if(currentGameObjects.get(i) instanceof Ship) {
				Ship user = (Ship)currentGameObjects.get(i);
				//int newMissileCount = user.getMissileCount();
				int newMissileCount = 12;
				user.setMissileCount(newMissileCount);
			}
		}*/
	}
	public void  asteroidDestroyed() {
		//find  instanceof asteroid object
		IIterator theElements = currentGameObjects.getIterator();
		Asteroid asteroid = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Asteroid)
				asteroid =(Asteroid) o;	
		}
		//find instance of missile object
		IIterator theeElements = currentGameObjects.getIterator();
		Missile firedMissile = null;
		while(theeElements.hasNext()) {
			Object o = theeElements.getNext();
			if(o instanceof Missile)
				firedMissile =(Missile) o;	
		}
		if((asteroid != null)||(firedMissile != null)) {
			Random rand1 = new Random();
			int max = asteroid.getSize();
			int min = 0;
			int returnVal1 =rand1.nextInt(max-min)+min;
			int nextMax = max- returnVal1;
			int returnVal2 = rand1.nextInt(nextMax-min+1)+min;
			int returnVal3 = max-(returnVal1+returnVal2);
			
			//first case that asteroid is small
			if((asteroid.getSize()>=6) &&(asteroid.getSize()<=18)) {
				++gameScore;
				currentGameObjects.remove(asteroid);
				currentGameObjects.remove(firedMissile);
				System.out.println("asteroid was destroyed!");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			} //second case that asteroid is medium
			else if((asteroid.getSize() >= 19) &&(asteroid.getSize()<=24)) {
				
				int randomSize1 = rand1.nextInt();
				//int n =- randomSize1;
				//int size1 = randomSize1 +6;
				//int size2 =max+6;
				Asteroid randoAster1 = new Asteroid();
				randoAster1.setSize(returnVal1);
				Asteroid randoAster2 = new Asteroid();
				randoAster2.setSize(returnVal2);
				//destroy old asteroid
				currentGameObjects.remove(asteroid);
				currentGameObjects.remove(firedMissile);

				//create 2 new asteroids in the process
				currentGameObjects.add(returnVal1);
				currentGameObjects.add(returnVal2);
				//add 3 points
				gameScore = gameScore + 3;
				System.out.println("medium asteroid was destroyed!");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));

			}// final case in which is largest size, breaks into 3 pieces
			else if((asteroid.getSize() >= 25) &&(asteroid.getSize()<=30)) {
				//Random rand1 = new Random();
				//Random rand2 = new Random();
				//int randomSize2 = rand2.nextInt();
				//int randomSize1 = rand1.nextInt();
				//n-= randomSize1;
				//int size1 = randomSize1 +6;
				//n-= randomSize2;
				//int size2 = randomSize2 +6;
				//int size3 = n+6;
				Asteroid randoAster1 = new Asteroid();
				randoAster1.setSize(returnVal1);
				Asteroid randoAster2 = new Asteroid();
				randoAster2.setSize(returnVal2);
				Asteroid randoAster3 = new Asteroid();
				randoAster3.setSize(returnVal3);

				//destroy old asteroid
				currentGameObjects.remove(asteroid);
				currentGameObjects.remove(firedMissile);
				//create 2 new asteroids in the process
				currentGameObjects.add(randoAster1);
				currentGameObjects.add(randoAster2);
				currentGameObjects.add(randoAster3);
				//add 3 points
				gameScore = gameScore + 5;
				System.out.println("large asteroid was destroyed!");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}
		}
		else {
			System.out.println("Sorry! there is either no ship, or no asteroid on map!");
		
		/*for(int i =0; i < currentGameObjects.size(); i++) {
				if(currentGameObjects.get(i) instanceof Ship) {
					Ship user = (Ship)currentGameObjects.get(i);
					int newMissileCount = user.getMissileCount();
					newMissileCount = newMissileCount - 1;
					user.setMissileCount(newMissileCount);		
			}	
		}
		for(int i =0; i <currentGameObjects.size(); i++) {
			if(currentGameObjects.get(i) instanceof Asteroid) {
				currentGameObjects.remove(i);
				break;
			}*/		
		}
		//++gameScore;
	}
	
	public void  saucerDestroyed() {
		//find  instanceof ship object
				IIterator theElements = currentGameObjects.getIterator();
				Missile missile = null;
				while(theElements.hasNext()) {
					Object o = theElements.getNext();
					if(o instanceof Missile)
						missile =(Missile) o;	
				}
				//find instance of aster object
				IIterator theeElements = currentGameObjects.getIterator();
				FlyingSaucer saucer = null;
				while(theeElements.hasNext()) {
					Object o = theeElements.getNext();
					if(o instanceof FlyingSaucer)
						saucer =(FlyingSaucer) o;	
				}
				if((missile != null)||(saucer != null)) {
					gameScore += 2;
					currentGameObjects.remove(missile);
					--shipLivesLeft;
					currentGameObjects.remove(saucer);
					System.out.println("Flying Saucer was destroyed!");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
				else
					System.out.println("there is either no Saucer or ship on map!");

				
		
		/*for(int i =0; i <currentGameObjects.size(); i++) {
			if(currentGameObjects.get(i) instanceof Ship) {
				Ship user = (Ship)currentGameObjects.get(i);
				int newMissileCount = user.getMissileCount();
				newMissileCount = newMissileCount - 1;
				user.setMissileCount(newMissileCount);
			}
		}	
		for(int i =0; i <currentGameObjects.size(); i++) {
			if(currentGameObjects.get(i) instanceof FlyingSaucer) {
				currentGameObjects.remove(i);
				break;
			}		
		}
		++gameScore;
		++gameScore;*/
	}
	public void  asteroidCrashShip() {
		//find  instanceof ship object
		IIterator theElements = currentGameObjects.getIterator();
		Ship ship = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Ship)
				ship =(Ship) o;	
		}
		//find instance of aster object
		IIterator theeElements = currentGameObjects.getIterator();
		Asteroid aster = null;
		while(theeElements.hasNext()) {
			Object o = theeElements.getNext();
			if(o instanceof Asteroid)
				aster =(Asteroid) o;	
		}
		if((ship != null)||(aster != null)) {
			//gameScore += 2;
			currentGameObjects.remove(ship);

			
			currentGameObjects.remove(aster);
			System.out.println("ship was destroyed by asteroid!");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("there is either no asteroid or ship to crash on map!");
		
		
		/*//looks for first ship then removes it 
			for(int i =0; i <currentGameObjects.size(); i++) {
				if(currentGameObjects.get(i) instanceof Ship) {
					currentGameObjects.remove(i);
					break;
				}
				//looks for first asteroid then removes it 
			for(int idx =0; idx <currentGameObjects.size(); idx++) {
				if(currentGameObjects.get(idx) instanceof Asteroid) {
					currentGameObjects.remove(idx);
					break;
				}
			}
		}
		--shipLivesLeft;*/
		
	}
	public void  saucerCrashShip() {
		//find  instanceof ship object
		IIterator theElements = currentGameObjects.getIterator();
		Ship ship = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Ship)
				ship =(Ship) o;	
		}
		//find instance of aster object
		IIterator theeElements = currentGameObjects.getIterator();
		FlyingSaucer saucer = null;
		while(theeElements.hasNext()) {
			Object o = theeElements.getNext();
			if(o instanceof FlyingSaucer)
				saucer =(FlyingSaucer) o;	
		}
		if((ship != null)||(saucer != null)) {
			//gameScore += 2;
			currentGameObjects.remove(ship);
			--shipLivesLeft;
			
			currentGameObjects.remove(saucer);
			System.out.println("Flying Saucer crashed Ship!");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("there is either no Saucer or ship on map!");
		if(shipLivesLeft == 0) {
			gameOverSound.play();
			quitGame();
		}
		
		/*
		//looks for ship then removes it 
		for(int i =0; i <currentGameObjects.size(); i++) {
			if(currentGameObjects.get(i) instanceof Ship) {
				currentGameObjects.remove(i);
				break;
			}
			//looks for saucer then removes it 
		for(int idx =0; idx <currentGameObjects.size(); idx++) {
			if(currentGameObjects.get(idx) instanceof FlyingSaucer) {
				currentGameObjects.remove(idx);
				break;
			}
		}
	}
	--shipLivesLeft;*/
		
}
		
	
	public void  asteroidCrashAsteroid() {
		//find  instanceof aster object
		IIterator theElements = currentGameObjects.getIterator();
		Asteroid aster1 = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Asteroid)
				aster1 =(Asteroid) o;
		}
		currentGameObjects.remove(aster1);
		//find instance of aster object
		IIterator theeElements = currentGameObjects.getIterator();
		Asteroid aster2 = null;
		while(theeElements.hasNext()) {
			Object o = theeElements.getNext();
			if(o instanceof Asteroid)
				aster2 =(Asteroid) o;	
		}
		currentGameObjects.remove(aster2);
		System.out.println("two asteroids destroyed");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		/*//looks for asteroid to remove
		for(int idx =0; idx <currentGameObjects.size(); idx++) {
			if(currentGameObjects.get(idx) instanceof Asteroid) {
				currentGameObjects.remove(idx);
				break;
			}
		}
		//then we repeat same process to remove another asteroid from the game
		for(int idx =0; idx <currentGameObjects.size(); idx++) {
			if(currentGameObjects.get(idx) instanceof Asteroid) {
				currentGameObjects.remove(idx);
				break;
			}
		}*/
	}
	public void  saucerCrashAsteroid() {
		IIterator theElements = currentGameObjects.getIterator();
		Asteroid aster = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Asteroid)
				aster =(Asteroid) o;	
		}
		//find instance of aster object
		IIterator theeElements = currentGameObjects.getIterator();
		FlyingSaucer saucer = null;
		while(theeElements.hasNext()) {
			Object o = theeElements.getNext();
			if(o instanceof FlyingSaucer)
				saucer =(FlyingSaucer) o;	
		}
		if((aster != null)||(saucer != null)) {
			//gameScore += 2;
			currentGameObjects.remove(aster);
			currentGameObjects.remove(saucer);
			System.out.println("Flying Saucer crashed Asteroid!");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("there is either no Saucer or asteriod on map!");
		
	/*//looks for an asteroid to remove
		for(int idx =0; idx <currentGameObjects.size(); idx++) {
			if(currentGameObjects.get(idx) instanceof Asteroid) {
				currentGameObjects.remove(idx);
				break;
			}
		}
		//and then it deletes the a saucer from the gameObjects vector array
		for(int idx =0; idx <currentGameObjects.size(); idx++) {
			if(currentGameObjects.get(idx) instanceof FlyingSaucer) {
				currentGameObjects.remove(idx);
				break;
			}
		}
		*/
	}
	public void  clockTick() {
		//1.) all moveable objects  must update their position 
		//2.) every missile fuel level is reduced by one.
		//3.) each space station toggles its blinking rate
		//4.) 
		 //Point2D mapScreen = new Point2D(getWidth(),getHeight());
		 IIterator iterator = currentGameObjects.getIterator();
		 Object currentObj;
		 while(iterator.hasNext()) {
			 currentObj = iterator.getNext();
			 //run thru moveableObjects
			 
			 if(currentObj instanceof MoveableObject) 
				 ((MoveableObject)currentObj).move(gameClock);
			 
			 //deal with missile fuel
			 if(currentObj instanceof Missile) {
					Missile currentMissile =  (Missile)currentObj;
					int currentFuel = currentMissile.getmFuel();
					if(currentFuel == 0) {
						currentGameObjects.remove(currentMissile);
						System.out.println("ur missile fizzled out..");
						break;
					}
					else {
						currentMissile.setmFuel(--currentFuel);
					}
				 }
			 //now deal with station objects
		   	if((currentObj instanceof SpaceStation) && (gameClock% ((SpaceStation)currentObj).getBlinkRate()==0)) {
					boolean lightUp = ((SpaceStation)currentObj).getLight();
					((SpaceStation)currentObj).setLight(lightUp);
				}

		 }
		 //increment time
		gameClock++;		
		if(curTime+20 < gameClock){
			for(int i = 0; i < collisionArray.size(); i++){
				collisionArray.remove(i);
			}
		}
		collisionCheck();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		
	}
	
	public void collisionCheck() {
		IIterator colIterator = currentGameObjects.getIterator();

		while(colIterator.hasNext() ){

			ICollider curObj = (ICollider)colIterator.getNext();

			// get a collidable object 
			// check if this object collides with any OTHER object 
			IIterator iter2 = currentGameObjects.getIterator(); 

			while(iter2.hasNext()){ 
				ICollider otherObj = (ICollider)iter2.getNext(); 
				// get a collidable object 
				// check for collision 
				if(otherObj!=curObj){

					// make sure it's not the SAME object 

					if(curObj.collidesWith(otherObj))
						curObj.handleCollision(otherObj); 
					 
		removeCollided();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
				}
			}
		}
	}

	public void removeCollided() {
		GameObjectsCollection garbage = new GameObjectsCollection();
		IIterator remIterator = currentGameObjects.getIterator();
		Object target = new Object();
		while(remIterator.hasNext()) {
			target = remIterator.getNext();
			if(((GameObject)target).getFlag() == true) {
				garbage.add(target);
			}
		}
		garbageRemover(garbage);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void garbageRemover(GameObjectsCollection Garbage) {
		IIterator garIterator = Garbage.getIterator();
		while(garIterator.hasNext()) {
			Object target = garIterator.getNext();
			if(target instanceof Asteroid) {
				asterSound.play();
				asteroidDestroyed();
				currentGameObjects.remove(target);
			}
			if(target instanceof FlyingSaucer) {
				saucerSound.play();
				saucerDestroyed();
				currentGameObjects.remove(target);
			}
			if(target instanceof Ship) {
				
				//saucerCrashShip();
				currentGameObjects.remove(target);
				shipLivesLeft--;
				if(shipLivesLeft >= 1) {
					//System.out.println("player has lives: " + playerLives);
					//addShip();
					}
					else {
						gameOverSound.play();
						quitGame();
					}
			}
			if(target instanceof Missile) {
				missileSound.play();
				currentGameObjects.remove(target);
			}
		}
	}

	public void printMap() {
		//after overriding toString in all subclasses:
		//use a for loop to run thru the vector one object at a time.
		IIterator iterator = currentGameObjects.getIterator();
		while(iterator.hasNext())
			System.out.println(iterator.getNext().toString());

	}
	public void quitGame() {
		boolean b = Dialog.show("GAME OVER MAN", "your score was: " + gameScore, "Ok", null);
		if(b)
			System.exit(0);
		
	}

	public void leftTurn() {
		IIterator theElements = currentGameObjects.getIterator();
		Ship ship = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Ship)
				ship =(Ship) o;	
		}
		if(ship != null) {
			ship.turnLeft();
		}
		else
			System.out.println("no ship exists yet!");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	public void rightTurn() {
		IIterator theElements = currentGameObjects.getIterator();
		Ship ship = null;
		while(theElements.hasNext()) {
			Object o = theElements.getNext();
			if(o instanceof Ship)
				ship =(Ship) o;	
		}
		if(ship != null) {
			ship.turnRight();
		}
		else
			System.out.println("no ship exists yet!");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void soundOn() {
		sound = true;
		bgSound.play();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	

	public void soundOff() {
		sound = false;
		bgSound.pause();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	

	public void soundToggle(){
		sound = !sound;
		this.setChanged();
		notifyObservers(new GameWorldProxy(this));
	}	
	public void setWidth(int width) {
		mapDimensions.setX(width);
		
		
	}
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		mapDimensions.setY(height);
	}
	public static double getHeight() {
		return mapDimensions.getX();
	}
	public static double getWidth() {
		return mapDimensions.getY();
	}

	
	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return gameScore;
	}
	@Override
	public int getMissiles() {
		// TODO Auto-generated method stub
		return initMissiles;
	}
	@Override
	public int getElapsedTime() {
		// TODO Auto-generated method stub
		return gameClock;
	}
	@Override
	public boolean getSound() {
		// TODO Auto-generated method stub
		return sound;
	}
	@Override
	public int getLives() {
		return shipLivesLeft;
	}
	@Override
	public GameObjectsCollection getObjects() {
		return currentGameObjects;
	}

	public Collection getStart() {
		return start;
	}

	
}