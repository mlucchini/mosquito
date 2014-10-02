package com.mosquito.games.view.actor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mosquito.games.app.module.event.ModuleFinishedEvent;
import com.mosquito.games.view.action.DispatchViewEventAction;

public class LoadingActor extends Group {
	static final float FADEIN_DURATION = 0.5f;
	static final float LOADING_DURATION = 0.5f;
	static final float FADEOUT_DURATION = 0.5f;

	TextureRegion texture = new TextureRegion(new Texture("data/tex/loading.jpg"));

	public LoadingActor(Vector2 parentSize) {
		setBounds((parentSize.x - texture.getRegionWidth()) / 2f, 0f, texture.getRegionWidth(), texture.getRegionHeight());

		fadeInOut();
	}

	@Override
    public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

    	Color batchColor = batch.getColor();
    	Color actorColor = new Color(getColor());
    	actorColor.a *= parentAlpha;
    	
    	batch.setColor(actorColor);
    	batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    	//batch.draw(texture, getX(), getY());
    	batch.setColor(batchColor);
    }

	private void fadeInOut() {
		getColor().a = 0f;
		addAction(sequence(
				alpha(1f, FADEIN_DURATION),
				delay(LOADING_DURATION),
				alpha(0f, FADEOUT_DURATION),
				new DispatchViewEventAction(new ModuleFinishedEvent())));
	}
}
