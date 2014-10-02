package com.mosquito.games.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.LevelData;
import com.mosquito.games.model.event.CollectGoalClearedEvent;
import com.mosquito.games.model.event.CollectItemEvent;
import com.mosquito.games.model.event.CollectItemListener;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.model.event.GameStartedListener;
import com.mosquito.games.shared.Color;
import com.mosquito.games.view.actor.hud.CollectActor;

public class CollectModel implements GameStartedListener, CollectItemListener {
	Map<Color, Integer> remaining = new HashMap<Color, Integer>();
	List<CollectActor> collectActors;

	public CollectModel(List<CollectActor> collectActors) {
		this.collectActors = collectActors;

		EventManager.listen(GameStartedEvent.class, this);
		EventManager.listen(CollectItemEvent.class, this);
	}
	
	@Override
	public void gameStarted(LevelData levelData) {
		remaining = new HashMap<Color, Integer>(levelData.getGoals());
		for (CollectActor collectActor : collectActors) {
			Integer colorRemaining = remaining.get(collectActor.getCollectColor());
			if (colorRemaining != null) {
				collectActor.SetRemaining(colorRemaining.intValue());
			} else {
				collectActor.SetRemaining(0);
			}
		}
	}

	@Override
	public void itemCollected(Color color) {
		if (remaining.get(color).intValue() > 0) {
			remaining.put(color, remaining.get(color) - 1);
			if (remaining.get(color) == 0) {
				EventManager.dispatch(new CollectGoalClearedEvent(color));
			}
		}
	}
}
