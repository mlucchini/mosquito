package com.mosquito.games.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.LevelData;
import com.mosquito.games.model.event.GameFinishedEvent;
import com.mosquito.games.model.event.GameFinishedListener;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.model.event.GameStartedListener;
import com.mosquito.games.model.event.MoveStartedEvent;
import com.mosquito.games.model.event.MoveStartedListener;
import com.mosquito.games.model.event.GameFinishedEvent.GameResult;
import com.mosquito.games.model.event.MoveStartedEvent.Direction;
import com.mosquito.games.shared.Color;
import com.mosquito.games.view.event.ViewCollectStartedEvent;
import com.mosquito.games.view.event.ViewCollectStartedListener;
import com.mosquito.games.view.event.ViewItemGrownEvent;
import com.mosquito.games.view.event.ViewItemGrownListener;

public class SoundModel extends Actor implements GameStartedListener, GameFinishedListener, MoveStartedListener, ViewItemGrownListener, ViewCollectStartedListener {
	Music background;
	Sound fling;
	Sound grow;
	Sound collection;
	Sound success;
	Sound failure;

	public SoundModel() {
		background = Gdx.audio.newMusic(Gdx.files.internal("data/sound/background.wav"));
		background.setLooping(true);
        fling = Gdx.audio.newSound(Gdx.files.internal("data/sound/fling.mp3"));
        grow = Gdx.audio.newSound(Gdx.files.internal("data/sound/grow.ogg"));
        collection = Gdx.audio.newSound(Gdx.files.internal("data/sound/collection.wav"));
        success = Gdx.audio.newSound(Gdx.files.internal("data/sound/success.wav"));
        failure = Gdx.audio.newSound(Gdx.files.internal("data/sound/failure.wav"));

		EventManager.listen(GameStartedEvent.class, this);
		EventManager.listen(GameFinishedEvent.class, this);
		EventManager.listen(MoveStartedEvent.class, this);
		EventManager.listen(ViewItemGrownEvent.class, this);
		EventManager.listen(ViewCollectStartedEvent.class, this);
	}

	@Override
	public void gameStarted(LevelData levelData) {
		if (!background.isPlaying()) {
			background.play();
		}
	}
	
	@Override
	public void gameFinished(GameResult gameResult) {
		switch (gameResult) {
			case SUCCESS:
				success.play();
				break;
			case FAILURE:
				background.stop();
				failure.play();
				break;
		}
	}

	@Override
	public void moveStarted(Direction direction) {
        fling.play(0.3f);
	}
	
	@Override
	public void itemGrown() {
        grow.play(0.8f);
	}

	@Override
	public void collectStarted(Color collectedColor) {
		collection.play();
	}
}
