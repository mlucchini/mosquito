package com.mosquito.games.model.strategy;

import java.util.ArrayList;
import java.util.List;

import com.mosquito.games.model.ItemModel;

public class SelectStrategyDispatcher {
	boolean active = true;
	List<SelectStrategy> strategies = new ArrayList<SelectStrategy>();

	public SelectStrategyDispatcher(SelectStrategy strategy) {
		strategies.add(strategy);
	}
	
	public SelectStrategyDispatcher(SelectStrategy strategy, SelectStrategy strategy2) {
		strategies.add(strategy);
		strategies.add(strategy2);
	}
	
	public SelectStrategyDispatcher(SelectStrategy... strategies) {
		for (SelectStrategy strategy : strategies) {
			this.strategies.add(strategy);
		}
	}

	public void execute(ItemModel itemModel) {
		if (active) {
			for (SelectStrategy strategy : strategies) {
				if (strategy.wantsToHandle(itemModel)) {
					strategy.execute(itemModel);
				}
			}
		}
	}

	public void deactivate() {
		active = false;
	}
	
	public void activate() {
		active = true;
	}
}
