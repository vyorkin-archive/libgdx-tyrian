package com.vyorkin.engine.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager {
	private static final String KEY = "default";
	private static final String DEVELOPER_MODE = "developer";
	private static final String MUSIC_VOLUME = "music.volume";
	private static final String MUSIC_MUTED = "music.muted";
	private static final String SOUND_VOLUME = "sound.volume";
	private static final String SOUND_MUTED = "sound.muted";
	
	private Preferences get() {
		return Gdx.app.getPreferences(KEY);
	}
	
	public boolean isDeveloperMode() {
		return get().getBoolean(DEVELOPER_MODE, true);
	}
	
	public boolean isMusicMuted() {
		return get().getBoolean(MUSIC_MUTED, false);
	}
	public void setMusicMuted(boolean muted) {
		get().putBoolean(MUSIC_MUTED, muted);
		get().flush();
	}
	
	public float getMusicVolume() {
		return get().getFloat(MUSIC_VOLUME);
	}
	public void setMusicVolume(float volume) {
		get().putFloat(MUSIC_VOLUME, volume);
		get().flush();
	}
	
	public boolean isSoundMuted() {
		return get().getBoolean(SOUND_MUTED);
	}
	public void setSoundMuted(boolean muted) {
		get().putBoolean(SOUND_MUTED, false);
		get().flush();
	}
	
	public float getSoundVolume() {
		return get().getFloat(SOUND_VOLUME);
	}
	public void setSoundVolume(float volume) {
		get().putFloat(SOUND_VOLUME, volume);
		get().flush();
	}
}
