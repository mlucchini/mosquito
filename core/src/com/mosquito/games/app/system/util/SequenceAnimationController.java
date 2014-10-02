package com.mosquito.games.app.system.util;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mosquito.games.shared.ItemType;

public class SequenceAnimationController {
	ItemType type;
	TextureAtlas atlas;

	Map<String, Animation> animations = new HashMap<String, Animation>();
	Animation currentAnimation = null;
	float currentAnimationStateTime = 0f;

	public SequenceAnimationController(ItemType type, TextureAtlas atlas) {
		this.type = type;
		this.atlas = atlas;
	}
	
	public void play(String animationName) {
		if (!animations.containsKey(animationName)) {
			Animation animation = SequenceAnimationFactory.make(type, animationName, atlas);
			animations.put(animationName, animation);
		}
		
		currentAnimation = animations.get(animationName);
		currentAnimationStateTime = 0f;
	}

	public void update(float delta) {
		currentAnimationStateTime += delta;
	}

	public boolean isAnimating() {
		return currentAnimation != null && currentAnimation.getKeyFrames().length > 0;
	}
	
	public TextureRegion getCurrentTexture() {
    	return currentAnimation.getKeyFrame(currentAnimationStateTime, false);
    }
}
