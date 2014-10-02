package com.mosquito.games.view.event;

import com.mosquito.games.app.system.event.GameEvent;

public class ViewItemGrownEvent implements GameEvent<ViewItemGrownListener> {
	public ViewItemGrownEvent() {		
	}

	@Override
	public void notify(ViewItemGrownListener listener) {
		listener.itemGrown();
	}
}
