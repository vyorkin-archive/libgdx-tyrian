package com.vyorkin.engine.services;

import com.vyorkin.tyrian.domain.Level;
import com.vyorkin.tyrian.level.TyrianLevelLoader;

public class LevelManager {
	private final Level[] levels;
	
	public LevelManager() {
        levels = new Level[] {
    		new Level(0, "Tyrian", new TyrianLevelLoader())
        };
	}
	
	public Level[] getLevels() {
		return levels;
	}
	
	public Level get(int id) {
		return levels[id];
	}
}
