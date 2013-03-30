package com.vyorkin.engine.services;

import com.badlogic.gdx.assets.AssetManager;

public interface ResourceLoader {
	public void load(AssetManager assets);
	public void unload(AssetManager assets);
}
