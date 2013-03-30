package com.vyorkin.tyrian;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.vyorkin.engine.CrazyGame;
import com.vyorkin.engine.EngineSettings;
import com.vyorkin.engine.utils.SetDoneRunnable;
import com.vyorkin.tyrian.resources.TyrianFont;
import com.vyorkin.tyrian.resources.TyrianMusic;
import com.vyorkin.tyrian.resources.TyrianSfx;
import com.vyorkin.tyrian.resources.TyrianSkin;
import com.vyorkin.tyrian.screens.LoadingScreen;
import com.vyorkin.tyrian.screens.MainMenuScreen;
import com.vyorkin.tyrian.screens.SplashScreen;
import com.vyorkin.tyrian.screens.base.GameScreen;

public class TyrianGame extends CrazyGame {
	public static final String VERSION = "0.0.0.02 Pre-Alpha";
	public static final String LOG = "tyrian";
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 240;
	
	@Override
	protected EngineSettings setup() {
		
		assets.load(TyrianSfx.EXPLOSION_BIG, Sound.class);
		assets.load(TyrianSfx.EXPLOSION_LONG, Sound.class);
		assets.load(TyrianSfx.EXPLOSION_SHORT, Sound.class);
		
		assets.load(TyrianSfx.HIT_ENEMY, Sound.class);
		assets.load(TyrianSfx.HIT_PLAYER, Sound.class);
		assets.load(TyrianSfx.HIT_SHIELD, Sound.class);
		
		assets.load(TyrianSfx.MENU_ENTER_CLICK, Sound.class);
		assets.load(TyrianSfx.MENU_ENTER_HIT, Sound.class);
		assets.load(TyrianSfx.MENU_EXIT, Sound.class);
		assets.load(TyrianSfx.MENU_SELECT, Sound.class);
		
		assets.load(TyrianMusic.GAME_OVER_SOLO, Music.class);
		assets.load(TyrianMusic.END_OF_LEVEL, Music.class);
		assets.load(TyrianMusic.TYRIAN_THE_SOUND, Music.class);
		
		assets.load(TyrianSkin.UI, Skin.class);
		assets.load(TyrianFont.CONSOLAS, BitmapFont.class);
		
		return new EngineSettings(VERSION, LOG, WIDTH, HEIGHT);
	}

	@Override
	protected GameScreen getNextScreen(GameScreen screen) {
		
		if (screen == null) {
			return new SplashScreen();
		} else if (screen instanceof SplashScreen) {
			return new MainMenuScreen();
		} else if (screen instanceof MainMenuScreen) {
			MainMenuScreen menuScreen = (MainMenuScreen)screen;
			return menuScreen.getNextScreen();
		} else if (screen instanceof LoadingScreen) {
			LoadingScreen loadingScreen = (LoadingScreen)screen;
			return loadingScreen.getNextScreen();
		}
		
		return null;
	}
}
