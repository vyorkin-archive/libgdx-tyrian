package com.vyorkin.tyrian.domain;

import java.util.Locale;

import com.vyorkin.engine.utils.TextUtils;


public abstract class Product {
	private final String name;
	private final int price;
	
	protected Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSimpleName() {
		return getName()
			.replace(' ', '-')
			.toLowerCase();
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getPriceString() {
		return TextUtils.credits(price);
	}
	
	public String toString() {
		return String.format(Locale.US, "%s (%s)", 
			getName(), getPriceString());
	}
}
