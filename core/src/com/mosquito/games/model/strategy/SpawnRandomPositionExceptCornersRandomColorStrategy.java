package com.mosquito.games.model.strategy;

import java.util.Arrays;
import java.util.List;

import com.mosquito.games.model.BoardModel;
import com.mosquito.games.shared.BoardPosition;

public class SpawnRandomPositionExceptCornersRandomColorStrategy implements SpawnStrategy {
	static List<BoardPosition> corners = Arrays.asList(
			new BoardPosition(0, 0),
			new BoardPosition(0, BoardPosition.LAST_ROW),
			new BoardPosition(BoardPosition.LAST_COLUMN, 0),
			new BoardPosition(BoardPosition.LAST_COLUMN, BoardPosition.LAST_ROW));

	SpawnRandomPositionRandomColorStrategy spawnStrategy;

	public SpawnRandomPositionExceptCornersRandomColorStrategy(BoardModel boardModel) {
		spawnStrategy = new SpawnRandomPositionRandomColorStrategy(boardModel, corners);
	}
	
	public void execute() {
		spawnStrategy.execute();
	}
}
