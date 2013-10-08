package org.maupu.breaker;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LooseState extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("Game over !", container.getWidth()/2, container.getHeight()/2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		if(input.isKeyDown(Keyboard.KEY_RETURN)) {
			// Restart game
			game.enterState(GameState.STATE_GAME);
		}
	}

	@Override
	public int getID() {
		return GameState.STATE_GAME_OVER;
	}

}
