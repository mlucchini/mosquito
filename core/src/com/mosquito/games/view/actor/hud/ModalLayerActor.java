package com.mosquito.games.view.actor.hud;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.LevelData;
import com.mosquito.games.model.event.GameFinishedEvent;
import com.mosquito.games.model.event.GameFinishedListener;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.model.event.GameStartedListener;
import com.mosquito.games.model.event.GameFinishedEvent.GameResult;
import com.mosquito.games.view.actor.anim.ModalLayerAnim;

public class ModalLayerActor extends Group implements GameStartedListener, GameFinishedListener {
	Stage stage;

	public ModalLayerActor(Stage stage) {
		this.stage = stage;

		EventManager.listen(GameStartedEvent.class, this);
		EventManager.listen(GameFinishedEvent.class, this);
	}
	
	@Override
	public void gameStarted(LevelData levelData) {
		removeOverlay();
	}

	@Override
	public void gameFinished(GameResult gameResult) {
		addOverlay();
	}

    public void addOverlay() {
    	stage.addAction(ModalLayerAnim.getOverlayIn());
    }
    
    public void removeOverlay() {
    	stage.addAction(ModalLayerAnim.getOverlayOut());
    }
}
