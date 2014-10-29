package com.paranoidalien.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paranoidalien.managers.GameStateManager;

public class Game implements ApplicationListener {

    public static final String TITLE = "Pachinko";
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final float STEP = 1 / 60f;
    private float accum;

    private SpriteBatch sb;
    private OrthographicCamera cam;
    private OrthographicCamera hudCam;

    private GameStateManager gsm;

    @Override
    public void create() {
        sb = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, WIDTH, HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, WIDTH, HEIGHT);

        gsm = new GameStateManager(this);
    }

    @Override
    public void render() {
        accum += Gdx.graphics.getDeltaTime();
        while (accum >- STEP) {
            accum -= STEP;
            gsm.update(STEP);
            gsm.render();
        }
    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    // Getters And Setters
    public SpriteBatch getSpriteBatch() {
        return sb;
    }

    public OrthographicCamera getCamera() {
        return cam;
    }

    public OrthographicCamera getHUDCamera() {
        return hudCam;
    }
}
