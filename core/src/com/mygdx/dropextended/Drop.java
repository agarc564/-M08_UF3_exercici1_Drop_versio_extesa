package com.mygdx.dropextended;

//Classe principal del joc, que hereta de la classe Game

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Drop extends Game {
	//variables del joc
	SpriteBatch batch;
	public BitmapFont font;

	//Punt d'entrada al joc. Cal sobrecarregar aquest mètode, no hi ha constructor
	@Override
	public void create () {
		batch = new SpriteBatch();//render de sprites en la pantalla
		font = new BitmapFont(); // render de texto en la pantalla
		this.setScreen(new MainMenuScreen(this));
	}

	//No oblidar sobrecarregar aquest mètode!!
	@Override
	public void render () {
		super.render();
	}

	//Elimina objectes pesats
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
