package com.vyorkin.engine.utils;

import com.vyorkin.tyrian.screens.base.GameScreen;

public class SetDoneRunnable implements Runnable {
	private final GameScreen screen;
	
	public SetDoneRunnable(GameScreen screen) {
		this.screen = screen;
	}
	
	@Override
	public void run() {
		screen.setDone();
	}
}
