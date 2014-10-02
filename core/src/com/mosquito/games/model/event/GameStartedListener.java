package com.mosquito.games.model.event;

import com.mosquito.games.data.levels.LevelData;

public interface GameStartedListener {
	public void gameStarted(LevelData levelData);
}
