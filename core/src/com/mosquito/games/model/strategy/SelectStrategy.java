package com.mosquito.games.model.strategy;

import com.mosquito.games.model.ItemModel;

public interface SelectStrategy {
	public boolean wantsToHandle(ItemModel itemModel);
	public void execute(ItemModel itemModel);
}
