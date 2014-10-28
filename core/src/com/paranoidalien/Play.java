package com.paranoidalien;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Play extends GameState {

    private BitmapFont font = new BitmapFont();

    private World world;
    private final float gravity = 9.81f;
    private Box2DDebugRenderer boxRenderer;

    public Play(GameStateManager gsm) {
        super(gsm);

        world = new World(new Vector2(0, gravity), true);
        boxRenderer = new Box2DDebugRenderer();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);


    }

    @Override
    public void render() {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        font.draw(sb, "Play State", 100, 100);
        sb.end();
    }

    @Override
    public void dispose() {

    }

}
