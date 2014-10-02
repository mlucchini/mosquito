package com.mosquito.games.view.action;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.mosquito.games.view.actor.ItemActor;


/**
 * This was supposed to remove the actor from the stage but
 * this would stop its action queue from being updated which
 * would make our life harder as we may want to queue actions
 * behind a disappeared actor.
 * At least this way the Actor's draw method will not be called 
 * anymore.
 */

public class HideItemActorAction extends RunnableAction {
	ItemActor itemActor;

	public HideItemActorAction(ItemActor itemActor) {
		this.itemActor = itemActor;
	}

	@Override
	public void run() {
		itemActor.setVisible(false);
		itemActor.dispose();
	}
}
