package com.mosquito.games.model.strategy;

import com.mosquito.games.model.event.MoveStartedEvent.Direction;

public interface ShiftStrategy {
	public void execute(Direction direction);
}
