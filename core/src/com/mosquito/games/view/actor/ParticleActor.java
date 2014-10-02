package com.mosquito.games.view.actor;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mosquito.games.app.system.util.ParticleEffectFilenameFactory;
import com.mosquito.games.view.actor.helper.ActorDrawer;

public class ParticleActor extends Group {
	static final int POOL_SIZE = 20;
	static Map<String, ParticleEffectPool> templateEffectPools = new HashMap<String, ParticleEffectPool>();
	
	PooledEffect effect = null;
	String effectName;	
	
	public ParticleActor(String effectName) {
		this.effectName = effectName;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (effect != null) {
			float x = getX() + getParent().getWidth() / 2f;
			float y = getY() + getParent().getHeight() / 2f;
			effect.setPosition(x, y);
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (effect != null) {
			ActorDrawer.draw(this, batch, parentAlpha, effect, Gdx.graphics.getDeltaTime());
			if (effect.isComplete()) {
				effect.free();
				effect = null;
				remove();
			}
		}
	}

	public void start() {
		if (effect == null) {
			this.effect = createEffect();
		} else {
			effect.start();
		}
	}

	private PooledEffect createEffect() {
		if (!templateEffectPools.containsKey(effectName)) {
			ParticleEffect templateEffect = new ParticleEffect();
			templateEffect.load(Gdx.files.internal(ParticleEffectFilenameFactory.make(effectName)), Gdx.files.internal("data/particle"));
			ParticleEffectPool templateEffectPool = new ParticleEffectPool(templateEffect, 0, POOL_SIZE);

			templateEffectPools.put(effectName, templateEffectPool);
		}

		return templateEffectPools.get(effectName).obtain();
	}
}
