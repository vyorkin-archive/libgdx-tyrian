package com.vyorkin.engine;

import vyorkin.engine.loading.LoadingRenderer;
import vyorkin.engine.loading.TextLoadingRenderer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import com.vyorkin.engine.diagnostics.DiagnosticsRenderer;
import com.vyorkin.engine.services.*;
import com.vyorkin.tyrian.screens.LoadingScreen;
import com.vyorkin.tyrian.screens.base.GameScreen;

public abstract class CrazyGame extends Game {
	private LoadingRenderer loading;
	
	protected AssetManager assets;
	protected PreferencesManager prefs;
	
	private FPSLogger fpsLogger;
	private DiagnosticsRenderer diagnostics;
	
	private GameScreen nextScreen;
	
	@Override
	public void create() {
		this.nextScreen = null;
		
		this.assets = new AssetManager();
		this.prefs = new PreferencesManager();
		
		Engine.assets = assets;
		Engine.font = new BitmapFont();
		Engine.batch = new SpriteBatch();
		Engine.preferences = prefs;
		
		this.loading = new TextLoadingRenderer(
			Engine.batch, Engine.font, 
			Engine.assets, new Runnable() {
				@Override
				public void run() {
					setScreen(nextScreen);
				}
			}
		);
		
		if (prefs.isDeveloperMode()) {
			this.diagnostics = new DiagnosticsRenderer();
			this.fpsLogger = new FPSLogger();
		}
		
		
		Engine.levels = new LevelManager();
		Engine.locales = new LocaleManager();
		Engine.profiles = new ProfileManager();

		Engine.music = new MusicManager(prefs.isMusicMuted(), prefs.getMusicVolume());
		Engine.sounds = new SoundManager(prefs.isSoundMuted(), prefs.getSoundVolume());

		EngineSettings settings = setup();
		Engine.settings = settings;
		
		OrthographicCamera camera = new OrthographicCamera(settings.width, settings.height);
		camera.position.set(settings.width / 2, settings.height / 2, 0);
		
		Engine.camera = camera;
		
		assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		Texture.setAssetManager(assets);
		
		this.nextScreen = getNextScreen(null);
		this.nextScreen.load();
	}
	
	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		
		Engine.log("Setting screen: " + 
			screen.getClass().getSimpleName());
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		Engine.log(String.format(
			"Resizing to: %dx%d", width, height));
	}
	
	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		GameScreen currentScreen = (GameScreen)getScreen();
		
		if (loading.isDone()) {
			if (currentScreen.isDone()) {
				currentScreen.dispose();
				
				nextScreen = getNextScreen(currentScreen);
				nextScreen.load();
				if (assets.getQueuedAssets() == 0) {
					setScreen(nextScreen);
				}
			} else {
				currentScreen.render(delta);
			}
		} else {
			if (currentScreen instanceof LoadingScreen) {
				currentScreen.render(delta);
			} else {
				loading.render(delta);
			}
		}
		
		if (prefs.isDeveloperMode()) {
			diagnostics.render(delta);
			fpsLogger.log();
		}
	}

	@Override
	public void pause() {
		super.pause();
		Engine.log("pausing");
	}
	
	@Override
	public void resume() {
		super.resume();
		Engine.log("Resuming");
	}
	
	@Override 
	public void dispose() {
		super.dispose();
		
		Engine.log("Disposing");
		
		Engine.music.dispose();
		Engine.font.dispose();
		Engine.batch.dispose();
		Engine.assets.dispose();
	}
	
	protected abstract EngineSettings setup();
	protected abstract GameScreen getNextScreen(GameScreen screen);
}
