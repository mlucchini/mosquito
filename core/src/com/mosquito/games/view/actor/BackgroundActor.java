package com.mosquito.games.view.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mosquito.games.view.actor.helper.ActorDrawer;

public class BackgroundActor extends Actor {
	TextureRegion texture = new TextureRegion(new Texture("data/tex/background.png"));
	
	public BackgroundActor(Vector2 parentSize) {
		setBounds((parentSize.x - texture.getRegionWidth()) / 2f, 0f, texture.getTexture().getWidth(), texture.getTexture().getHeight());
	}

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	ActorDrawer.draw(this, batch, parentAlpha, texture);
    }
}
