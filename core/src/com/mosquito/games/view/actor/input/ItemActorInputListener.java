package com.mosquito.games.view.actor.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.view.actor.ItemActor;
import com.mosquito.games.view.event.ViewItemTouchedEvent;

public class ItemActorInputListener extends InputListener {
	ItemActor itemActor;

	public ItemActorInputListener(ItemActor itemActor) {
		this.itemActor = itemActor;
	}

	@Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		EventManager.dispatch(new ViewItemTouchedEvent(itemActor));
		return true;
    }
}
