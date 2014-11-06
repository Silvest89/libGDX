/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvenia.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader;
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader.AtlasTiledMapLoaderParameters;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.silvenia.game.MySilvenia;
import com.silvenia.game.creatures.Player;


/**
 *
 * @author Johnnie
 */
public class TiledGame implements Screen, InputProcessor{
    MySilvenia game;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    
    private Player player;
    private TextureAtlas playerAtlas;
    private MapProperties prop;
    public TiledGame(MySilvenia game){
                this.game = game;
        }

    @Override
    public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        AtlasTiledMapLoaderParameters parameters = new AtlasTiledMapLoaderParameters();
        parameters.forceTextureFilters = true;
        parameters.textureMinFilter = Texture.TextureFilter.Nearest;
        parameters.textureMagFilter = Texture.TextureFilter.Nearest;
        
        tiledMap = new AtlasTmxMapLoader().load("map/untitled.tmx", parameters);
        prop = tiledMap.getProperties();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        
        playerAtlas = new TextureAtlas("player.pack");
        
        player = new Player(this, playerAtlas, (TiledMapTileLayer)tiledMap.getLayers().get(0));
        player.setPosition(2 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 46) * player.getCollisionLayer().getTileHeight());
        
        Gdx.input.setInputProcessor(player);
    }    
    
    @Override
    public void render(float delta) {   
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();
        
        tiledMapRenderer.setView(camera);       
        
        tiledMapRenderer.getSpriteBatch().begin();
        tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("background"));
        player.draw(tiledMapRenderer.getSpriteBatch());
        //tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get(""));
        tiledMapRenderer.getSpriteBatch().end();
                
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,32);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,-32);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

        @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
    @Override
    public void resize(int width, int height) {     
    }
    
    
    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {       
    }

    @Override
    public void resume() {      
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
        tiledMapRenderer.dispose();
        player.getTexture().dispose();
        playerAtlas.dispose();       
        
    }
    
    public int getMapWidth(){
        return prop.get("width", Integer.class);
    }
    
    public int getMapHeight(){
        return prop.get("height", Integer.class);
    }

    public int getTileWidth(){
        return prop.get("tilewidth", Integer.class);
    }
    
    public int getTileHeight(){
        return prop.get("tileheight", Integer.class);
    }
}
