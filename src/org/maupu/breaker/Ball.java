package org.maupu.breaker;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Ball implements IGameComponent {
	public static final int BALL_DEFAULT_RADIUS = 10;
	private Circle bounds;
	private float x,y;
	private float radius;
	private float speedx = 5;
	private float speedy = 5;
	private boolean isMoving = false;
	private boolean isAlive = true;
	private IEventHandler eventHandler;
	
	public Ball(float initx, float inity, IEventHandler eventHandler) {
		x = initx;
		y = inity;
		this.eventHandler = eventHandler;
		radius = BALL_DEFAULT_RADIUS;
		bounds = new Circle(x, y, radius);
	}
	
	@Override
	public void render(Graphics g) {
		g.draw(bounds);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(input.isKeyDown(Keyboard.KEY_SPACE)) {
			// Launch ball
			isMoving = true;
		}
		
		if(isMoving) {
			x += speedx;
			y += speedy;
			
			if(x > container.getWidth()-radius || x < radius)
				speedx *= -1;
			if(y > container.getHeight()+radius) {
				// Ball entirely under bottom = looser !
				// For now, you are safe ;)
				speedy *= -1;
			}
			if(y < radius)
				speedy *= -1;
			
			// Ball is moving
			eventHandler.eventOccurs(this);
			setLocation(x, y);
		}
	}
	
	public void setLocation(float x, float y) {
		// set location is upper left corner of the shape, not centered
		this.x = x;
		this.y = y;
		bounds.setLocation(x-radius, y-radius);
	}
	
	public void setSpeedX(float speedx) {
		this.speedx = speedx;
	}
	
	public void setSpeedY(float speedy) {
		this.speedy = speedy;
	}
	
	public float getSpeedX() {
		return speedx;
	}
	
	public float getSpeedY() {
		return speedy;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	@Override
	public int getID() {
		return GameState.BALL_ID;
	}

	@Override
	public Shape getShape() {
		return bounds;
	}
}
