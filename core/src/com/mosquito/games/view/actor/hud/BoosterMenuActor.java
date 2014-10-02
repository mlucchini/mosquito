package com.mosquito.games.view.actor.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mosquito.games.view.actor.helper.ActorDrawer;
import com.mosquito.games.view.actor.input.ActorTouchRotator;
import com.mosquito.games.view.constants.GraphicConstants;

public class BoosterMenuActor extends Actor {
	static final float WHEEL_EXPOSED_AMOUNT = 0.2f;

	TextureRegion texture = new TextureRegion(new Texture("data/tex/booster_wheel.png"));

	public BoosterMenuActor() {
		setTransform();
		setListeners();
	}
	
	private void setTransform() {
		float width = texture.getTexture().getWidth();
		float height = texture.getTexture().getHeight();
		setBounds(GraphicConstants.BOOSTER_MENU.x - width / 2f, GraphicConstants.BOOSTER_MENU.y - height * (1f - WHEEL_EXPOSED_AMOUNT), width, height);
		setOrigin(width / 2f, height / 2f);
	}

	private void setListeners() {
		addListener(new ActorTouchRotator(this));
	}

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	super.draw(batch, parentAlpha);
    	ActorDrawer.draw(this, batch, parentAlpha, texture);
    }
}
