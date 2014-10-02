package com.mosquito.games.view.event;

import com.mosquito.games.app.system.event.GameEvent;
import com.mosquito.games.view.actor.ItemActor;

public class ViewItemTouchedEvent implements GameEvent<ViewItemTouchedListener> {
	ItemActor itemActor;

	public ViewItemTouchedEvent(ItemActor itemActor) {
		this.itemActor = itemActor;
	}

	@Override
	public void notify(ViewItemTouchedListener listener) {
		listener.itemTouched(itemActor);
	}
}
