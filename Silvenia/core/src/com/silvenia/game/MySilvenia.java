package com.silvenia.game;

import com.badlogic.gdx.Game;
import com.silvenia.game.data.Assets;
import static com.silvenia.game.data.Assets.manager;
import com.silvenia.game.screens.Splash;
import com.silvenia.game.screens.MainMenu;
import com.silvenia.game.screens.TiledGame;

public class MySilvenia extends Game {
    public static final String TITLE="Game Project"; 
    public static final int WIDTH=1280,HEIGHT=720; // used later to set window size
    Splash splashScreen;
    MainMenu mainMenu;
    TiledGame tiledGame;
    
    @Override
    public void create() {
            //splashScreen = new Splash(this);
            
            //mainMenu = new MainMenu(this);
            setScreen(new TiledGame(this));                    
    }
    public void setMainMenu()
    {        
        mainMenu = new MainMenu(this);
        
        setScreen(mainMenu);
    }
    public void setSplashScreen()
    {
        splashScreen = new Splash(this);
        
        setScreen(splashScreen);
    }
    
    public void setGameScreen()
    {
        tiledGame = new TiledGame(this);
        
        setScreen(tiledGame);
    }
            
}
