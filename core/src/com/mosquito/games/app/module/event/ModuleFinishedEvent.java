package com.mosquito.games.app.module.event;

import com.mosquito.games.app.system.event.GameEvent;

public class ModuleFinishedEvent implements GameEvent<ModuleFinishedListener> {

	@Override
	public void notify(ModuleFinishedListener listener) {
		listener.moduleFinished();
	}
}
