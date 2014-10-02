package com.mosquito.games.model.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.ItemModel;
import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;

public class SpawnRandomPositionRandomColorStrategy implements SpawnStrategy {
	BoardModel boardModel;
	List<BoardPosition> exceptPositions;

	public SpawnRandomPositionRandomColorStrategy(BoardModel boardModel) {
		this.boardModel = boardModel;
		this.exceptPositions = new ArrayList<BoardPosition>();
	}
	
	public SpawnRandomPositionRandomColorStrategy(BoardModel boardModel, List<BoardPosition> exceptPositions) {
		this.boardModel = boardModel;
		this.exceptPositions = exceptPositions;
	}

	@Override
	public void execute() {
		Random random = new Random();
		
		BoardPosition randomBoardPosition = new BoardPosition(0, 0);
		ItemModel item = null;
		do {
			randomBoardPosition.x = random.nextInt(BoardPosition.WIDTH);
			randomBoardPosition.y = random.nextInt(BoardPosition.HEIGHT);
			item = boardModel.getItemAtPosition(randomBoardPosition.x, randomBoardPosition.y);
		} while (item != null || exceptPositions.contains(randomBoardPosition));
		
		List<Color> spawnableColors = Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
		Color randomColor = spawnableColors.get(random.nextInt(spawnableColors.size()));

		ItemModel spawnedItem = boardModel.addItem(randomBoardPosition, new ItemType(randomColor));
		spawnedItem.show();
	}
}
