package com.mosquito.games.model.strategy;

import java.util.List;

import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.ItemModel;
import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;

public class GrowToNextLevelStrategy implements GrowStrategy {
	BoardModel board;
	
	public GrowToNextLevelStrategy(BoardModel boardModel) {
		this.board = boardModel;
	}

	@Override
	public void execute() {
		for (int i = 0; i < BoardPosition.WIDTH; i++) {
			for (int j = 0; j < BoardPosition.HEIGHT; j++) {
				List<ItemModel> itemsAtPosition = board.getItemsAtPosition(i, j);
				if (itemsAtPosition.size() > 1) {
					growItems(itemsAtPosition);
				}
			}
		}
	}
	
	public void growItems(List<ItemModel> items) {
		int maxLevel = 0;
		int numberOfKings = 0;
		
		for (ItemModel item : items) {
			if (item.getType().level == ItemType.MAX_LEVEL) {
				numberOfKings++;
			}
			if (item.getType().level > maxLevel) {
				maxLevel = item.getType().level;
			}
		}

		ItemType grownType = null;
		if (numberOfKings > 1) {
			grownType = new ItemType(Color.CHUCK);
		} else {
			grownType = new ItemType(items.get(0).getType().color,
					Math.min(maxLevel + (items.size() - 1), ItemType.MAX_LEVEL));
		}
		
		board.growInto(items, grownType);
	}
}
