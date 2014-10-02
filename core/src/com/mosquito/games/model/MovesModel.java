package com.mosquito.games.model;

import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.LevelData;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.model.event.GameStartedListener;
import com.mosquito.games.model.event.MoveFinishedEvent;
import com.mosquito.games.model.event.MoveFinishedListener;
import com.mosquito.games.model.event.MovesUpdatedEvent;
import com.mosquito.games.view.actor.hud.MovesActor;

public class MovesModel implements GameStartedListener, MoveFinishedListener {
	MovesActor movesActor;
	int countDown;

	public MovesModel(MovesActor movesActor) {
		this.movesActor = movesActor;

        EventManager.listen(GameStartedEvent.class, this);
        EventManager.listen(MoveFinishedEvent.class, this);
	}
	
	@Override
	public void gameStarted(LevelData levelData) {
		countDown = levelData.getNumberMoves();
		movesActor.initMoves(countDown);
		
		EventManager.dispatch(new MovesUpdatedEvent(countDown));
	}

	@Override
	public void moveFinished() {
		countDown--;
		movesActor.setMoves(countDown);
		
		EventManager.dispatch(new MovesUpdatedEvent(countDown));
	}
}
