package com.mosquito.games.app.system.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontFactory {
	public enum FontType {
		CAMBRIA
	}

	public static BitmapFont make(FontType fontType, float scale) {
		BitmapFont bitmapFont = null;
		switch (fontType) {
			case CAMBRIA:
			default:
				bitmapFont = new BitmapFont(Gdx.files.internal("data/font/cambria.fnt"), Gdx.files.internal("data/font/cambria.png"), false);
				break;
		}
		
		bitmapFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bitmapFont.setScale(scale);
		return bitmapFont;
	}
}
