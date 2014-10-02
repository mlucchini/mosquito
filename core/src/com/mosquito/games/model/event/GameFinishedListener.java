package com.mosquito.games.model.event;

import com.mosquito.games.model.event.GameFinishedEvent.GameResult;

public interface GameFinishedListener {
	public void gameFinished(GameResult gameResult);
}
