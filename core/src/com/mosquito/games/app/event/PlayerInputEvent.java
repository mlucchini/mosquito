package com.mosquito.games.app.event;

import com.mosquito.games.app.system.event.GameEvent;

public class PlayerInputEvent implements GameEvent<PlayerInputListener> {
	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}
	
	Direction direction;
	
	public PlayerInputEvent(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void notify(PlayerInputListener listener) {
		listener.input(direction);
	}
}
