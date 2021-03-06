package org.maupu.breaker;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public interface IGameComponent {
	public void render(Graphics g);
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;
	public int getID();
	public Shape getShape();
}
