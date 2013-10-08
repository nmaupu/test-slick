package org.maupu.breaker;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerBar implements IGameComponent {
	public static final int BAR_INITIAL_WIDTH=100;
	public static final int BAR_INITIAL_HEIGHT=20;
	public static final int BAR_INITIAL_X=260;
	public static final int BAR_INITIAL_Y=455;
	
	private Rectangle bounds;
	private float x,y;
	private int width, height;
	
	private IEventHandler notifyer;
	
	public PlayerBar(IEventHandler notifyer) {
		this.notifyer = notifyer;
		x = BAR_INITIAL_X;
		y = BAR_INITIAL_Y;
		width = BAR_INITIAL_WIDTH;
		height = BAR_INITIAL_HEIGHT;
		bounds = new Rectangle(x, y, width, height);
	}

	@Override
	public void render(Graphics g) {
		g.draw(bounds);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(input.isKeyDown(Keyboard.KEY_LEFT)) {
			x -= delta * 0.30f;
			if(x < 0)
				x = 0;
		} else if(input.isKeyDown(Keyboard.KEY_RIGHT)) {
			x += delta * 0.30f;
			if(x > container.getWidth()-bounds.getWidth())
				x = container.getWidth()-bounds.getWidth();
		}
		
		// Notify new location
		notifyer.eventOccurs(this);
		
		bounds.setLocation(x, y);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return bounds.getWidth();
	}
	
	public float getHeight() {
		return bounds.getHeight();
	}
	
	protected void setBarSize(float w, float h) {
		bounds.setSize(w, h);
	}

	@Override
	public int getID() {
		return GameState.PLAYER_BAR_ID;
	}

	@Override
	public Shape getShape() {
		return bounds;
	}
}
