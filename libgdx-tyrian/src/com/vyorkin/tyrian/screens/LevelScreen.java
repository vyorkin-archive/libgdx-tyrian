package com.vyorkin.tyrian.screens;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.vyorkin.tyrian.domain.Level;

import com.vyorkin.tyrian.resources.TyrianLevel;
import com.vyorkin.tyrian.screens.base.GameScreen;

public class LevelScreen extends GameScreen {
	private final Level level;
	private TiledMap map;
	private TiledMapRenderer renderer;
	
	public LevelScreen(Level level) {
		this.level = level;
	}

	@Override
	public void show() {
		super.show();

//		map = assets.get(TyrianLevel.TYRIAN);
		map = new TmxMapLoader().load(TyrianLevel.TYRIAN);
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 12f);
		
		camera.setToOrtho(false, 30, 20);
		camera.update();
	}
	
	@Override
	protected void draw(float delta) {
		renderer.setView(camera);
		renderer.render();
	}

	@Override
	public void load() {
		//assets.load("tests/data/maps/tiled/super-koalio/level1.tmx", TiledMap.class);
	}

	@Override
	protected void unload() {
		//assets.unload("tests/data/maps/tiled/super-koalio/level1.tmx");
	}
}
