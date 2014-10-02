package com.mosquito.games.model.strategy;

import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.ItemModel;
import com.mosquito.games.shared.Color;

public class RemoveNearbyItemsSelectStrategy extends PerformOnNearbyItemsSelectStrategy {
	public RemoveNearbyItemsSelectStrategy(BoardModel boardModel) {
		super(boardModel);
	}

	@Override
	protected void performActionOnNearbyItem(ItemModel itemModel) {
		if (itemModel != null) {
			itemModel.explode();
		}	
	}

	@Override
	public boolean wantsToHandle(ItemModel itemModel) {
		return itemModel.getType().color == Color.CHUCK;
	}
}
