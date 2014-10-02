package com.mosquito.games.model.event;

import com.mosquito.games.app.system.event.GameEvent;

public class MoveStartedEvent implements GameEvent<MoveStartedListener> {
	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}
	
	Direction direction;
	
	public MoveStartedEvent(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void notify(MoveStartedListener listener) {
		listener.moveStarted(direction);
	}
}
