package com.mosquito.games.model.event;

import com.mosquito.games.app.system.event.GameEvent;
import com.mosquito.games.shared.Color;

public class CollectItemEvent implements GameEvent<CollectItemListener> {
	Color color;

	public CollectItemEvent(Color color) {
		this.color = color;
	}

	@Override
	public void notify(CollectItemListener listener) {
		listener.itemCollected(color);
	}
}
