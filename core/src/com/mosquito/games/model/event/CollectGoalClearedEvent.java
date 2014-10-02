package com.mosquito.games.model.event;

import com.mosquito.games.app.system.event.GameEvent;
import com.mosquito.games.shared.Color;

public class CollectGoalClearedEvent implements GameEvent<CollectGoalClearedListener> {
	Color color;
	
	public CollectGoalClearedEvent(Color color) {
		this.color = color;
	}

	@Override
	public void notify(CollectGoalClearedListener listener) {
		listener.goalCleared(color);
	}
}
