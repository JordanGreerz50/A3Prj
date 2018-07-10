package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension2D;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;



public interface ISelectable {
	//a way to mark an object as "selected"or not
	void setSelected(boolean select);
	//a way to test whether an object is selected
	//pPtrRelPrnt is a pointer position relative to the parent origin
	//pCmpRelPrnt is the Component 
	boolean isSelected();
	//a way to determine if a pointer is "in" an object
	//pPtrRelPrn is a pointer position relative to the parent origin
	//pCmpRelPrnt is the Component position relative to the parent origin
	boolean contains(Point2D pPtrRelPrnt, Point2D pCmpRelPrnt);
	 // a way to draw the object that knows about drawing
	 // different ways depending on isSelected
	void draw(Graphics g, Point2D pCmpRelPrnt);

}