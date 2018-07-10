package com.mycompany.a3;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;

public  class MapView extends Component implements Observer {

	private GameWorld gameWorld;
	private GameObjectsCollection gameObjects;
	GameObject gObject;
	private int iPx= 0;
				
	
	//private Point2D mapSize;
	//private Collection gameStart;
	//private Dimension mapDimension =Dimension.getdCmpSize();
	private int iPy = 0;
	
	
	public MapView(GameWorld gw) {
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		gameWorld = gw; 
		

	}
	
	public void update(Observable o, Object arg) {
		gameWorld =(GameWorld)o;
		GameWorldProxy gw = (GameWorldProxy)arg;
		gameObjects = gw.getObjects();
		IIterator gameIterator = gameObjects.getIterator();
		while(gameIterator.hasNext()) {
			//System.out.println(gameIterator.getNext());
			
			GameObject obj = (GameObject)gameIterator.getNext();
			if(obj instanceof  SpaceStation) {
				if(((IGameWorld)o).getElapsedTime() %((SpaceStation)obj).getBlinkRate()==0) {
					((SpaceStation)obj).setLight(true);
				} else {
					((SpaceStation)obj).setLight(false);
				}	
			}
		}
			
		
		//gameStart = gameWorld.getStart();
		
		/*IIterator iterator = ((IGameWorld)o).getObjects().getIterator();
		while(iterator.hasNext()) 
			 gObject = (GameObject)iterator.getNext();
			 if(gObject instanceof SpaceStation) {
			 }*/
		repaint();
		
	}
	public void paint(Graphics g) {
		if (gameObjects == null) return;
		else {
			super.paint(g);
			Point2D pCmpRelativePrint= new Point2D(this.getX(),this.getY());
			IIterator iterator = gameObjects.getIterator();

			while(iterator.hasNext()) {
				pCmpRelativePrint = new Point2D(this.getX(), this.getY());
				IDrawable drawObj = (IDrawable)iterator.getNext();
				drawObj.draw(g, pCmpRelativePrint);
			}
		}	
	}
	@Override
	public void pointerPressed(int x,int y){
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		
		Point2D pPtrRelPrnt = new Point2D(x, y);
		Point2D pCmpRelativePrint = new Point2D(this.getX(), this.getY());
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			ISelectable selectedObject = (ISelectable)iterator.getNext();
		    if (selectedObject.contains(pPtrRelPrnt, pCmpRelativePrint)) 
		    		selectedObject.setSelected(true);
		    	else {
		    		selectedObject.setSelected(false);
		    	} 	
		    }
		repaint();
		}
}	

