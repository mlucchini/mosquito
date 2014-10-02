package com.mosquito.games.app.system.event;

import java.util.ArrayList;
import java.util.HashMap;

public final class Events {
	@SuppressWarnings("rawtypes")
	private final HashMap<Class, ArrayList> map = new HashMap<Class, ArrayList>(10);

	public <L> void listen(Class<? extends GameEvent<L>> evtClass, L listener) {
		final ArrayList<L> listeners = listenersOf(evtClass);
		synchronized (listeners) {
			if (!listeners.contains(listener)) {
				listeners.add(listener);
			}
		}
	}

	public <L> void mute(Class<? extends GameEvent<L>> evtClass, L listener) {
		final ArrayList<L> listeners = listenersOf(evtClass);
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	public <L> void dispatch(final GameEvent<L> evt) {
		@SuppressWarnings("unchecked")
		Class<GameEvent<L>> evtClass = (Class<GameEvent<L>>) evt.getClass();

		for (L listener : listenersOf(evtClass)) {
			evt.notify(listener);
		}
	}

	private <L> ArrayList<L> listenersOf(Class<? extends GameEvent<L>> evtClass) {
		synchronized (map) {
			@SuppressWarnings("unchecked")
			final ArrayList<L> existing = map.get(evtClass);
			if (existing != null) {
				return existing;
			}

			final ArrayList<L> emptyList = new ArrayList<L>(5);
			map.put(evtClass, emptyList);
			return emptyList;
		}
	}
}
