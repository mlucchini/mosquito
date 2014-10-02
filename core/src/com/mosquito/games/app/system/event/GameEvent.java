package com.mosquito.games.app.system.event;

public interface GameEvent<L> {
	public void notify(final L listener);
}
