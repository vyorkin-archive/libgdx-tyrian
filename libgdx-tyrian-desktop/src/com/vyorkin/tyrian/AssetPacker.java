package com.vyorkin.tyrian;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class AssetPacker {
	public static void main(String[] args) {
        TexturePacker2.Settings s = new TexturePacker2.Settings();
        s.maxHeight = 2048;
        
        TexturePacker2.process(s, "resources/loading", "../libgdx-tyrian-android/assets/loading", "loading.pack");
        TexturePacker2.process(s, "resources/level", "../libgdx-tyrian-android/assets/level", "level.pack");
    }
}
