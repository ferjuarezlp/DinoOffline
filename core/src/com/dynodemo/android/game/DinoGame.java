package com.dynodemo.android.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dynodemo.android.screens.PlayScreen;
import com.dynodemo.android.states.GameStateManager;
import com.dynodemo.android.states.MenuState;

public class DinoGame extends Game {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	public static final String TITLE = "DinoDemo";
	private GameStateManager gsm;
	public  SpriteBatch batch;
	private Music music;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("macaron_island.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		super.render();
		/*Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);*/
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
