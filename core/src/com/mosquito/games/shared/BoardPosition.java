package com.mosquito.games.shared;

public class BoardPosition {
	public static final int WIDTH = 6;
	public static final int HEIGHT = 6;
	public static final int LAST_COLUMN = WIDTH - 1;
	public static final int LAST_ROW = HEIGHT - 1;

	public static final BoardPosition OUT = new BoardPosition(-1, -1);
	
	public int x;
	public int y;
	
	public BoardPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public BoardPosition(BoardPosition boardPosition) {
		this.x = boardPosition.x;
		this.y = boardPosition.y;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof BoardPosition))
			return false;

		BoardPosition other = (BoardPosition) object;
		return other.x == x && other.y == y;
	}
}
