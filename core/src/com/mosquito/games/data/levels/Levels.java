package com.mosquito.games.data.levels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Levels {
	static Levels levels = new Levels();
	static {
		addAll(LevelDataList.all);
		levels.currentLevel = 1;
	}

	Map<Integer, LevelData> map = new HashMap<Integer, LevelData>();
	int currentLevel;

	public static LevelData getLevel(int levelNumber) {
		return levels.map.get(levelNumber - 1);
	}
	
	public static LevelData getCurrentLevel() {
		return levels.map.get(levels.currentLevel - 1);
	}
	
	public static int getCurrentLevelNumber() {
		return levels.currentLevel;
	}
	
	public static int getTotalNumberOfLevels() {
		return levels.map.size();
	}
	
	public static void incrementCurrentLevel() {
		int nextLevel = (levels.currentLevel % levels.map.size()) + 1;
		levels.currentLevel = nextLevel;
	}
	
	public static void addAll(List<LevelData> levelsData) {
		for (LevelData levelData : levelsData) {
			levels.map.put(levels.map.size(), levelData);
		}
	}
}
