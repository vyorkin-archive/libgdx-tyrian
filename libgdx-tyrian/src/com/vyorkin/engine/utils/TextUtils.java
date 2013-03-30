package com.vyorkin.engine.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class TextUtils {
	private static final NumberFormat FORMATTER;
	
	static {
		FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
		FORMATTER.setMinimumFractionDigits(0);
		FORMATTER.setMaximumFractionDigits(0);
	}
	
	private TextUtils() {
	}
	
	public static String credits(int value) {
		return FORMATTER.format(value);
	}
}
