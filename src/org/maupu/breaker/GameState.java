package org.maupu.breaker;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {
	private Input input;
	private PlayerBar player;
	private List<Brick> bricks = new ArrayList<Brick>();

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		player = new PlayerBar(input);
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<12; j++) {
				bricks.add(new Brick(80+j*Brick.BRICK_WIDTH, 50+i*Brick.BRICK_HEIGHT));
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		player.render(g);
		
		int size = bricks.size();
		for (int i=0; i<size; i++) {
			bricks.get(i).render(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		player.update(container, delta);
	}

	@Override
	public int getID() {
		return 1;
	}
}
