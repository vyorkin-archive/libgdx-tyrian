package com.vyorkin.tyrian.level;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

import com.vyorkin.engine.services.ResourceLoader;
import com.vyorkin.tyrian.resources.TyrianMusic;

public class TyrianLevelLoader implements ResourceLoader {
	@Override
	public void load(AssetManager assets) {
		assets.load(TyrianMusic.TYRIAN_THE_LEVEL, Music.class);
	}

	@Override
	public void unload(AssetManager assets) {
		assets.unload(TyrianMusic.TYRIAN_THE_LEVEL);
	}
}
