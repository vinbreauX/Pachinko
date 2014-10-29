package com.paranoidalien.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.paranoidalien.entities.SquarePeg;
import com.paranoidalien.main.Game;
import com.paranoidalien.managers.GameStateManager;

import static com.paranoidalien.managers.Box2dVars.PPM;


public class Play extends GameState {

    private World world;
    private final float gravity = 9.81f;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dcam;

    private Array<SquarePeg> squarePegs;

    public Play(GameStateManager gsm) {
        super(gsm);

        world = new World(new Vector2(0, gravity), true);
        b2dr = new Box2DDebugRenderer();

        placePegs();

        // Set up camera
        b2dcam = new OrthographicCamera();
        b2dcam.setToOrtho(false, Game.WIDTH / PPM, Game.HEIGHT / PPM);

    }

    private void placePegs() {
        squarePegs = new Array<SquarePeg>();
        for (int i = 10; i < 100; i = i + 10) {
            for (int j = 10; j < 100; j = j + 10) {
                SquarePeg peg = new SquarePeg((int) ((Game.WIDTH * i) / PPM), (int)((Game.HEIGHT * j) / PPM), world);
                squarePegs.add(peg);
            }
        }

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
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world, b2dcam.combined);
    }

    @Override
    public void dispose() {

    }

}
