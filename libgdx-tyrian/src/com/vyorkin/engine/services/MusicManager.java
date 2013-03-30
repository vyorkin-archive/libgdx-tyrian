package com.vyorkin.engine.services;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;
import com.vyorkin.engine.Engine;

public class MusicManager implements Disposable {
	private final AssetManager assets;
	
    private String current;
    private float volume;
    private boolean muted;

    public MusicManager(boolean muted, float volume) {
    	this.assets = Engine.assets;
    	this.muted = false;
    	this.volume = 1;
    }

    public void play(String fileName) {
    	if (muted || current == fileName)
    		return;
    	
    	Engine.log("Playing music: " + fileName);
    	
    	stop();

    	Music resource = assets.get(fileName, Music.class);
    	
    	resource.setVolume(volume);
    	resource.setLooping(true);
    	resource.play();
    	
    	current = fileName;
    }
    
    public void stop() {
    	if (current != null) {
    		Engine.log("Stopping current music");
    		assets.get(current, Music.class).stop();
    	}
    }
    
    public void setVolume(float volume) {
    	Engine.log("Adjusting music volume to: " + volume);
    	
    	this.volume = volume;
    	
    	if (current != null)
    		assets.get(current, Music.class).setVolume(volume);
    }
    
    public boolean isMuted() {
    	return muted;
    }
    
    public void setMuted(boolean muted) {
    	this.muted = muted;
    	if (muted)
    		stop();
    }
    
    public void dispose() {
    	Engine.log("Disposing music manager");
    	stop();
    }
}