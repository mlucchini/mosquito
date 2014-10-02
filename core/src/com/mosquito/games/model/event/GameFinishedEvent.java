package com.mosquito.games.model.event;

import com.mosquito.games.app.system.event.GameEvent;

public class GameFinishedEvent implements GameEvent<GameFinishedListener> {
	public enum GameResult {
		SUCCESS, FAILURE
	}
	
	GameResult gameResult;
	
	public GameFinishedEvent(GameResult gameResult) {
		this.gameResult = gameResult;
	}

	@Override
	public void notify(GameFinishedListener listener) {
		listener.gameFinished(gameResult);
	}
}
