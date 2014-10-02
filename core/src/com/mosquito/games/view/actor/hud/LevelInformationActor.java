package com.mosquito.games.view.actor.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.app.system.util.FontFactory;
import com.mosquito.games.app.system.util.FontFactory.FontType;
import com.mosquito.games.data.levels.LevelData;
import com.mosquito.games.data.levels.Levels;
import com.mosquito.games.model.event.GameFinishedEvent;
import com.mosquito.games.model.event.GameFinishedListener;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.model.event.GameStartedListener;
import com.mosquito.games.model.event.GameFinishedEvent.GameResult;
import com.mosquito.games.view.constants.GraphicConstants;

public class LevelInformationActor extends Actor implements GameStartedListener, GameFinishedListener {
	static final float SHOW_HIDE_DELTA_Y = 300f;
	static final float SHOW_HIDE_DURATION = 0.5f;

	String levelText = "";
	BitmapFont font;
	
	public LevelInformationActor() {
		font = FontFactory.make(FontType.CAMBRIA, 0.8f);
		font.setColor(GraphicConstants.FONT_COLOR_YELLOWISH);
		
		setBounds(GraphicConstants.LEVEL_INFORMATION.x, GraphicConstants.LEVEL_INFORMATION.y + SHOW_HIDE_DELTA_Y,
				font.getBounds("0").width, font.getBounds("0").height);

        EventManager.listen(GameStartedEvent.class, this);
        EventManager.listen(GameFinishedEvent.class, this);
        
        setVisible(false);
	}

    @Override
    public void draw(Batch batch, float alpha) {
    	super.draw(batch, alpha);
    	font.draw(batch, levelText, getX(), getY());
    }

	@Override
	public void gameStarted(LevelData levelData) {
		levelText = "Level " + Levels.getCurrentLevelNumber() + "/" + Levels.getTotalNumberOfLevels();
		show();
	}

	@Override
	public void gameFinished(GameResult gameResult) {
		hide();
	}
	
	private void show() {
		setVisible(true);
		addAction(Actions.moveBy(0f, -SHOW_HIDE_DELTA_Y, SHOW_HIDE_DURATION, Interpolation.bounceIn));
	}
	
	private void hide() {
		addAction(Actions.moveBy(0f, SHOW_HIDE_DELTA_Y, SHOW_HIDE_DURATION, Interpolation.bounceOut));
	}
}
