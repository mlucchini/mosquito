package com.mosquito.games.view.actor.helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorDrawer {
	public static void draw(Actor actor, Batch batch, float parentAlpha, TextureRegion texture) {
		Color initialBatchColor = setBatchColor(actor, batch, parentAlpha);
    	batch.draw(texture, actor.getX(), actor.getY(), actor.getOriginX(), actor.getOriginY(), actor.getWidth(), actor.getHeight(), actor.getScaleX(), actor.getScaleY(), actor.getRotation());
    	setBatchColor(batch, initialBatchColor);
	}
	
	public static void draw(Actor actor, Batch batch, float parentAlpha, BitmapFont font, CharSequence text) {
		Color initialBatchColor = setBatchColor(actor, batch, parentAlpha);
		font.draw(batch, text, actor.getX(), actor.getY());
		setBatchColor(batch, initialBatchColor);
	}
	
	public static void draw(Actor actor, Batch batch, float parentAlpha, PooledEffect effect, float deltaTime) {
		Color initialBatchColor = setBatchColor(actor, batch, parentAlpha);
		effect.draw(batch, deltaTime);
		setBatchColor(batch, initialBatchColor);
	}

	private static Color setBatchColor(Actor actor, Batch batch, float parentAlpha) {
		if (shouldSetBatchColor(actor)) {
			Color initialBatchColor = batch.getColor();
			Color mixedActorColor = new Color(actor.getColor());
	    	mixedActorColor.a *= parentAlpha;
	    	setBatchColor(batch, mixedActorColor);
	    	return initialBatchColor;
		}
		return null;
	}
	
	private static void setBatchColor(Batch batch, Color color) {
		if (color != null) {
			batch.setColor(color);
		}
	}

	private static boolean shouldSetBatchColor(Actor actor) {
		return !actor.getColor().equals(Color.WHITE);
	}
}
