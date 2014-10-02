package com.mosquito.games.view.actor.hud;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mosquito.games.app.system.util.SkinFactory;
import com.mosquito.games.app.system.util.SkinFactory.SkinType;
import com.mosquito.games.view.constants.GraphicConstants;

public class PlayButton extends TextButton {
	public enum PlayType {
		RETRY, NEXT
	}

	static final String RETRY_PLAY_TEXT = "Retry";
	static final String NEXT_PLAY_TEXT = "Next!";

	public PlayButton(PlayType playType) {
		super(getButtonText(playType), SkinFactory.make(SkinType.DEFAULT));
		setTransform();
	}

	private void setTransform() {
		setBounds(GraphicConstants.PLAY_BUTTON.x - getWidth() / 2f, GraphicConstants.PLAY_BUTTON.y - getHeight() / 2f, getWidth(), getHeight());
	}

	private static String getButtonText(PlayType playType) {
		switch (playType) {
			case RETRY: return RETRY_PLAY_TEXT;
			case NEXT: return NEXT_PLAY_TEXT;
			default: return NEXT_PLAY_TEXT;
		}
	}
}
