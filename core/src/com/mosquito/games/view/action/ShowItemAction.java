package com.mosquito.games.view.action;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.after;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.mosquito.games.shared.ItemType;
import com.mosquito.games.view.actor.ItemActor;
import com.mosquito.games.view.actor.anim.ItemAnim;

public class ShowItemAction extends RunnableAction {
	ItemType itemType;
	ItemActor itemActor;

	public ShowItemAction(ItemType itemType, ItemActor itemActor) {
		this.itemType = itemType;
		this.itemActor = itemActor;
	}

	@Override
	public void run() {
		itemActor.setVisible(true);
		if (itemType.level == ItemType.FIRST_LEVEL) {
			itemActor.addAction(after(ItemAnim.getSpawned()));
    	} else {
    		itemActor.addAction(after(ItemAnim.getMerge(itemActor)));
    	}
	}
}
