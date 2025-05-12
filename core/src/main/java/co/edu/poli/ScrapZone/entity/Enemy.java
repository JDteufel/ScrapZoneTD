package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Enemy {
    protected Body body;
    protected float speed;
    protected int health;

    public Enemy(World world, Vector2 position) {
        createBody(world, position);
    }

    private void createBody(World world, Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public abstract void update(float delta);

    public boolean isDead() {
        return health <= 0;
    }

    public Body getBody() {
        return body;
    }
}
