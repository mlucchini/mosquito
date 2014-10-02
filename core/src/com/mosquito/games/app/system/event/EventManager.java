package com.mosquito.games.app.system.event;

public class EventManager {
	public final static Events events = new Events();
	
	public static <L> void listen(Class<? extends GameEvent<L>> evtClass, L listener) {
		events.listen(evtClass, listener);
	}
	
	public static <L> void mute(Class<? extends GameEvent<L>> evtClass, L listener) {
		events.mute(evtClass, listener);
	}
	
	public static <L> void dispatch(final GameEvent<L> evt) {
		events.dispatch(evt);
	}
}
