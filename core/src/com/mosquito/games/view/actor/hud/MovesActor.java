package com.mosquito.games.view.actor.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mosquito.games.app.system.util.FontFactory;
import com.mosquito.games.app.system.util.FontFactory.FontType;
import com.mosquito.games.view.actor.helper.ActorDrawer;
import com.mosquito.games.view.constants.GraphicConstants;

public class MovesActor extends Actor {
	static final Color YELLOWISH = new Color(193f/255f, 171f/255f, 96f/255f, 1f);
	static final int RED_ZONE_THRESHOLD = 5;

	int countDown;
	BitmapFont font;
	
	public MovesActor() {
		font = FontFactory.make(FontType.CAMBRIA, 0.7f);
		font.setColor(YELLOWISH);

		setBounds(GraphicConstants.MOVES_COUNTER.x, GraphicConstants.MOVES_COUNTER.y, font.getBounds("0").width, font.getBounds("0").height);
	}

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	super.draw(batch, parentAlpha);
    	ActorDrawer.draw(this, batch, parentAlpha, font, String.valueOf(countDown));
    }

	public void initMoves(int countDown) {
		this.countDown = countDown;
		font.setColor(YELLOWISH);
	}

	public void setMoves(int countDown) {
		this.countDown = countDown;
		if (countDown == RED_ZONE_THRESHOLD) {
			font.setColor(Color.RED);
		}
	}	
}
