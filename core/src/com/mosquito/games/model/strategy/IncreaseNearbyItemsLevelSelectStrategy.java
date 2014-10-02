package com.mosquito.games.model.strategy;

import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.ItemModel;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;

public class IncreaseNearbyItemsLevelSelectStrategy extends PerformOnNearbyItemsSelectStrategy {
	public IncreaseNearbyItemsLevelSelectStrategy(BoardModel boardModel) {
		super(boardModel);
	}

	@Override
	protected void performActionOnNearbyItem(ItemModel item) {
		if (item != null) {
			ItemType type = item.getType();
			if (type.level > 0 && type.level < (ItemType.MAX_LEVEL)) {
				type.level++;
				item.remove();
				ItemModel increasedItem = boardModel.addItem(item.getPosition(), type);
				increasedItem.show();
			}
		}
	}

	@Override
	public boolean wantsToHandle(ItemModel itemModel) {
		return itemModel.getType().color == Color.CHUCK;
	}
}
