package com.mosquito.games.data.levels;

import java.util.HashMap;
import java.util.Map;

import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;

public abstract class LevelData {
	int numberMoves;
	Map<BoardPosition, ItemType> initialItems = new HashMap<BoardPosition, ItemType>();
	Map<Color, Integer> goals = new HashMap<Color, Integer>();

	public int getNumberMoves() {
		return numberMoves;
	}

	public Map<BoardPosition, ItemType> getInitialItems() {
		return initialItems;
	}
	
	public Map<Color, Integer> getGoals() {
		return goals;
	}
	
	protected void setMoves(int numberMoves) {
		this.numberMoves = numberMoves;
	}
	protected void m(int numberMoves) {
		setMoves(numberMoves);
	}

	protected void addInitialItem(BoardPosition boardPosition, ItemType type) {
		initialItems.put(new BoardPosition(boardPosition), new ItemType(type));
	}
	
	protected void addGoal(Color color, int amount) {
		goals.put(color, amount);
	}
}
