package com.mosquito.games.model.event;

import com.mosquito.games.app.system.event.GameEvent;

public class MovesUpdatedEvent implements GameEvent<MovesUpdatedListener> {
	int countDown;
	
	public MovesUpdatedEvent(int countDown) {
		this.countDown = countDown;
	}

	@Override
	public void notify(MovesUpdatedListener listener) {
		listener.movesUpdated(countDown);
	}
}
