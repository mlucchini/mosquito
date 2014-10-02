package com.mosquito.games.app.event;

import com.mosquito.games.app.event.PlayerInputEvent.Direction;


public interface PlayerInputListener {
	public void input(Direction direction);
}
