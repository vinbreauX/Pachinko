package com.paranoidalien.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GameContactListener implements ContactListener {

    private boolean firstSound = true;

    private Sound bounce1 = Gdx.audio.newSound(Gdx.files.internal("audio/blip_01.wav"));
    private Sound bounce2 = Gdx.audio.newSound(Gdx.files.internal("audio/blip_02.wav"));

    @Override
    public void beginContact(Contact c) {
        if (firstSound) {
            bounce1.play();
        } else {
            bounce2.play();
        }
        firstSound = !firstSound;

    }

    @Override
    public void endContact(Contact c) {

    }

    @Override
    public void preSolve(Contact c, Manifold m) {

    }

    @Override
    public void postSolve(Contact c, ContactImpulse ci) {

    }

}
