package com.mosquito.games.app.module;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.Levels;
import com.mosquito.games.model.BoardModel;
import com.mosquito.games.model.CollectModel;
import com.mosquito.games.model.GameStateModel;
import com.mosquito.games.model.MovesModel;
import com.mosquito.games.model.SoundModel;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.view.actor.BackgroundActor;
import com.mosquito.games.view.actor.BoardActor;
import com.mosquito.games.view.actor.hud.HudActor;
import com.mosquito.games.view.actor.hud.ModalLayerActor;

public class GameModule implements Module {
	Stage stage;

	BackgroundActor backgroundActor;
	HudActor hudActor;
	ModalLayerActor modalLayerActor;
	BoardActor boardActor;

	public GameModule(Stage stage) {
		this.stage = stage;
		
		createActors();
		createModels();
	}
	
	private void createActors() {
		Vector2 stageSize = new Vector2(stage.getWidth(), stage.getHeight());

		backgroundActor = new BackgroundActor(stageSize);
		hudActor = new HudActor(stageSize);
		boardActor = new BoardActor(stageSize);
		modalLayerActor = new ModalLayerActor(stage);		
	}
	
	private void createModels() {
		new SoundModel();
		new GameStateModel();
		new BoardModel(boardActor);
		new MovesModel(hudActor.getMovesActor());
		new CollectModel(hudActor.getCollectActors());
	}

	@Override
	public void show() {
        stage.addActor(backgroundActor);
        stage.addActor(hudActor);
        stage.addActor(modalLayerActor);
        stage.addActor(boardActor);

        EventManager.dispatch(new GameStartedEvent(Levels.getCurrentLevel()));
	}

	@Override
	public void hide() {
		backgroundActor.remove();
		hudActor.remove();
		modalLayerActor.remove();
		boardActor.remove();
	}
	
	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {		
	}

	@Override
	public void dispose() {		
	}
}
