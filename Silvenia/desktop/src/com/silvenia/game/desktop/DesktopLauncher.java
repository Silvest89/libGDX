package com.silvenia.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.silvenia.game.MySilvenia;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width=MySilvenia.WIDTH; // sets window width
                config.height=MySilvenia.HEIGHT;  // sets window height
                config.addIcon("icon.png", Files.FileType.Internal);
                config.resizable = false;
                //TexturePacker.process("map", "map", "terrain.json");
		new LwjglApplication(new MySilvenia(), config);                
	}
}
