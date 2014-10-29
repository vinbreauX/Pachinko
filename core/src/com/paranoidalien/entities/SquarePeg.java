package com.paranoidalien.entities;

import com.badlogic.gdx.physics.box2d.*;

import static com.paranoidalien.managers.Box2dVars.PPM;

public class SquarePeg {

    private BodyDef bdef;
    private FixtureDef fdef;
    private PolygonShape shape;

    public SquarePeg(int posX, int posY, World world) {

        bdef = new BodyDef();

        bdef.position.set(posX / PPM, posY / PPM);
        fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        shape = new PolygonShape();
        shape.setAsBox(5 / PPM, 5 / PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
    }

}
