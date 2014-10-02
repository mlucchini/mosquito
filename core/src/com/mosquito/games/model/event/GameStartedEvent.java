package com.mosquito.games.model.event;

import com.mosquito.games.app.system.event.GameEvent;
import com.mosquito.games.data.levels.LevelData;

public class GameStartedEvent implements GameEvent<GameStartedListener> {
	LevelData levelData;

	public GameStartedEvent(LevelData levelData) {
		this.levelData = levelData;
	}

	@Override
	public void notify(GameStartedListener listener) {
		listener.gameStarted(levelData);
	}
}
