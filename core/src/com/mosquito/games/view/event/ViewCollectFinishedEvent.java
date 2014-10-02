package com.mosquito.games.view.event;

import com.mosquito.games.app.system.event.GameEvent;
import com.mosquito.games.shared.Color;

public class ViewCollectFinishedEvent implements GameEvent<ViewCollectFinishedListener> {
	Color color;
	
	public ViewCollectFinishedEvent(Color color) {
		this.color = color;
	}

	@Override
	public void notify(ViewCollectFinishedListener listener) {
		listener.collectFinished(color);
	}
}
