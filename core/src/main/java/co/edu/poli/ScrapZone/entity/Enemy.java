package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import co.edu.poli.ScrapZone.view.Render;
import co.edu.poli.ScrapZone.view.HealthBar;
import co.edu.poli.ScrapZone.entity.Health;


public abstract class Enemy {
    protected Health health;
    protected Movement movement;
    protected Render render;

    protected int damage;
    protected EnemyType type;

public Enemy(float health, float speed, int damage, float width, float height, EnemyType type) {
    this.health = new Health(health);
    this.movement = new Movement(speed);
    this.render = new Render(width, height);
    this.damage = damage;
    this.type = type;
}

public boolean update(float delta) {
    if (isDead()) {
        return false;
    }
    boolean reachedEnd = movement.update(delta);
    updateSpecific(delta);
    return reachedEnd;
}

protected abstract void updateSpecific(float delta);
public void draw(SpriteBatch batch) {
    if (!isDead()) {
        render.render(batch, getX(), getY());
    }
}

    public class Movement {
        private float x, y;
        public Movement(float speed) {
            this.speed = speed;
            this.x = 0;
            this.y = 0;
        }

        public void setPosition(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }


public void renderHealthBar(SpriteBatch batch, Texture bgTexture, Texture fgTexture) {
    HealthBar.render(batch, health, getX(), getY(),
        render.getWidth(), bgTexture, fgTexture);
}

public void dispose() {
    render.dispose();
}

public void takeDamage(float damage) {
    health.takeDamage(damage);
    onDamaged(damage);
}

public boolean isDead() {
    boolean dead = health.isDead();
    if (dead) onDeath();
    return dead;
}

public float getHealth() { return health.getHealth(); }
public float getMaxHealth() { return health.getMaxHealth(); }

public void setPath(Array<Vector2> path) { movement.setPath(path); }
public boolean hasReachedEnd() { return movement.hasReachedEnd(); }
public float getX() { return movement.getX(); }
public float getY() { return movement.getY(); }
public float getSpeed() { return movement.getSpeed(); }
public void setSpeed(float speed) { movement.setSpeed(speed); }

public Rectangle getBounds() { return render.getBounds(getX(), getY()); }
public boolean contains(float pointX, float pointY) {
    return render.contains(getX(), getY(), pointX, pointY);
}
public void setTexture(Texture texture) { render.setTexture(texture); }
public float getWidth() { return render.getWidth(); }
public float getHeight() { return render.getHeight(); }

public int getDamage() { return damage; }

protected void onDamaged(float damage) {}
protected void onDeath() {}
}

