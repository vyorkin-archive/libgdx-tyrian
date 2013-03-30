package com.vyorkin.engine.diagnostics;

import com.badlogic.gdx.Gdx;
import com.vyorkin.engine.Engine;
import com.vyorkin.engine.common.Renderable;

public class DiagnosticsRenderer implements Renderable {
	@Override
	public void render(float delta) {
		Engine.batch.begin();
		
		Engine.font.drawMultiLine(
			Engine.batch, getDiagnostics(), 
			Engine.settings.width - 160, 60
		);
		
		Engine.batch.end();
	}
	
	private String getDiagnostics() {
		return 
			"FPS: " + Gdx.graphics.getFramesPerSecond() + "\n" +
			"Java heap: " + Gdx.app.getJavaHeap()/1024/1024 + "M" + "\n" +
			"Native heap: " + Gdx.app.getNativeHeap()/1024/1024 + "M";
	}
}
