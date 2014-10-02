package com.mosquito.games.model.strategy;

import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.ItemModel;

public abstract class PerformOnNearbyItemsSelectStrategy implements SelectStrategy {
	BoardModel boardModel;

	public PerformOnNearbyItemsSelectStrategy(BoardModel boardModel) {
		this.boardModel = boardModel;
	}

	@Override
	public void execute(ItemModel itemModel) {
		int x = itemModel.getPosition().x;
		int y = itemModel.getPosition().y;
		
		performActionOnNearbyItem(boardModel.getItemAtPosition(x - 1, y - 1));
		performActionOnNearbyItem(boardModel.getItemAtPosition(x - 1, y));
		performActionOnNearbyItem(boardModel.getItemAtPosition(x - 1, y + 1));
		performActionOnNearbyItem(boardModel.getItemAtPosition(x, y - 1));
		performActionOnNearbyItem(boardModel.getItemAtPosition(x, y + 1));
		performActionOnNearbyItem(boardModel.getItemAtPosition(x + 1, y - 1));
		performActionOnNearbyItem(boardModel.getItemAtPosition(x + 1, y));
		performActionOnNearbyItem(boardModel.getItemAtPosition(x + 1, y + 1));
		
		itemModel.remove();
	}

	protected abstract void performActionOnNearbyItem(ItemModel item);
}
