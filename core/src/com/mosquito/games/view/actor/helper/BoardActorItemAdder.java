package com.mosquito.games.view.actor.helper;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mosquito.games.view.actor.ItemActor;

public class BoardActorItemAdder {
	public static void addSortedByDepth(Group items, ItemActor newItemActor) {
		for (Actor actor : items.getChildren()) {
			ItemActor itemActor = (ItemActor) actor;
			if (itemActor.getZ() > newItemActor.getZ()) {
				items.addActorBefore(itemActor, newItemActor);
				return;
			}
		}
		items.addActor(newItemActor);
	}
}
