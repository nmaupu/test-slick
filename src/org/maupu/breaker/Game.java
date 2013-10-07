package org.maupu.breaker;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	public static final String GAME_NAME="Breaker";

	public Game(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new GameState());
	}

	public static void main (String[] args) {
		try {
			AppGameContainer agc = new AppGameContainer(new Game(Game.GAME_NAME));
			agc.setDisplayMode(640, 480, false);
			agc.setTargetFrameRate(60);
			agc.setMultiSample(4);
			agc.setVSync(true);
			agc.start();
		} catch(SlickException se) {
			se.printStackTrace();
		}
	}
}
