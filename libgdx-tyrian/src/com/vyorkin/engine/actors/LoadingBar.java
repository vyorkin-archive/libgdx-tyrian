package com.vyorkin.engine.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBar extends Actor {
	
    private final Animation animation;
    
    private TextureRegion reg;
    private float stateTime;

    public LoadingBar(Animation animation) {
        this.animation = animation;
        reg = animation.getKeyFrame(0);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        reg = animation.getKeyFrame(stateTime);
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.draw(reg, getX(), getY());
    }
}
