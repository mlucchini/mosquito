package com.mosquito.games.app.event;

import com.mosquito.games.app.system.event.GameEvent;

public class AppStartedEvent implements GameEvent<AppStartedListener> {

	@Override
	public void notify(AppStartedListener listener) {
		listener.appStarted();
	}
}
