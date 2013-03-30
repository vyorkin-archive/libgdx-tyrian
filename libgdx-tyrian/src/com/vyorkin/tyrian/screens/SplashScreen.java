package com.vyorkin.tyrian.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

import com.vyorkin.engine.Engine;
import com.vyorkin.engine.utils.SetDoneRunnable;
import com.vyorkin.tyrian.resources.TyrianTexture;
import com.vyorkin.tyrian.screens.base.StageScreen;

public class SplashScreen extends StageScreen {
	private Image splashImage;
	
	@Override
	public void show() {
		super.show();
		
		Texture texture = Engine.assets.get(TyrianTexture.SPLASH, Texture.class);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    
        TextureRegion region = new TextureRegion(texture, 0, 0, 512, 320);
        Drawable drawable = new TextureRegionDrawable(region);
        splashImage = new Image(drawable, Scaling.stretch);
        splashImage.setFillParent(true);

        splashImage.getColor().a = 0f;
		
		splashImage.addAction(Actions.sequence(
			Actions.fadeIn(.5f), Actions.delay(1), Actions.fadeOut(.5f),
			Actions.run(new SetDoneRunnable(this))
		));
		
		stage.addActor(splashImage);
	}
	
	@Override
	public void load() {
		super.load();
		Engine.assets.load(TyrianTexture.SPLASH, Texture.class);
	}

	@Override
	protected void unload() {
		super.unload();
		Engine.assets.unload(TyrianTexture.SPLASH);
	}
}
