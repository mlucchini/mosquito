package com.mosquito.games.app.system.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.mosquito.games.shared.ItemType;

public class SequenceAnimationFactory {
	static final float FPS = 30f;

	public static Animation make(ItemType itemType, String animationName, TextureAtlas textureAtlas) {
		Array<AtlasRegion> regions = new Array<AtlasRegion>();

    	String prefix = new StringBuilder("state0")
    		.append(itemType.level)
    		.append("_")
    		.append(animationName)
    		.append("0")
    		.toString();

    	for (int i = 1;; i++) {
    		String regionName = prefix + i;
    		AtlasRegion region = textureAtlas.findRegion(regionName);
    		if (region != null) {
    			regions.add(region);
    		} else {
    			break;
    		}
    	}
    	
    	return new Animation(1f / FPS, regions);
	}
}
