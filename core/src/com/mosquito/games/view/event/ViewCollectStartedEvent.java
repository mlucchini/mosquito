package com.mosquito.games.view.event;

import com.mosquito.games.app.system.event.GameEvent;
import com.mosquito.games.shared.Color;

public class ViewCollectStartedEvent implements GameEvent<ViewCollectStartedListener> {
	Color color;
	
	public ViewCollectStartedEvent(Color color) {
		this.color = color;
	}

	@Override
	public void notify(ViewCollectStartedListener listener) {
		listener.collectStarted(color);
	}
}
