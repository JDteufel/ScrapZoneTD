package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Projectile {
    protected Body body;
    protected float damage;
    protected float speed = 5f;

    public Projectile(World world, Vector2 position, Vector2 target, float damage) {
        this.damage = damage;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0f;
        fixtureDef.friction = 0f;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef);
        shape.dispose();

        Vector2 direction = target.sub(position).nor().scl(speed);
        body.setLinearVelocity(direction);

        body.setUserData(this);
    }

    public float getDamage() {
        return damage;
    }

    public Body getBody() {
        return body;
    }

    public boolean isOutOfBounds(float width, float height) {
        Vector2 pos = body.getPosition();
        return pos.x < -width || pos.x > width || pos.y < -height || pos.y > height;
    }

    public void dispose(World world) {
        if (body != null) {
            world.destroyBody(body);
        }
    }
}
