package org.maupu.breaker;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class PlayerBar implements IGameComponent {
	public static final int BAR_WIDTH=100;
	public static final int BAR_HEIGHT=20;
	public static final int BAR_INITIAL_X=260;
	public static final int BAR_INITIAL_Y=455;
	
	private Input input;
	private Rectangle bounds;
	private float x,y;
	
	public PlayerBar(Input input) {
		this.input = input;
		x=BAR_INITIAL_X;
		y=BAR_INITIAL_Y;
		bounds = new Rectangle(x, y, BAR_WIDTH, BAR_HEIGHT);
	}

	@Override
	public void render(Graphics g) {
		g.draw(bounds);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
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
		
		bounds.setLocation(x, y);
	}
	
}
