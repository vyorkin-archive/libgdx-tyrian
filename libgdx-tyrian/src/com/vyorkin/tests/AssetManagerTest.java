package com.vyorkin.tests;

import vyorkin.engine.loading.LoadingRenderer;
import vyorkin.engine.loading.TextLoadingRenderer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.TimeUtils;
import com.vyorkin.tyrian.screens.base.GameScreen;


public class AssetManagerTest extends ApplicationAdapter implements AssetErrorListener {
	private AssetManager manager;
	private SpriteBatch batch;
	
	private BitmapFont font1;
	private BitmapFont font2;
	private TextureAtlas atlas;
	private Texture texture;
	
	private LoadingRenderer loading;
	
	int frame = 0;
	int reloads = 0;
	
	boolean diagnosed = false;
	private long start;
	
	@Override
	public void create() {
		manager = new AssetManager();
		manager.setErrorListener(this);
		batch = new SpriteBatch();
		Texture.setAssetManager(manager);
		
		font1 = new BitmapFont(Gdx.files.internal("tests/data/font.fnt"), false);
		
		loading = new TextLoadingRenderer(batch, font1, manager, new Runnable() {
			@Override
			public void run() {
				System.out.println("took: " + (TimeUtils.nanoTime() - start) / 1000000000.0f);
				unload();
				load();
				reloads++;
			}
		});
		
		load();
	}
	
	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (loading.isDone()) {
		} else {
			loading.render(Gdx.graphics.getDeltaTime());
		}
		
		frame++;
		
	}
	
	private void load() {
		start = TimeUtils.nanoTime();
		
		texture = new Texture("tests/data/animation.png");
		atlas = new TextureAtlas(Gdx.files.internal("tests/data/pack"));
		font2 = new BitmapFont(Gdx.files.internal("tests/data/verdana39.fnt"), false);
		
		System.out.println("Plain took: " + (TimeUtils.nanoTime() - start) / 1000000000.0f);
		
		start = TimeUtils.nanoTime();
		
		manager.load("tests/data/animation.png", Texture.class);
		manager.load("tests/data/pack1.png", Texture.class);
		manager.load("tests/data/pack", TextureAtlas.class);
		manager.load("tests/data/verdana39.png", Texture.class);
		manager.load("tests/data/verdana39.fnt", BitmapFont.class);
	}
	
	private void unload() {
		texture.dispose();
		atlas.dispose();
		font2.dispose();
		
		manager.unload("tests/data/animation.png");
		manager.unload("tests/data/pack1.png");
		manager.unload("tests/data/pack");
		manager.unload("tests/data/verdana39.png");
		manager.unload("tests/data/verdana39.fnt");
	}

	@Override
	public void dispose() {
		manager.dispose();
		batch.dispose();
		font1.dispose();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void error(String fileName, Class type, Throwable t) {
		Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", (Exception)t);
	}
}
