/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvenia.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author Johnnie
 */
public class Assets {
    public static AssetManager manager = new AssetManager();
    public static Skin menuSkin;

    // In here we'll put everything that needs to be loaded in this format:
    // manager.load("file location in assets", fileType.class);
    // 
    // libGDX AssetManager currently supports: Pixmap, Texture, BitmapFont,
    //     TextureAtlas, TiledAtlas, TiledMapRenderer, Music and Sound.
    public static void queueLoading() {
        manager.load("skins/menuSkin.pack", TextureAtlas.class);
    }

    //In here we'll create our skin, so we only have to create it once.
    public static void setMenuSkin() {
        if (menuSkin == null)
            menuSkin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
                    manager.get("skins/menuSkin.pack", TextureAtlas.class));
    }
    // This function gets called every render() and the AssetManager pauses the loading each frame
    // so we can still run menus and loading screens smoothly
    public static boolean update() {
        return manager.update();
    }
}