package vyorkin.engine.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.vyorkin.engine.Engine;
import com.vyorkin.engine.common.Renderable;

public abstract class LoadingRenderer implements Renderable {
	private static final String LOG = "LoadingRenderer";
	
	private final Runnable doneCallback;
	private final AssetManager assets;
	
	protected LoadingRenderer(AssetManager assets) {
		this(assets, null);
	}
	protected LoadingRenderer(AssetManager assets, Runnable doneCallback) {
		this.assets = assets;
		this.doneCallback = doneCallback;
	}
	
	public int getPercents() {
		return (int)(assets.getProgress() * 100.0f);
	}
	
	public boolean isDone() {
		return assets.getProgress() == 1;
	}
	
	public void render(float delta) {
		if (assets.update()) {
			if (doneCallback != null) {
				doneCallback.run();
			}
		} else {
			draw(delta);
			Gdx.app.debug(LOG, "Loading: " + getPercents());
		}
	}
	
	protected abstract void draw(float delta);
}
