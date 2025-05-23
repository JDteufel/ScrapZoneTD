package co.edu.poli.ScrapZone.service;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Vector2;
import co.edu.poli.ScrapZone.entity.Enemy;
import co.edu.poli.ScrapZone.entity.FastEnemy;
import co.edu.poli.ScrapZone.entity.TankEnemy;
import co.edu.poli.ScrapZone.entity.BasicEnemy;

public class EntityManager implements Disposable {
    private static final float WORLD_WIDTH = 800f;
    private static final float WORLD_HEIGHT = 600f;

    private final Viewport gameViewport;
    private final Array<Enemy> activeEnemies;

    public EntityManager() {
        this.gameViewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT);
        this.activeEnemies = new Array<>();
    }

    public void prepareLevel() {
        activeEnemies.clear();
    }

    public void update(float delta) {
        for (int i = activeEnemies.size - 1; i >= 0; i--) {
            Enemy enemy = activeEnemies.get(i);
            enemy.update(delta);

            if (enemy.isDead() || enemy.hasReachedEnd()) {
                // Aquí podrías:
                // - Notificar a otro sistema (ej. para dar puntos al jugador si murió)
                // - Notificar a otro sistema (ej. para restar vidas si llegó al final)
                activeEnemies.removeIndex(i);
                // Si los enemigos fueran Disposable (poco común para entidades simples sin Box2D)
                // if (enemy instanceof Disposable) {
                //    ((Disposable) enemy).dispose();
                // }
            }
        }
        // Aquí también podrías añadir lógica para:
        // - Disparar la siguiente oleada de enemigos basada en tiempo o condiciones.
        // - Actualizar otros tipos de entidades si este gestor los maneja.
    }

    public void renderEnemies(SpriteBatch batch) {
        for (Enemy enemy : activeEnemies) {
            enemy.draw(batch);
        }
    }

    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
    }

    public Array<Enemy> getActiveEnemies() {
        return activeEnemies;
    }

    public Viewport getGameViewport() {
        return gameViewport;
    }

    @Override
    public void dispose() {
        // Limpiar la lista de enemigos
        // Si los enemigos individuales tuvieran recursos que necesitaran ser liberados explícitamente
        // y fueran Disposable, podrías iterar y llamarlos aquí, aunque es menos común
        // sin la complejidad de Box2D. Generalmente, el AssetManager se encarga de texturas, etc.
        activeEnemies.clear();
        // No hay 'world' de Box2D que liberar.
        // Si esta clase cargara sus propios assets (poco común, usualmente es el AssetManager o la Screen)
        // se liberarían aquí.
    }
}
