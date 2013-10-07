package org.maupu.breaker;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface IGameComponent {
	public void render(Graphics g);
	public void update(GameContainer container, int delta) throws SlickException;
	
}