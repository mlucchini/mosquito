package com.mosquito.games.model.event;

import com.mosquito.games.app.system.event.GameEvent;
import com.mosquito.games.model.ItemModel;

public class ItemSelectedEvent implements GameEvent<ItemSelectedListener> {
	ItemModel itemModel;

	public ItemSelectedEvent(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	@Override
	public void notify(ItemSelectedListener listener) {
		listener.itemSelected(itemModel);
	}
}
