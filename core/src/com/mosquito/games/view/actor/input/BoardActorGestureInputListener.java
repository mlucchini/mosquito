package com.mosquito.games.view.actor.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.mosquito.games.app.event.PlayerInputEvent;
import com.mosquito.games.app.event.PlayerInputEvent.Direction;
import com.mosquito.games.app.system.event.EventManager;

public class BoardActorGestureInputListener extends ActorGestureListener {
	@Override
	public void fling(InputEvent event, float velocityX, float velocityY, int button) {
		Direction direction;
		if (Math.abs(velocityX) > Math.abs(velocityY)) {
			direction = velocityX > 0 ? Direction.RIGHT : Direction.LEFT;
		} else {
			direction = velocityY < 0 ? Direction.DOWN : Direction.UP;
		}
		EventManager.dispatch(new PlayerInputEvent(direction));
	}
}
