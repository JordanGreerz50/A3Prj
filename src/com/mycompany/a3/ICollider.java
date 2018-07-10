package com.mycompany.a3;

public interface ICollider {
	boolean collidesWith(ICollider anotherObject);
	void handleCollision(ICollider anotherObject);

}
