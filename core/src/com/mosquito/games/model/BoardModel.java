package com.mosquito.games.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.data.levels.LevelData;
import com.mosquito.games.model.event.GameFinishedEvent;
import com.mosquito.games.model.event.GameFinishedListener;
import com.mosquito.games.model.event.GameStartedEvent;
import com.mosquito.games.model.event.GameStartedListener;
import com.mosquito.games.model.event.ItemSelectedEvent;
import com.mosquito.games.model.event.ItemSelectedListener;
import com.mosquito.games.model.event.MoveFinishedEvent;
import com.mosquito.games.model.event.MoveFinishedListener;
import com.mosquito.games.model.event.MoveStartedEvent;
import com.mosquito.games.model.event.MoveStartedListener;
import com.mosquito.games.model.event.GameFinishedEvent.GameResult;
import com.mosquito.games.model.event.MoveStartedEvent.Direction;
import com.mosquito.games.model.helper.BoardModelPositionHelper;
import com.mosquito.games.model.helper.BoardModelStrategyHelper;
import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;
import com.mosquito.games.view.actor.BoardActor;

public class BoardModel implements GameStartedListener, GameFinishedListener, MoveStartedListener, MoveFinishedListener, ItemSelectedListener {
	BoardModelStrategyHelper strategies;
	BoardActor boardActor;

	List<ItemModel> itemModels = new ArrayList<ItemModel>();
	
	public BoardModel(BoardActor boardActor) {
		this.strategies = new BoardModelStrategyHelper(this);
		this.boardActor = boardActor;

    	setEvents();
	}

	private void setEvents() {
		EventManager.listen(GameStartedEvent.class, this);
    	EventManager.listen(GameFinishedEvent.class, this);
    	EventManager.listen(MoveStartedEvent.class, this);
    	EventManager.listen(MoveFinishedEvent.class, this);
        EventManager.listen(ItemSelectedEvent.class, this);
	}
	
	public ItemModel getItemAtPosition(int x, int y) {
		return BoardModelPositionHelper.getItemAtPosition(itemModels, x, y);
	}
	
	public List<ItemModel> getItemsAtPosition(int x, int y) {
		return BoardModelPositionHelper.getItemsAtPosition(itemModels, x, y);
	}
	
	public ItemModel addItem(BoardPosition boardPosition, ItemType itemType) {
		ItemModel item = new ItemModel(boardPosition, itemType, itemType.color != Color.BLOCK, boardActor.getTileSize());
    	
		itemModels.add(item);
    	boardActor.addItem(item.getActor());

    	return item;
    }
	
	public void growInto(List<ItemModel> mergedItems, ItemType intoType) {
		ItemModel arbitraryItem = mergedItems.get(0);
		ItemModel grownItem = addItem(arbitraryItem.getPosition(), intoType);
		arbitraryItem.growInto(grownItem);
		
		for (ItemModel item : mergedItems) {
			item.remove();
		}
	}

	@Override
	public void gameStarted(LevelData levelData) {
		itemModels.clear();
		boardActor.removeItems();
		
		if (levelData.getInitialItems().isEmpty()) {
			strategies.spawn.execute();
		} else {
			for (Entry<BoardPosition, ItemType> initialItem : levelData.getInitialItems().entrySet()) {
				ItemModel item = addItem(initialItem.getKey(), initialItem.getValue());
				item.show();
			}
		}

		boardActor.show();
	}

	@Override
	public void gameFinished(GameResult gameResult) {
		boardActor.hide();
	}
	
    @Override
    public void moveStarted(Direction direction) {
    	strategies.shift.execute(direction);
    	strategies.grow.execute();
    	strategies.collect.execute();
    }
    
    @Override
    public void moveFinished() {
    	strategies.spawn.execute();
    }

	@Override
	public void itemSelected(ItemModel itemModel) {
		if (itemModel.getType().level == ItemType.SECOND_LEVEL) {
			handleFreeze(itemModel);
		} else if (itemModel.getType().color == Color.CHUCK) {
			handleExplode(itemModel);
		}
	}

	private void handleFreeze(ItemModel item) {
		item.freezeUnfreeze();
		for (ItemModel otherItem : itemModels) {
			if (otherItem != item && !otherItem.isMovable() && !(otherItem.getType().color == Color.CHUCK || otherItem.getType().color == Color.BLOCK)) {
				otherItem.freezeUnfreeze();
			}
		}
	}
	
	private void handleExplode(ItemModel itemModel) {
		strategies.select.execute(itemModel);
	}
}
