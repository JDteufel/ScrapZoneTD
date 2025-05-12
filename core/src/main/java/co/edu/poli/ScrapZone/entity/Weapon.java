package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Weapon {
    protected float damage;
    protected float fireRate; // en segundos entre disparos
    protected float cooldown;
    protected Vector2 position;
    protected World world;

    public Weapon(World world, Vector2 position) {
        this.world = world;
        this.position = position;
        this.cooldown = 0;
    }

    public void update(float delta) {
        if (cooldown > 0) {
            cooldown -= delta;
        }
    }

    public boolean canFire() {
        return cooldown <= 0;
    }

    public void tryFire(Vector2 target) {
        if (canFire()) {
            fire(target);
            cooldown = fireRate;
        }
    }

    protected abstract void fire(Vector2 target);
}
