package com.paranoidalien.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.paranoidalien.entities.RoundPeg;
import com.paranoidalien.main.Game;
import com.paranoidalien.managers.Box2dVars;
import com.paranoidalien.managers.GameContactListener;
import com.paranoidalien.managers.GameInputProcessor;
import com.paranoidalien.managers.GameStateManager;

import static com.paranoidalien.managers.Box2dVars.PPM;


public class Play extends GameState {

    private World world;
    private final float gravity = -9.81f;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dcam;

    public Play(GameStateManager gsm) {
        super(gsm);

        world = new World(new Vector2(0, gravity), true);
        world.setContactListener(new GameContactListener());
        b2dr = new Box2DDebugRenderer();

        placePegs();
        placeWalls();
        placeCatchers();
        dropBall(MathUtils.random(100, 300));

        // Set up camera
        b2dcam = new OrthographicCamera();
        b2dcam.setToOrtho(false, Game.WIDTH / PPM, Game.HEIGHT / PPM);

    }


    private void placePegs() {
        // 9 Peg rows
        for (int i = 10; i < 100; i = i + 10) {
            for (int j = 10; j < 100; j = j + 10) {
                RoundPeg peg = new RoundPeg((int) (Game.WIDTH * i / PPM), (int) ((Game.HEIGHT * j) / PPM), world);

            }
        }

        // 3 Peg rows
        for (int i = 25; i < 100; i = i + 25) {
            for (int j = 120; j < 700; j = j + 160) {
                RoundPeg peg = new RoundPeg((int) (Game.WIDTH * i / PPM), (int) j, world);
            }
        }

        // 2 Peg Rows wide
        for (int i = 15; i < 100; i = i + 70) {
            for (int j = 200; j < 700; j = j + 160) {
                RoundPeg peg = new RoundPeg((int) (Game.WIDTH * i / PPM), (int) j, world);
            }
        }

        // 2 Peg rows narrow
        for (int i = 35; i < 90; i = i + 30) {
            RoundPeg peg = new RoundPeg((int) (Game.WIDTH * i / PPM), 355, world);
        }
    }

    private void placeWalls() {
        BodyDef bdef;
        FixtureDef fdef;
        PolygonShape shape;

        bdef = new BodyDef();
        fdef = new FixtureDef();
        shape = new PolygonShape();

        // Left Wall
        bdef.position.set(0 / PPM, 0 / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);
        shape.setAsBox(5 / PPM, Game.HEIGHT / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = Box2dVars.BIT_WALLS;
        fdef.filter.maskBits = Box2dVars.BIT_BALL;
        body.createFixture(fdef).setUserData("wall");

        // Right wall
        bdef.position.set(Game.WIDTH / PPM, 0 / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body1 = world.createBody(bdef);
        shape.setAsBox(5 / PPM, Game.HEIGHT / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = Box2dVars.BIT_WALLS;
        fdef.filter.maskBits = Box2dVars.BIT_BALL;
        body1.createFixture(fdef).setUserData("wall");

        // Bottom wall
        bdef.position.set(0 / PPM, 0 / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body2 = world.createBody(bdef);
        shape.setAsBox(Game.WIDTH / PPM, 5 / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = Box2dVars.BIT_WALLS;
        fdef.filter.maskBits = Box2dVars.BIT_BALL;
        body2.createFixture(fdef).setUserData("wall");
    }

    private void placeCatchers() {
        BodyDef bdef;
        FixtureDef fdef;
        PolygonShape shape;

        bdef = new BodyDef();
        fdef = new FixtureDef();
        shape = new PolygonShape();

        for (int i = 10; i < 100; i = i + 10) {
            bdef.position.set((Game.WIDTH * i / PPM) / PPM, 0);
            bdef.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bdef);
            shape.setAsBox(5 / PPM, 30 / PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = Box2dVars.BIT_WALLS;
            fdef.filter.maskBits = Box2dVars.BIT_BALL;
            body.createFixture(fdef).setUserData("wall");
            System.out.println("placing catcher");
        }
    }

    @Override
    public void handleInput() {


    }

    private void dropBall(int posX){

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        bdef.position.set(posX / PPM, 790 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        // Randomly drop ball
        Body body = world.createBody(bdef);
        CircleShape cshape = new CircleShape();
        cshape.setRadius(13 / PPM);
        fdef.shape = cshape;
        fdef.filter.categoryBits = Box2dVars.BIT_BALL;
        fdef.filter.maskBits = Box2dVars.BIT_WALLS | Box2dVars.BIT_PEG;
        fdef.restitution = 0.7f;
        fdef.density = 0.9f;
        fdef.friction = 0.1f;
        body.createFixture(fdef).setUserData("ball");
    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);


    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world, b2dcam.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();

    }

}
