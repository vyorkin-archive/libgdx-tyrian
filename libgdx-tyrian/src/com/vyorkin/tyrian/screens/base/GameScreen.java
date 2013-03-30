package com.vyorkin.tyrian.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vyorkin.engine.Engine;
import com.vyorkin.engine.EngineSettings;
import com.vyorkin.engine.services.MusicManager;
import com.vyorkin.engine.services.PreferencesManager;
import com.vyorkin.engine.services.SoundManager;

public abstract class GameScreen implements Screen {
	
	protected final EngineSettings settings = Engine.settings;
	protected final PreferencesManager prefs = Engine.preferences;
	protected final SoundManager sounds = Engine.sounds;
	protected final MusicManager music = Engine.music;
	protected final AssetManager assets = Engine.assets;
	protected final SpriteBatch batch = Engine.batch;
	protected final OrthographicCamera camera = Engine.camera;
	protected final BitmapFont font = Engine.font;
	
	private boolean done;
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone() {
		done = true;
	}
	
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public void render(float delta) {
		update(delta);
		
		camera.update();
		camera.apply(Gdx.gl10);
		
		//batch.setProjectionMatrix(camera.combined);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		draw(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		log(String.format("Resizing screen to: %dx%d", width, height));
	}

	@Override
	public void show() {
		log("Showing screen: " + getName());
	}

	@Override
	public void hide() {
		log("Hiding screen: " + getName()); 
	}

	@Override
	public void pause() {
		log("Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		log("Resuming screen: " + getName());
	}

	@Override
	public void dispose() {
		log("Disposing screen: " + getName());
		unload();
	}
	
	protected void log(String text) {
		Engine.log(text);
	}
	
	protected void update(float delta) {}
	protected abstract void draw(float delta);
	
	public void load() {}
	protected void unload() {}
}
