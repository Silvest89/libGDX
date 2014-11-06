/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvenia.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.silvenia.game.MySilvenia;
import com.silvenia.game.data.Assets;
/**
 *
 * @author Johnnie
 */
public class Splash implements Screen {
    MySilvenia game;
    
    private Texture texture = new Texture(Gdx.files.internal("splash.png"));
    private Image splashImage = new Image(texture);
    private Texture texture2 = new Texture(Gdx.files.internal("splash2.png"));
    private Image splashImage2 = new Image(texture2);
    private Stage stage = new Stage();
      
    public boolean animationDone = false;
    
    public Splash(MySilvenia game){
                this.game = game;
        }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();     
        stage.draw();
        
        if(Assets.update()){ // check if all files are loaded          
            if(animationDone){ // when the animation is finished, go to MainMenu()
                Assets.setMenuSkin(); // uses files to create menuSkin
                game.setMainMenu();
            }
        }              
    }

    @Override
    public void resize(int width, int height) {     
    }

    @Override
    public void show() {
        stage.addActor(splashImage);
        stage.addActor(splashImage2);
        
        splashImage.addAction(Actions.sequence( Actions.alpha(0)
                       ,Actions.fadeIn(1.0f),Actions.delay(1f),Actions.fadeOut(1.0f)));
        splashImage2.addAction(Actions.sequence(Actions.alpha(0)
                       ,Actions.delay(3f),Actions.fadeIn(1.0f),Actions.delay(3f),Actions.fadeOut(1.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                animationDone = true;
            }
        })));
        
        //Assets.manager.clear(); 
        //not necessary, only when splash called more then once
        Assets.queueLoading();         
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
        texture.dispose();
        texture2.dispose();
        stage.dispose();
    }

}