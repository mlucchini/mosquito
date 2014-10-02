package com.mosquito.games.model.helper;

import java.util.ArrayList;
import java.util.List;

import com.mosquito.games.model.ItemModel;

public class BoardModelPositionHelper {
	public static ItemModel getItemAtPosition(List<ItemModel> itemModels, int x, int y) {
		for (ItemModel item : itemModels) {
			if (item.getPosition().x == x && item.getPosition().y == y) {
				return item;
			}
		}
		return null;
	}

	public static List<ItemModel> getItemsAtPosition(List<ItemModel> itemModels, int x, int y) {
		List<ItemModel> itemModelsAtPosition = new ArrayList<ItemModel>();
		for (ItemModel item : itemModels) {
			if (item.getPosition().x == x && item.getPosition().y == y) {
				itemModelsAtPosition.add(item);
			}
		}
		return itemModelsAtPosition;
	}
}
