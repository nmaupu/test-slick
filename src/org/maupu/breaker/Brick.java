package org.maupu.breaker;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

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
		g.draw(bounds);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
	}
	
	public boolean isAlive() {
		return isAlive;
	}
}
