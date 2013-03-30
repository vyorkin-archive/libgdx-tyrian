package com.vyorkin.engine;

public class EngineSettings {
	public static final String VERSION = "0.0.0.01 Pre-Alpha";
	public static final String LOG = "game";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 320;
	
	public int width;
	public int height;
	public boolean useGL20;
	public String preferences;
	public String version;
	public String log;
	
	public EngineSettings() {
		this(VERSION, LOG, WIDTH, HEIGHT);
	}
	public EngineSettings(String version, 
		String log, int width, int height) {
		this.version = version;
		this.log = log;
		this.width = width;
		this.height = height;
	}
}
