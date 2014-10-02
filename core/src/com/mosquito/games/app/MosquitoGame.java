package com.mosquito.games.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mosquito.games.app.event.AppStartedEvent;
import com.mosquito.games.app.module.ModuleManager;
import com.mosquito.games.app.system.event.EventManager;

public class MosquitoGame extends Game implements ApplicationListener {
    Stage stage;
    ModuleManager moduleManager;
    
    @Override
    public void create() {
    	this.stage = new Stage(new ExtendViewport(ExtendViewportConstants.WIDTH, ExtendViewportConstants.HEIGHT));
    	this.moduleManager = new ModuleManager(this, stage);
    	
    	Gdx.input.setInputProcessor(stage);
    	
    	EventManager.dispatch(new AppStartedEvent());
    }

    @Override
    public void dispose() {
    	stage.dispose();
    }

    @Override
    public void render() {    
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    	stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
