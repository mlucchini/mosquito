package com.mosquito.games.data.levels;

import java.util.ArrayList;
import java.util.List;

import com.mosquito.games.shared.BoardPosition;
import com.mosquito.games.shared.Color;
import com.mosquito.games.shared.ItemType;

@SuppressWarnings("unused")
public class LevelDataList {
	static private BoardPosition P00 = new BoardPosition(0, 0);
	static private BoardPosition P01 = new BoardPosition(0, 1);
	static private BoardPosition P02 = new BoardPosition(0, 2);
	static private BoardPosition P03 = new BoardPosition(0, 3);
	static private BoardPosition P04 = new BoardPosition(0, 4);
	static private BoardPosition P05 = new BoardPosition(0, 5);
	static private BoardPosition P10 = new BoardPosition(1, 0);
	static private BoardPosition P11 = new BoardPosition(1, 1);
	static private BoardPosition P12 = new BoardPosition(1, 2);
	static private BoardPosition P13 = new BoardPosition(1, 3);
	static private BoardPosition P14 = new BoardPosition(1, 4);
	static private BoardPosition P15 = new BoardPosition(1, 5);
	static private BoardPosition P20 = new BoardPosition(2, 0);
	static private BoardPosition P21 = new BoardPosition(2, 1);
	static private BoardPosition P22 = new BoardPosition(2, 2);
	static private BoardPosition P23 = new BoardPosition(2, 3);
	static private BoardPosition P24 = new BoardPosition(2, 4);
	static private BoardPosition P25 = new BoardPosition(2, 5);
	static private BoardPosition P30 = new BoardPosition(3, 0);
	static private BoardPosition P31 = new BoardPosition(3, 1);
	static private BoardPosition P32 = new BoardPosition(3, 2);
	static private BoardPosition P33 = new BoardPosition(3, 3);
	static private BoardPosition P34 = new BoardPosition(3, 4);
	static private BoardPosition P35 = new BoardPosition(3, 5);
	static private BoardPosition P40 = new BoardPosition(4, 0);
	static private BoardPosition P41 = new BoardPosition(4, 1);
	static private BoardPosition P42 = new BoardPosition(4, 2);
	static private BoardPosition P43 = new BoardPosition(4, 3);
	static private BoardPosition P44 = new BoardPosition(4, 4);
	static private BoardPosition P45 = new BoardPosition(4, 5);
	static private BoardPosition P50 = new BoardPosition(5, 0);
	static private BoardPosition P51 = new BoardPosition(5, 1);
	static private BoardPosition P52 = new BoardPosition(5, 2);
	static private BoardPosition P53 = new BoardPosition(5, 3);
	static private BoardPosition P54 = new BoardPosition(5, 4);
	static private BoardPosition P55 = new BoardPosition(5, 5);
	
	static private ItemType B = new ItemType(Color.BLOCK, 0);
	static private ItemType R1 = new ItemType(Color.RED, 1);
	static private ItemType R2 = new ItemType(Color.RED, 2);
	static private ItemType R3 = new ItemType(Color.RED, 3);
	static private ItemType B1 = new ItemType(Color.BLUE, 1);
	static private ItemType B2 = new ItemType(Color.BLUE, 2);
	static private ItemType B3 = new ItemType(Color.BLUE, 3);
	static private ItemType Y1 = new ItemType(Color.YELLOW, 1);
	static private ItemType Y2 = new ItemType(Color.YELLOW, 2);
	static private ItemType Y3 = new ItemType(Color.YELLOW, 3);
	static private ItemType G1 = new ItemType(Color.GREEN, 1);
	static private ItemType G2 = new ItemType(Color.GREEN, 2);
	static private ItemType G3 = new ItemType(Color.GREEN, 3);

	static List<LevelData> all = new ArrayList<LevelData>();
	static {
		/* 0-5 */
		all.add(new LevelData() {{
			m(1);
			addInitialItem(P00, R3);
			addGoal(Color.RED, 1);
		}});
		all.add(new LevelData() {{
			m(2);
			addInitialItem(P01, R2);
			addInitialItem(P51, R1);
			addGoal(Color.RED, 1);
		}});
		all.add(new LevelData() {{
			m(2);
			addInitialItem(P00, R1);
			addInitialItem(P20, R1);
			addInitialItem(P40, R1);
			addGoal(Color.RED, 1);
		}});
		all.add(new LevelData() {{
			m(1);
			addInitialItem(P54, R3);
			addInitialItem(P03, Y3);
			addGoal(Color.RED, 1);
			addGoal(Color.YELLOW, 1);
		}});
		all.add(new LevelData() {{
			m(10);
			addInitialItem(P00, R1);
			addInitialItem(P05, R1);
			addInitialItem(P55, R1);
			addGoal(Color.RED, 1);
		}});

		/* 5-10 */
		all.add(new LevelData() {{
			m(8);
			addInitialItem(P51, B3);
			addInitialItem(P35, G1);
			addInitialItem(P31, B);		
			addInitialItem(P54, B);
			addGoal(Color.BLUE, 1);
		}});
		all.add(new LevelData() {{
			m(12);
			addInitialItem(P10, B);
			addInitialItem(P11, B);
			addInitialItem(P13, B);
			addInitialItem(P14, B);
			addInitialItem(P15, B);
			addInitialItem(P50, B1);
			addInitialItem(P51, R1);
			addInitialItem(P23, G3);
			addGoal(Color.GREEN, 1);
		}});
		all.add(new LevelData() {{
			m(15);
			addInitialItem(P22, Y2);
			addInitialItem(P33, B2);
			addGoal(Color.YELLOW, 1);
			addGoal(Color.BLUE, 1);
		}});
		all.add(new LevelData() {{
			m(25);
			addInitialItem(P00, B);
			addInitialItem(P55, B);
			addInitialItem(P22, B);
			addInitialItem(P32, R1);
			addInitialItem(P33, B);
			addInitialItem(P23, G1);
			addGoal(Color.RED, 1);
			addGoal(Color.GREEN, 1);
		}});
		all.add(new LevelData() {{
			m(40);
			addGoal(Color.RED, 1);
			addGoal(Color.BLUE, 1);
			addGoal(Color.GREEN, 1);
			addGoal(Color.YELLOW, 1);
		}});
		
		/* 5-15 */
	}
}
