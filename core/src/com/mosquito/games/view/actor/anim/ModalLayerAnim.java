package com.mosquito.games.view.actor.anim;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ModalLayerAnim {
	static final float OVERLAY_INTENSITY = 0.2f;
	static final float OVERLAY_IN_DURATION = 0.2f;
	static final float OVERLAY_OUT_DURATION = 0.2f;
	
	public static Action getOverlayIn() {
		return Actions.alpha(1f - OVERLAY_INTENSITY, OVERLAY_IN_DURATION, Interpolation.pow2);
	}
	
	public static Action getOverlayOut() {
		return Actions.alpha(1f, OVERLAY_OUT_DURATION, Interpolation.pow2);
	}
}
