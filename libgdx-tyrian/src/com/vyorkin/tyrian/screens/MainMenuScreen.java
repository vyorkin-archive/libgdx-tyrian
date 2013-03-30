package com.vyorkin.tyrian.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.vyorkin.engine.Engine;
import com.vyorkin.engine.services.ResourceLoader;
import com.vyorkin.engine.utils.TouchUpListener;
import com.vyorkin.tyrian.domain.Level;
import com.vyorkin.tyrian.resources.TyrianMusic;
import com.vyorkin.tyrian.resources.TyrianSfx;
import com.vyorkin.tyrian.screens.base.GameScreen;
import com.vyorkin.tyrian.screens.base.UIScreen;

public class MainMenuScreen extends UIScreen {
	private class SetScreenListener extends TouchUpListener {
		private final GameScreen screen;
		
		public SetScreenListener(GameScreen screen) {
			this.screen = screen;
		}
		
		@Override
    	public void touchUp(InputEvent event, 
			float x, float y, int pointer, int button) {
    		sounds.play(TyrianSfx.MENU_ENTER_HIT);
    		setNextScreen(screen);
    	}
	}
	
	private GameScreen nextScreen;
	
	private void setNextScreen(GameScreen screen) {
		this.nextScreen = screen;
		setDone();
	}
	
	public GameScreen getNextScreen() {
		return nextScreen;
	}
	
	@Override
	public void show() {
		super.show();
		
        table.add("Улетай в космос нахуй!").spaceBottom(50);
        table.row();
        
        Level level = Engine.levels.get(0);
        ResourceLoader loader = level.getLoader();
        
        addButton("Новая игра", new LoadingScreen(loader, new LevelScreen(level)));
        addButton("Загрузить игру", new LoadGameScreen()); 
        addButton("Лучшие игроки", new HighScoreScreen());
        addButton("Настройки", new OptionsScreen());
        
        music.play(TyrianMusic.TYRIAN_THE_SOUND);
	}
	
	private void addButton(String text, GameScreen screen) {
		TextButton button = new TextButton(text, skin);
		button.addListener(new SetScreenListener(screen));
        table.add(button).uniform().fill().spaceBottom(10).row();
	}

	@Override
	public void load() {
		super.load();
		assets.load(TyrianMusic.TYRIAN_THE_SOUND, Music.class);
	}

	@Override
	protected void unload() {
		super.unload();
		assets.unload(TyrianMusic.TYRIAN_THE_SOUND);
	}
}
