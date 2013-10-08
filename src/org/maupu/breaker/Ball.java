package org.maupu.breaker;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Ball implements IGameComponent {
	public static final int BALL_DEFAULT_RADIUS = 10;
	private Circle bounds;
	private float x,y;
	private float radius;
	private float speedx = 0.20f;
	private float speedy = -0.20f;
	private boolean isMoving = false;
	private boolean isAlive = true;
	
	public Ball(float initx, float inity) {
		x = initx;
		y = inity;
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
			x += speedx*delta;
			y += speedy*delta;
			
			if(x > container.getWidth()-radius || x < radius)
				speedx *= -1;
			if(y > container.getHeight()+radius) {
				// Ball entirely under bottom = looser !
				// For now, you are safe ;)
				speedy *= -1;
			}
			if(y < radius)
				speedy *= -1;
			
			setLocation(x, y);
		}
	}
	
	public void setLocation(float x, float y) {
		// set location is upper left corner of the shape, not centered
		bounds.setLocation(x-radius, y-radius);
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
}
