package com.vyorkin.tyrian.screens.base;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.vyorkin.engine.Engine;
import com.vyorkin.tyrian.resources.TyrianSkin;

public abstract class UIScreen extends StageScreen {
	private final static int WIDTH = 800;
	private final static int HEIGHT = 480;
	
	protected final boolean isDeveloperMode;
	
	protected Skin skin;
	protected Table table;
	
	public UIScreen() {
		super(WIDTH, HEIGHT);
		
		this.isDeveloperMode = Engine
			.preferences.isDeveloperMode();
	}
	
	@Override
	public void show() {
		super.show();
		
		skin = assets.get(TyrianSkin.UI, Skin.class);
		table = createTable();
		
		stage.addActor(table);
	}
	
	@Override
	public void draw(float delta) {
		super.draw(delta);
		
		if (isDeveloperMode)
			Table.drawDebug(stage);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		skin.dispose();
	}
	
	private Table createTable() {
		Table table = new Table(skin);
        table.setFillParent(true);
        
        if (isDeveloperMode)
            table.debug();

        return table;
	}
}
