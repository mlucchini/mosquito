package com.mosquito.games.model.strategy;

import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.ItemModel;
import com.mosquito.games.model.event.MoveStartedEvent.Direction;
import com.mosquito.games.shared.BoardPosition;

public class ShiftToSameColorStrategy implements ShiftStrategy {
	BoardModel boardModel;

	public ShiftToSameColorStrategy(BoardModel boardModel) {
		this.boardModel = boardModel;
	}

	@Override
	public void execute(Direction direction) {
		if (direction == Direction.LEFT || direction == Direction.UP) {
			for (int i = 0; i < BoardPosition.WIDTH; i++) {
				for (int j = 0; j < BoardPosition.HEIGHT; j++) {
					moveItem(i, j, direction);
				}
			}
		} else if (direction == Direction.RIGHT || direction == Direction.DOWN) {
			for (int i = BoardPosition.LAST_COLUMN; i >= 0; i--) {
				for (int j = BoardPosition.LAST_ROW; j >= 0; j--) {
					moveItem(i, j, direction);
				}
			}
		}
	}

	private void moveItem(int i, int j, Direction direction) {
		ItemModel item = boardModel.getItemAtPosition(i, j);
		
		if (item != null && item.isMovable()) {
			BoardPosition itemPosition = item.getPosition();
			BoardPosition nextPosition = getNextPosition(item, direction);
			
			item.shiftTo(item.isMovable() ? nextPosition : itemPosition, direction);
		}
	}

	private BoardPosition getNextPosition(ItemModel item, Direction direction) {
		int x = item.getPosition().x;
		int y = item.getPosition().y;
		
		ItemModel nextItem = null;
		BoardPosition nextBoardPosition = null;
		if (direction == Direction.LEFT) {
			for (int i = x - 1; i >= 0 && nextItem == null; i--) {
				nextItem = boardModel.getItemAtPosition(i, y);
				if (nextItem != null) {
					if (!item.isMergeable() || !nextItem.getType().equals(item.getType())) {
						nextBoardPosition = new BoardPosition(i + 1, y);
					}
				}
			}
		} else if (direction == Direction.RIGHT) {
			for (int i = x + 1; i < BoardPosition.WIDTH && nextItem == null; i++) {
				nextItem = boardModel.getItemAtPosition(i, y);
				if (nextItem != null) {
					if (!item.isMergeable() || !nextItem.getType().equals(item.getType())) {
						nextBoardPosition = new BoardPosition(i - 1, y);
					}
				}
			}
		} else if (direction == Direction.DOWN) {
			for (int j = y + 1; j < BoardPosition.HEIGHT && nextItem == null; j++) {
				nextItem = boardModel.getItemAtPosition(x, j);
				if (nextItem != null) {
					if (!item.isMergeable() || !nextItem.getType().equals(item.getType())) {
						nextBoardPosition = new BoardPosition(x, j - 1);
					}
				}
			}
		} else if (direction == Direction.UP) {
			for (int j = y - 1; j >= 0 && nextItem == null; j--) {
				nextItem = boardModel.getItemAtPosition(x, j);
				if (nextItem != null) {
					if (!item.isMergeable() || !nextItem.getType().equals(item.getType())) {
						nextBoardPosition = new BoardPosition(x, j + 1);
					}
				}
			}
		}
		
		if (nextBoardPosition != null) {
			return nextBoardPosition;
		} else if (nextItem != null) {
			return nextItem.getPosition();
		} else {
			int nextX = direction == Direction.LEFT ? 0 : direction == Direction.RIGHT ? BoardPosition.LAST_COLUMN : x;
			int nextY = direction == Direction.UP ? 0 : direction == Direction.DOWN ? BoardPosition.LAST_ROW : y;
			return new BoardPosition(nextX, nextY);
		}
	}
}
