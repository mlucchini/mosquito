package com.mosquito.games.model.strategy;

import java.util.ArrayList;
import java.util.List;

import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.ItemModel;
import com.mosquito.games.model.event.CollectItemEvent;
import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.shared.CollectPoint;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;

public class CollectCornersStrategy implements CollectStrategy {
	static final CollectPoint TOP_LEFT_CORNER = new CollectPoint(Color.YELLOW, new BoardPosition(0, 0));
	static final CollectPoint TOP_RIGHT_CORNER = new CollectPoint(Color.RED, new BoardPosition(BoardPosition.LAST_COLUMN, 0));
	static final CollectPoint BOTTOM_LEFT_CORNER = new CollectPoint(Color.GREEN, new BoardPosition(0, BoardPosition.LAST_ROW));
	static final CollectPoint BOTTOM_RIGHT_CORNER = new CollectPoint(Color.BLUE, new BoardPosition(BoardPosition.LAST_COLUMN, BoardPosition.LAST_ROW));

	BoardModel boardModel;

	public CollectCornersStrategy(BoardModel boardModel) {
		this.boardModel = boardModel;
	}

	@Override
	public void execute() {
		 List<CollectPoint> collectionPositions = getCollectionPositions();
		 for (CollectPoint corner : collectionPositions) {
			 ItemModel item = boardModel.getItemAtPosition(corner.boardPosition.x, corner.boardPosition.y);
			 if (item != null && isCollectable(item, corner)) {
				 collect(item);
			 }
		 }
	}
	
	private List<CollectPoint> getCollectionPositions() {
		List<CollectPoint> corners = new ArrayList<CollectPoint>();
		corners.add(TOP_LEFT_CORNER);
		corners.add(TOP_RIGHT_CORNER);
		corners.add(BOTTOM_LEFT_CORNER);
		corners.add(BOTTOM_RIGHT_CORNER);
		return corners;
	}
	
	private boolean isCollectable(ItemModel item, CollectPoint corner) {
		ItemType type = item.getType();
		
		boolean validLevel = type.level == ItemType.MAX_LEVEL;
		boolean validColor = type.color == corner.color;
		
		return validLevel && validColor;
	}
	
	private void collect(ItemModel item) {
		item.collect();
		EventManager.dispatch(new CollectItemEvent(item.getType().color));
	}
}
