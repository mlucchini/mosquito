package com.mosquito.games.app.system.util;


public class ParticleEffectFilenameFactory {
	public static String make(String effectName) {
		StringBuilder effectFilename = new StringBuilder("data/particle/")
			.append(effectName)
			.append(".effect");
		return effectFilename.toString();
	}
}
