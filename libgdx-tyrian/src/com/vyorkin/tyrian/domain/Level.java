package com.vyorkin.tyrian.domain;

import com.badlogic.gdx.utils.Array;
import com.vyorkin.engine.services.ResourceLoader;

public class Level {
	private final int id;
	private final String name;
	private final ResourceLoader loader;
	
	private boolean completed;
	private Array<Level> next;
	
	public Level(int id, String name, ResourceLoader loader) {
		this.id = id;
		this.name = name;
		this.loader = loader;
	}
	
	public Level(int id, String name, ResourceLoader loader, Array<Level> next) {
		this(id, name, loader);
		
		this.next = next;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public ResourceLoader getLoader() {
		return loader;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public Level[] getNext() {
		return next.items;
	}
	
	public void setNext(Level[] levels) {
		next = new Array<Level>(levels);
	}

	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
