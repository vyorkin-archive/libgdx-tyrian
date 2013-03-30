package vyorkin.engine.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vyorkin.engine.Engine;

public class TextLoadingRenderer extends LoadingRenderer {
	private final SpriteBatch batch;
	private final BitmapFont font;
	
	public TextLoadingRenderer(SpriteBatch batch, BitmapFont font, 
			AssetManager assets, Runnable doneCallback) {
		super(assets, doneCallback);
		
		this.batch = batch;
		this.font = font;
	}
	
	@Override
	protected void draw(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		font.setColor(Color.WHITE);
		font.draw(batch, "Loading: " + getPercents(), 100, 200);
		
		batch.end();
	}
}
