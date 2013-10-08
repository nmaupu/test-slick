package org.maupu.breaker;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Brick implements IGameComponent {
	public static final int BRICK_WIDTH=40;
	public static final int BRICK_HEIGHT=15;
	
	private Rectangle bounds;
	private boolean isAlive = true;
	
	public Brick(int x, int y) {
		bounds = new Rectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT);
	}

	@Override
	public void render(Graphics g) {
		if(isAlive)
			g.draw(bounds);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void destroyIt() {
		isAlive = false;
	}

	@Override
	public int getID() {
		return GameState.BRICK_ID;
	}

	@Override
	public Shape getShape() {
		return bounds;
	}
}
