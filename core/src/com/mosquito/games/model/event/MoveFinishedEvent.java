package com.mosquito.games.model.event;

import com.mosquito.games.app.system.event.GameEvent;

public class MoveFinishedEvent implements GameEvent<MoveFinishedListener> {
	@Override
	public void notify(MoveFinishedListener listener) {
		listener.moveFinished();
	}
}
