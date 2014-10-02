package com.mosquito.games.view.actor.hud;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mosquito.games.shared.Color;
import com.mosquito.games.view.constants.GraphicConstants;

public class HudActor extends Group {
	MovesActor movesActor;
	LevelInformationActor levelInformationActor;
	BoosterMenuActor boosterMenuActor;
	SwipeActor swipeActor;
	List<CollectActor> collectActors = new ArrayList<CollectActor>();

	public HudActor(Vector2 parentSize) {
		setPosition((parentSize.x - GraphicConstants.GAME_ZONE_WIDTH) / 2f, 0f);
        setup(parentSize);
	}

	private void setup(Vector2 parentSize) {
		movesActor = new MovesActor();
		levelInformationActor = new LevelInformationActor();
		boosterMenuActor = new BoosterMenuActor();
		swipeActor = new SwipeActor();
		
		collectActors.add(new CollectActor(Color.BLUE));
		collectActors.add(new CollectActor(Color.YELLOW));
		collectActors.add(new CollectActor(Color.GREEN));
		collectActors.add(new CollectActor(Color.RED));
        
		for (Actor actor : collectActors) {
			addActor(actor);
		}
        addActor(movesActor);
        addActor(levelInformationActor);
        addActor(boosterMenuActor);
        addActor(swipeActor);
	}
	
	public MovesActor getMovesActor() {
		return movesActor;
	}
	
	public LevelInformationActor getLevelInformationActor() {
		return levelInformationActor;
	}
	
	public BoosterMenuActor getBoosterMenuActor() {
		return boosterMenuActor;
	}
	
	public List<CollectActor> getCollectActors() {
		return collectActors;
	}
}
