package com.mosquito.games.view.action;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.app.system.event.GameEvent;

@SuppressWarnings("rawtypes")
public class DispatchViewEventAction extends RunnableAction {
	GameEvent event;
	
	public <L> DispatchViewEventAction(GameEvent<L> event) {
		this.event = event;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		EventManager.dispatch(event);
	}
}
