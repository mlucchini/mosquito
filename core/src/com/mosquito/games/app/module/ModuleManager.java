package com.mosquito.games.app.module;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mosquito.games.app.event.AppStartedEvent;
import com.mosquito.games.app.event.AppStartedListener;
import com.mosquito.games.app.module.event.ModuleFinishedEvent;
import com.mosquito.games.app.module.event.ModuleFinishedListener;
import com.mosquito.games.app.module.event.ModuleStartedEvent;
import com.mosquito.games.app.system.event.EventManager;

public class ModuleManager implements AppStartedListener, ModuleFinishedListener {
	Game game;
	Stage stage;

    LoadingModule loadingModule;
    GameModule gameModule;

	public ModuleManager(Game game, Stage stage) {
		this.game = game;
		this.stage = stage;
		
		loadingModule = new LoadingModule(stage);
        gameModule = new GameModule(stage);

		EventManager.listen(AppStartedEvent.class, this);
		EventManager.listen(ModuleFinishedEvent.class, this);
	}

	@Override
	public void appStarted() {
		game.setScreen(loadingModule);
		EventManager.dispatch(new ModuleStartedEvent(loadingModule));
	}

	@Override
	public void moduleFinished() {
		// The flow will need to be managed properly when we have more modules
		game.setScreen(gameModule);
		EventManager.dispatch(new ModuleStartedEvent(gameModule));
	}
}
