package com.mosquito.games.view.actor.hud;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.Levels;
import com.mosquito.games.model.event.GameFinishedEvent;
import com.mosquito.games.model.event.GameFinishedListener;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.model.event.GameFinishedEvent.GameResult;
import com.mosquito.games.view.actor.hud.PlayButton.PlayType;
import com.mosquito.games.view.constants.GraphicConstants;

public class SwipeActor extends Group implements GameFinishedListener {
	private static final float SLIDE_DURATION = 0.5f;
	private static final float SLIDE_TRANSLATION = GraphicConstants.GAME_ZONE_WIDTH;
	
	PlayButton playButton;

	public SwipeActor() {
        EventManager.listen(GameFinishedEvent.class, this);
        
        setVisible(false);
	}

	@Override
	public void gameFinished(GameResult gameResult) {
		createPlayButton(gameResult);
		show();
	}

	private void createPlayButton(GameResult gameResult) {
		PlayType playType = gameResult == GameResult.FAILURE ? PlayType.RETRY : PlayType.NEXT;
		
		if (playButton != null) { 
			playButton.remove();
		}
		playButton = new PlayButton(playType);
		playButton.addListener(new ClickListener() {
        	@Override
            public void clicked(InputEvent event, float x, float y) {
        		EventManager.dispatch(new GameStartedEvent(Levels.getCurrentLevel()));
        		hide();
            }
        });
		addActor(playButton);
	}
	
	private void show() {
		setX(-SLIDE_TRANSLATION);
		addAction(Actions.sequence(
			Actions.show(),
			Actions.moveTo(0f, 0f, SLIDE_DURATION, Interpolation.pow5Out)));
	}
	
	private void hide() {
		addAction(Actions.sequence(
			Actions.moveBy(SLIDE_TRANSLATION, 0f, SLIDE_DURATION, Interpolation.pow5In),
			Actions.hide()));
	}
}
