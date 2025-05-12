package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ScrewGun extends Weapon {

    public ScrewGun(World world, Vector2 position) {
        super(world, position);
        this.damage = 10f;
        this.fireRate = 0.7f;
    }

    @Override
    protected void fire(Vector2 target) {

    }
}
