package com.mosquito.games.model;

import com.mosquito.games.app.event.PlayerInputEvent;
import com.mosquito.games.app.event.PlayerInputEvent.Direction;
import com.mosquito.games.app.event.PlayerInputListener;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.LevelData;
import com.mosquito.games.data.levels.Levels;
import com.mosquito.games.model.event.GameFinishedEvent;
import com.mosquito.games.model.event.GameFinishedEvent.GameResult;
import com.mosquito.games.model.event.GameFinishedListener;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.model.event.GameStartedListener;
import com.mosquito.games.model.event.MoveFinishedEvent;
import com.mosquito.games.model.event.MoveStartedEvent;
import com.mosquito.games.view.event.ViewBoardUpdatedEvent;
import com.mosquito.games.view.event.ViewBoardUpdatedListener;

public class GameStateModel implements PlayerInputListener, ViewBoardUpdatedListener, GameStartedListener, GameFinishedListener {
	LevelRulesModel levelRules = new LevelRulesModel();
	
	boolean inGame = false;
	boolean inMove = false;
	
	public GameStateModel() {
		EventManager.listen(PlayerInputEvent.class, this);
		EventManager.listen(ViewBoardUpdatedEvent.class, this);
		
		EventManager.listen(GameStartedEvent.class, this);
		EventManager.listen(GameFinishedEvent.class, this);
	}

	@Override
	public void input(Direction direction) {
		if (inGame && !inMove) {
			switch (direction) {
				case LEFT: EventManager.dispatch(new MoveStartedEvent(MoveStartedEvent.Direction.LEFT)); break;
				case RIGHT: EventManager.dispatch(new MoveStartedEvent(MoveStartedEvent.Direction.RIGHT)); break;
				case UP: EventManager.dispatch(new MoveStartedEvent(MoveStartedEvent.Direction.UP)); break;
				case DOWN: EventManager.dispatch(new MoveStartedEvent(MoveStartedEvent.Direction.DOWN)); break;
			}
			inMove = true;
		}
	}
	
	@Override
	public void boardUpdated(boolean areItemsIdle) {
		if (inMove && areItemsIdle) {
			inMove = false;
			EventManager.dispatch(new MoveFinishedEvent());
		}
	}

	@Override
	public void gameStarted(LevelData levelData) {
		levelRules.setLevel(levelData);
		inGame = true;
	}

	@Override
	public void gameFinished(GameResult gameResult) {
		inGame = false;
		if (gameResult == GameResult.SUCCESS) {
			Levels.incrementCurrentLevel();
		}
	}
}
