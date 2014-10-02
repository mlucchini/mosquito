package com.mosquito.games.view.actor.anim;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;

import com.badlogic.gdx.scenes.scene2d.Action;

public class BoardAnim {
	static final float FADE_IN_DURATION = 0.2f;
	static final float FADE_OUT_DURATION = 0.5f;
	
	public static Action getFadeIn() {
		return fadeIn(FADE_IN_DURATION);
	}
	
	public static Action getFadeOut() {
		return fadeOut(FADE_OUT_DURATION);
	}
}
