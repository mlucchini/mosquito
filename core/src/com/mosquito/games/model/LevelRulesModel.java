package com.mosquito.games.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.LevelData;
import com.mosquito.games.model.event.CollectGoalClearedEvent;
import com.mosquito.games.model.event.CollectGoalClearedListener;
import com.mosquito.games.model.event.GameFinishedEvent;
import com.mosquito.games.model.event.MovesUpdatedListener;
import com.mosquito.games.model.event.MovesUpdatedEvent;
import com.mosquito.games.model.event.GameFinishedEvent.GameResult;
import com.mosquito.games.shared.Color;

public class LevelRulesModel implements CollectGoalClearedListener, MovesUpdatedListener {
	boolean gameFinished;
	int movesAvailable;
	Map<Color, Integer> remainingGoals;

	public LevelRulesModel() {
		this.gameFinished = false;
		this.movesAvailable = 0;
		this.remainingGoals = new HashMap<Color, Integer>();
		
		EventManager.listen(CollectGoalClearedEvent.class, this);
		EventManager.listen(MovesUpdatedEvent.class, this);
	}

	public void setLevel(LevelData levelData) {
		this.gameFinished = false;
		this.movesAvailable = levelData.getNumberMoves();
		this.remainingGoals = new HashMap<Color, Integer>(levelData.getGoals());
	}

	@Override
	public void goalCleared(Color color) {
		if (remainingGoals.containsKey(color)) {
			remainingGoals.put(color, remainingGoals.get(color) - 1);
		}

		check();
	}
	
	@Override
	public void movesUpdated(int countDown) {
		movesAvailable = countDown;

		check();
	}
	
	private void check() {
		if (!gameFinished) {
			for (Entry<Color, Integer> remainingGoal : remainingGoals.entrySet()) {
				if (remainingGoal.getValue() > 0) {
					if (movesAvailable == 0) {
						gameFinished = true;
						EventManager.dispatch(new GameFinishedEvent(GameResult.FAILURE));
					}
					return;
				}
			}
			
			gameFinished = true;
			EventManager.dispatch(new GameFinishedEvent(GameResult.SUCCESS));
		}
	}
}
