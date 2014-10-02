package com.mosquito.games.app.system.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinFactory {
	public enum SkinType {
		DEFAULT
	}

	public static Skin make(SkinType type) {
		switch (type) {
			case DEFAULT:
			default:
				return new Skin(Gdx.files.internal("data/skin/skin.json"));
		}
	}
}
