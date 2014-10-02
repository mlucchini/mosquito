package com.mosquito.games.view.actor.hud;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mosquito.games.app.system.event.EventManager;
import com.mosquito.games.app.system.util.FontFactory;
import com.mosquito.games.app.system.util.FontFactory.FontType;
import com.mosquito.games.shared.Color;
import com.mosquito.games.view.action.AddParticleAction;
import com.mosquito.games.view.actor.helper.ActorDrawer;
import com.mosquito.games.view.constants.GraphicConstants;
import com.mosquito.games.view.event.ViewCollectFinishedEvent;
import com.mosquito.games.view.event.ViewCollectFinishedListener;

public class CollectActor extends Group implements ViewCollectFinishedListener {
	static Map<Color, CollectionCounterProperties> properties = new HashMap<Color, CollectionCounterProperties>();
	static {
		properties.put(Color.BLUE, new CollectionCounterProperties(GraphicConstants.FONT_COLOR_BLUE, GraphicConstants.COLLECTION_COUNTER_BLUE));
		properties.put(Color.GREEN, new CollectionCounterProperties(GraphicConstants.FONT_COLOR_GREEN, GraphicConstants.COLLECTION_COUNTER_GREEN));
		properties.put(Color.YELLOW, new CollectionCounterProperties(GraphicConstants.FONT_COLOR_YELLOW, GraphicConstants.COLLECTION_COUNTER_YELLOW));
		properties.put(Color.RED, new CollectionCounterProperties(GraphicConstants.FONT_COLOR_RED, GraphicConstants.COLLECTION_COUNTER_RED));
	}

	Color color;
	int remaining = 0;
	int viewRemaining = 0;
	BitmapFont font;

	public CollectActor(Color color) {
		this.color = color;
		this.font = FontFactory.make(FontType.CAMBRIA, 0.6f);
		
		CollectionCounterProperties counterProperties = properties.get(color);
		setBounds(counterProperties.position.x, counterProperties.position.y, font.getBounds("0").width, font.getBounds("0").height);
		font.setColor(counterProperties.color);
		
		EventManager.listen(ViewCollectFinishedEvent.class, this);
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getCollectColor() {
		return color;
	}

	public void SetRemaining(int remaining) {
		this.remaining = remaining;
	}

	@Override
	public void collectFinished(Color color) {
		viewRemaining = remaining;
		if (remaining > 0 && this.color == color) {
			addAction(new AddParticleAction(this, "collect"));
		}
	}

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	super.draw(batch, parentAlpha);
    	ActorDrawer.draw(this, batch, parentAlpha, font, String.valueOf(viewRemaining));
    }
}
