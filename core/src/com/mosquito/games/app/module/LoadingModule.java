package com.mosquito.games.app.module;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mosquito.games.view.actor.LoadingActor;

public class LoadingModule implements Module {
	Stage stage;
	LoadingActor loadingActor;

	public LoadingModule(Stage stage) {
		this.stage = stage;
		this.loadingActor = new LoadingActor(new Vector2(stage.getWidth(), stage.getHeight()));
	}

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		stage.addActor(loadingActor);		
	}

	@Override
	public void hide() {
		loadingActor.remove();
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
