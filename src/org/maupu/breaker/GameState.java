package org.maupu.breaker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState implements IEventHandler {
	public static final int PLAYER_BAR_ID = 0;
	public static final int BALL_ID = 1;
	public static final int BRICK_ID = 2;
	
	private PlayerBar player;
	private List<Ball> balls = new ArrayList<Ball>();
	private List<Brick> bricks = new ArrayList<Brick>();

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		player = new PlayerBar(this);
		
		balls.add(new Ball(player.getX()+player.getWidth()/2,
						player.getY()-Ball.BALL_DEFAULT_RADIUS, this));
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<12; j++) {
				bricks.add(new Brick(80+j*Brick.BRICK_WIDTH, 50+i*Brick.BRICK_HEIGHT));
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		player.render(g);
		
		Iterator<Ball> itBalls = balls.iterator();
		while(itBalls.hasNext()) {
			Ball b = itBalls.next();
			b.render(g);
		}
		
		Iterator<Brick> itBricks = bricks.iterator();
		while(itBricks.hasNext()) {
			Brick b = itBricks.next();
			b.render(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		player.update(container, delta);
		
		Iterator<Ball> itBalls = balls.iterator();
		while(itBalls.hasNext()) {
			itBalls.next().update(container, delta);
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void eventOccurs(IGameComponent src) {
		if(src instanceof PlayerBar) {
			// Bar is moving, so do the ball if not started
			Iterator<Ball> itBalls = balls.iterator();
			while(itBalls.hasNext()) {
				Ball b = itBalls.next();
				if(! b.isMoving())
					setBallPositionRelativeToBar(b);
			}
		} else if(src instanceof Ball) {
			// Ball is moving, checking collisions with bar or bricks
			Iterator<Ball> itBalls = balls.iterator();
			while(itBalls.hasNext()) {
				Ball ball = itBalls.next();
				
				if (ball.getShape().intersects(player.getShape())) {
					ball.setSpeedY(ball.getSpeedY()*-1);
					ball.setLocation(ball.getX(), player.getY() - (ball.getRadius()*2)); // ugly isn't it ?
				} else { // if touching bar, don't bother going through bricks
					int size = bricks.size();
					for (int i=0; i<size; i++) {
						Brick brick = bricks.get(i);
						if(ball.getShape().intersects(brick.getShape()) && brick.isAlive()) {
							brick.destroyIt();
						}
					} // for
				}
			} // while
		}
	}
	
	private void setBallPositionRelativeToBar(Ball b) {
		b.setLocation(player.getX()+player.getWidth()/2, player.getY()-b.getRadius());
	}
}
