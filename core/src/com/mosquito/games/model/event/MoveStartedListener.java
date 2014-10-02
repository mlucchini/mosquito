package com.mosquito.games.model.event;

import com.mosquito.games.model.event.MoveStartedEvent.Direction;

public interface MoveStartedListener {
	public void moveStarted(Direction direction);
}
