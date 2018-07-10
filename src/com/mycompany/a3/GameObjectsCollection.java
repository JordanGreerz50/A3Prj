package com.mycompany.a3;

import java.util.Vector;


public class GameObjectsCollection implements ICollection {
	private Vector<GameObject> objectCollection; 

	public GameObjectsCollection() {
		objectCollection = new Vector<GameObject>();	
		
	}
	
	
	public IIterator getIterator() {
		return new GameObjIterator();
		  
	}
		
	private class GameObjIterator implements IIterator {
		private  int currElementIndex;
		
		public GameObjIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if ( objectCollection.size() <= 0) return false;
			if(currElementIndex == objectCollection.size()-1) 
				return false;
			return true;
		}
		
		public Object getNext() {
			currElementIndex++;
			return(objectCollection.elementAt(currElementIndex));
		}
	}


	@Override
	public void add(Object newObject) {
		objectCollection.addElement((GameObject) newObject);
		
	}


	public void remove(Object newObject) {
		objectCollection.remove(newObject);
		
	}

}
