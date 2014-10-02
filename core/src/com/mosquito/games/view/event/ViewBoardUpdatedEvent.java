package com.mosquito.games.view.event;

import com.mosquito.games.app.system.event.GameEvent;

public class ViewBoardUpdatedEvent implements GameEvent<ViewBoardUpdatedListener> {
	boolean areItemsIdle;

	public ViewBoardUpdatedEvent(boolean areItemsIdle) {
		this.areItemsIdle = areItemsIdle;
	}

	@Override
	public void notify(ViewBoardUpdatedListener listener) {
		listener.boardUpdated(areItemsIdle);
	}
}
