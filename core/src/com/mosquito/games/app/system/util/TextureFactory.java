package com.mosquito.games.app.system.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mosquito.games.shared.ItemType;

public class TextureFactory {

	public static TextureRegion makeRegion(ItemType type) {
		StringBuilder textureName = new StringBuilder("data/tex/chara0");
		textureName.append(type.color.ordinal() + 1);
		textureName.append("_state0");
		textureName.append(type.level);
		textureName.append(".png");

		return new TextureRegion(new Texture(textureName.toString()));
	}
	
	public static TextureAtlas makeAtlas(ItemType type) {
		StringBuilder atlasName = new StringBuilder("data/tex/chara0");
		atlasName.append(type.color.ordinal() + 1);
		atlasName.append(".pack");

		return new TextureAtlas(Gdx.files.internal(atlasName.toString()));
	}
	
	public static TextureAtlas makeAtlas(String path) {
		return new TextureAtlas(Gdx.files.internal(path));
	}
}
