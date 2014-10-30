package com.paranoidalien.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.paranoidalien.managers.Box2dVars;

import static com.paranoidalien.managers.Box2dVars.PPM;

public class RoundPeg {

    private BodyDef bdef;
    private FixtureDef fdef;
    private PolygonShape shape;
    private CircleShape cshape;

    public RoundPeg(int posX, int posY, World world) {

        bdef = new BodyDef();

        bdef.position.set(posX / PPM, posY / PPM);
        fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        cshape = new CircleShape();
        cshape.setRadius(4 / PPM);
        fdef.shape = cshape;
        fdef.filter.categoryBits = Box2dVars.BIT_PEG;
        fdef.filter.maskBits = Box2dVars.BIT_BALL;
        body.createFixture(fdef).setUserData("peg");
    }


}
