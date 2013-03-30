package com.vyorkin.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vyorkin.engine.services.*;

public class Engine {
	public static PreferencesManager preferences;
	public static LocaleManager locales;
	public static SoundManager sounds;
	public static MusicManager music;
	public static ProfileManager profiles;
	public static LevelManager levels;
	public static AssetManager assets;
	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	public static EngineSettings settings;
	public static BitmapFont font;
	
	public static void log(String text) {
		Gdx.app.log(settings.log, text);
	}
}
