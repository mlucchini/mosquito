package com.mosquito.games.shared;

public class ItemType {
	public static final int FIRST_LEVEL = 1;
	public static final int SECOND_LEVEL = 2;
	public static final int MAX_LEVEL = 3;

	public Color color;
	public int level;
	
	public ItemType(Color color, int level) {
		this.color = color;
		this.level = level;
	}
	
	public ItemType(Color color) {
		this.color = color;
		this.level = FIRST_LEVEL;
	}
	
	public ItemType(ItemType type) {
		this.color = type.color;
		this.level = type.level;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof ItemType))
			return false;

		ItemType other = (ItemType) object;
		return other.color == color;
	}
}
