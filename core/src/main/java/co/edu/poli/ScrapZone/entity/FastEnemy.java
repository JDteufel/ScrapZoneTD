package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class FastEnemy extends Enemy {
    public FastEnemy(World world, Vector2 position) {
        super(world, position);
        this.health = 50;
        this.speed = 2.5f;
    }

    @Override
    public void update(float delta) {
        body.setLinearVelocity(speed, 0);
    }
}
