package com.mosquito.games.model.helper;

import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.strategy.CollectCornersStrategy;
import com.mosquito.games.model.strategy.CollectStrategy;
import com.mosquito.games.model.strategy.GrowStrategy;
import com.mosquito.games.model.strategy.GrowToNextLevelStrategy;
import com.mosquito.games.model.strategy.RemoveNearbyItemsSelectStrategy;
import com.mosquito.games.model.strategy.SelectStrategyDispatcher;
import com.mosquito.games.model.strategy.ShiftStrategy;
import com.mosquito.games.model.strategy.ShiftToSameColorStrategy;
import com.mosquito.games.model.strategy.SpawnRandomPositionExceptCornersRandomColorStrategy;
import com.mosquito.games.model.strategy.SpawnStrategy;

public class BoardModelStrategyHelper {
	public ShiftStrategy shift;
	public GrowStrategy grow;
	public SpawnStrategy spawn;
	public CollectStrategy collect;
	public SelectStrategyDispatcher select;

	public BoardModelStrategyHelper(BoardModel boardModel) {
    	shift = new ShiftToSameColorStrategy(boardModel);
    	grow = new GrowToNextLevelStrategy(boardModel);
    	spawn = new SpawnRandomPositionExceptCornersRandomColorStrategy(boardModel);
    	collect = new CollectCornersStrategy(boardModel);
    	select = new SelectStrategyDispatcher(
    			new RemoveNearbyItemsSelectStrategy(boardModel));
	}
}
