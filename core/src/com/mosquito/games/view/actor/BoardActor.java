package com.mosquito.games.view.actor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.view.actor.helper.BoardActorItemAdder;
import com.mosquito.games.view.actor.input.BoardActorGestureInputListener;
import com.mosquito.games.view.constants.GraphicConstants;
import com.mosquito.games.view.event.ViewBoardUpdatedEvent;

public class BoardActor extends Group {
	Vector2 tileSize;
	Group itemActors = new Group();

    public BoardActor(Vector2 parentSize) {
    	addActor(itemActors);

    	setTransform(parentSize);
    	setListeners();
    	setTileSize();    	

        hide();
    }

    private void setTransform(Vector2 parentSize) {
    	setBounds((parentSize.x - GraphicConstants.BOARD_WIDTH) / 2f, GraphicConstants.BOARD_MARGIN_BOTTOM,
    			GraphicConstants.BOARD_WIDTH, GraphicConstants.BOARD_WIDTH);
    }
    
    private void setListeners() {
    	addListener(new BoardActorGestureInputListener());
    }
    
    private void setTileSize() {
    	tileSize = new Vector2(getWidth() / BoardPosition.WIDTH, getHeight() / BoardPosition.HEIGHT);
    }
    
    public Vector2 getTileSize() {
    	return tileSize;
    }
    
    @Override
    public void act(float delta) {
    	super.act(delta);
    	checkItemsIdleness();
    }

	private void checkItemsIdleness() {
		boolean areItemsIdle = true;
    	for (Actor item : itemActors.getChildren()) {
    		if (item.getActions().size > 0) {
    			areItemsIdle = false;
    			break;
    		}
    	}
    	EventManager.dispatch(new ViewBoardUpdatedEvent(areItemsIdle));
	}

	public void addItem(ItemActor itemActor) {
		BoardActorItemAdder.addSortedByDepth(itemActors, itemActor);
    }
	
	public void removeItems() {
		for (Actor itemActor : itemActors.getChildren()) {
			((ItemActor)itemActor).dispose();
		}
		itemActors.clear();
	}

	public void hide() {
		setVisible(false);
	}
	
	public void show() {
		setVisible(true);
	}
}
