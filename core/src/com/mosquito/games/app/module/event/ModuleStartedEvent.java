package com.mosquito.games.app.module.event;

import com.badlogic.gdx.Screen;
import com.mosquito.games.app.system.event.GameEvent;

public class ModuleStartedEvent implements GameEvent<ModuleStartedListener> {
	Screen module;

	public ModuleStartedEvent(Screen screen) {
		this.module = screen;
	}

	@Override
	public void notify(ModuleStartedListener listener) {
		listener.moduleStarted(module);
	}
}
