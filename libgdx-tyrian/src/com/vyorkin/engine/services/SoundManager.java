package com.vyorkin.engine.services;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.vyorkin.engine.Engine;

public class SoundManager {
	private final AssetManager assets;
	
	private float volume;
	private boolean muted;
	
	public SoundManager(boolean muted, float volume) {
		this.assets = Engine.assets;
		this.volume = 1;
		this.muted = false;
	}
	
	public void play(String fileName) {
		if (muted)
			return;
		
		Engine.log("Playing sound: " + fileName);
		assets.get(fileName, Sound.class).play(volume);
	}
	
	public float getVolume() {
		return volume;
	}
	
	public void setVolume(float volume) {
		Engine.log("Adjusting volume to: " + volume);
		
		this.volume = volume;
	}
	
	public boolean isMuted() {
		return muted;
	}
	
	public void setMuted(boolean muted) {
		this.muted = muted;
	}
}
