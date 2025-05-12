package co.edu.poli.ScrapZone.service;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import co.edu.poli.ScrapZone.entity.*;

public class Box2DService implements Disposable {
    private static final Vector2 GRAVITY = new Vector2(0f, 0f);
    private static final float STEP = 1f / 30f;
    private static final float WIDTH = 80f;
    private static final float HEIGHT = 60f;

    private World world;
    private float timeSinceUpdate;
    private final Viewport viewport = new StretchViewport(WIDTH, HEIGHT);

    private final Array<Enemy> enemies = new Array<>();

    public void create() {
        dispose();
        world = new World(GRAVITY, true);
        createWorldBounds();
        spawnEnemies(); // Agregar los enemigos al mundo
    }

    private void createWorldBounds() {
        // Si deseas limitar el área de juego
    }

    private void spawnEnemies() {
        enemies.add(new FastEnemy(world, new Vector2(-10, 10)));
        enemies.add(new TankEnemy(world, new Vector2(-12, 12)));
    }

    public void update(float delta) {
        timeSinceUpdate += delta;

        while (timeSinceUpdate >= STEP) {
            timeSinceUpdate -= STEP;

            world.step(STEP, 6, 2);

            for (int i = enemies.size - 1; i >= 0; i--) {
                Enemy enemy = enemies.get(i);
                enemy.update(STEP);
                if (enemy.isDead()) {
                    world.destroyBody(enemy.getBody());
                    enemies.removeIndex(i);
                }
            }
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public World getWorld() {
        return world;
    }

    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public void dispose() {
        for (Enemy enemy : enemies) {
            if (enemy.getBody() != null) {
                world.destroyBody(enemy.getBody());
            }
        }
        enemies.clear();

        if (world != null) {
            world.dispose();
            world = null;
        }
    }
}
