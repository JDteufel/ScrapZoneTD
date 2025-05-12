package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class TankEnemy extends Enemy {
    public TankEnemy(World world, Vector2 position) {
        super(world, position);
        this.health = 200;
        this.speed = 0.8f;
    }

    @Override
    public void update(float delta) {
        body.setLinearVelocity(speed, 0);
    }
}
