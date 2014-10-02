package com.mosquito.games.model;

import com.badlogic.gdx.math.Vector2;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.model.event.ItemSelectedEvent;
import com.mosquito.games.model.event.MoveStartedEvent.Direction;
import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;
import com.mosquito.games.view.actor.ItemActor;
import com.mosquito.games.view.event.ViewItemTouchedEvent;
import com.mosquito.games.view.event.ViewItemTouchedListener;

public class ItemModel implements ViewItemTouchedListener {
	BoardPosition position;
	ItemType type;
	boolean movable;

	ItemActor itemActor;
	
	public ItemModel(BoardPosition position, ItemType type, boolean movable, Vector2 tileSize) {
		this.position = new BoardPosition(position);
		this.type = new ItemType(type);
		this.movable = movable;
		this.itemActor = new ItemActor(position, type, tileSize);
		
		setEvents();
	}
	
	private void setEvents() {
		EventManager.listen(ViewItemTouchedEvent.class, this);
	}

	public BoardPosition getPosition() {
		return position;
	}
	
	public ItemType getType() {
		return type;
	}
	
	public ItemActor getActor() {
		return itemActor;
	}
	
    public boolean isMergeable() {
    	return type.color != Color.CHUCK || !movable;
    }

    public boolean isMovable() {
    	return movable;
    }
    
    public void show() {
    	itemActor.show(type);
    }
    
    public void remove() {
    	position = BoardPosition.OUT;
    	itemActor.hide();
    }
    
    public void shiftTo(BoardPosition boardPosition, Direction direction) {
    	boolean moves = !position.equals(boardPosition);
    	position = boardPosition;

		itemActor.shiftTo(boardPosition, direction, moves);
	}
    
    public void collect() {
		itemActor.collect(type.color, position.y == 0);
		remove();
    }

	public void explode() {
		itemActor.explode();
		remove();
	}
	
	public void freezeUnfreeze() {
		if (movable) {
			itemActor.freeze();
		} else {
			itemActor.unfreeze();
		}
		movable = !movable;
	}
	
	public void growInto(ItemModel grownItem) {
		itemActor.growInto(grownItem.type, grownItem.getActor());
	}

	@Override
	public void itemTouched(ItemActor itemActor) {
		if (this.itemActor == itemActor) {
			EventManager.dispatch(new ItemSelectedEvent(this));
		}
	}

    @Override
    public String toString() {
    	return new StringBuffer()
    		.append("Item: ")
    		.append("x: ").append(position.x).append(", ")
    		.append("y: ").append(position.y).append(", ")
    		.append("color: ").append(type.color).append(", ")
    		.append("level: ").append(type.level)
    		.toString();
    }
}
