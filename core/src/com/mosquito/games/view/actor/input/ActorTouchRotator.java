package com.mosquito.games.view.actor.input;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class ActorTouchRotator extends ActorGestureListener {
	static final float DEFAULT_FRICTION_FRACTOR = 5f;
	static final float DEFAULT_DECELERATION_DURATION = 1.5f;

	Actor actor;
	RotateByAction rotationAction;
	
	float frictionFactor = DEFAULT_FRICTION_FRACTOR;
	float decelerationDuration = DEFAULT_DECELERATION_DURATION;

	float angle = 0f;
	boolean clockwise = false;

	public ActorTouchRotator(Actor actor) {
		this.actor = actor;
	}
	
	public void setFrictionFactor(float frictionFactor) {
		this.frictionFactor = frictionFactor;
	}
	
	public void setDecelerationDuration(float decelerationDuration) {
		this.decelerationDuration = decelerationDuration;
	}
	
	@Override
	public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
		angle = getTouchAngle(x, y);
		if (rotationAction != null) {
			actor.removeAction(rotationAction);
		}
	}

	@Override
	public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
		float angle = getTouchAngle(x, y) - this.angle;
		clockwise = angle > 0;
		actor.rotateBy(angle);
	}

	@Override
	public void fling(InputEvent event, float velocityX, float velocityY, int button) {
		float amount = Math.abs(velocityX + velocityY) / frictionFactor * (clockwise ? 1f : -1f);
		rotationAction = Actions.rotateBy(amount, decelerationDuration, Interpolation.pow3Out);
		actor.addAction(rotationAction);
	}

	private float getTouchAngle(float x, float y) {
		return (float) Math.toDegrees(Math.atan2(y - actor.getOriginY(), x - actor.getOriginX()));
	}
}
