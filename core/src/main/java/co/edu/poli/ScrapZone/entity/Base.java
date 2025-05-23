package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Base {
    private float health;
    private final float maxHealth;
    private float x, y;
    private float width, height;

    private Texture texture;
    private Texture criticalTexture;

    private boolean isDestroyed = false;

    private static final float CRITICAL_HEALTH_PERCENTAGE = 0.25f;

    public Base(float x, float y, float width, float height, float maxHealth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void takeDamage(int damage) {
        if (isDestroyed) return;

        health -= damage;
        if (health <= 0) {
            health = 0;
            isDestroyed = true;
            onDestroyed();
        } else {
            onDamaged(damage);
        }
    }

    public void update(float delta) {
        if (isDestroyed) return;

        updateEffects(delta);
    }

    public void render(SpriteBatch batch) {
        Texture currentTexture = getCurrentTexture();
        if (currentTexture != null) {
            batch.draw(currentTexture, x - width/2, y - height/2, width, height);
        }

        renderEffects(batch);
    }

    public void renderHealthBar(SpriteBatch batch, Texture bgTexture, Texture fgTexture) {
        if (isDestroyed) return;

        float barWidth = width;
        float barHeight = 8f;
        float barX = x - barWidth/2;
        float barY = y + height/2 + 10f;

        batch.draw(bgTexture, barX, barY, barWidth, barHeight);

        float healthPercentage = getHealthPercentage();

        if (healthPercentage <= CRITICAL_HEALTH_PERCENTAGE) {
            batch.setColor(1f, 0.2f, 0.2f, 1f);
        } else {
            batch.setColor(0.2f, 1f, 0.2f, 1f);
        }

        batch.draw(fgTexture, barX, barY, barWidth * healthPercentage, barHeight);
        batch.setColor(1f, 1f, 1f, 1f);
    }

    public Rectangle getBounds() {
        return new Rectangle(x - width/2, y - height/2, width, height);
    }

    public boolean contains(float pointX, float pointY) {
        return getBounds().contains(pointX, pointY);
    }

    public boolean isCollidingWith(Enemy enemy) {
        return getBounds().overlaps(enemy.getBounds());
    }


    private Texture getCurrentTexture() {
        if (isDestroyed) {
            return criticalTexture; // Mostrar base destruida
        }

        float healthPercentage = getHealthPercentage();
        if (healthPercentage <= CRITICAL_HEALTH_PERCENTAGE && criticalTexture != null) {
            return criticalTexture;
        } else {
            return texture;
        }
    }

    private void onDamaged(int damage) {
        // Aquí puedes agregar efectos como:
        // - Reproducir sonido
        // - Crear partículas
        // - Hacer que la base tiemble
        // - Mostrar texto de daño
    }

    private void onDestroyed() {
        // Efectos de destrucción:
        // - Sonido de explosión
        // - Efectos de partículas
        // - Cambiar música
        // - Activar screen de Game Over
    }

    protected void updateEffects(float delta) {
        // Implementación por defecto vacía
        // Aquí podrías agregar:
        // - Animaciones de idle
        // - Efectos de humo cuando está dañada
        // - Luces parpadeantes cuando está crítica
    }

    /**
     * Renderiza efectos adicionales (se puede sobreescribir)
     */
    protected void renderEffects(SpriteBatch batch) {
        // Implementación por defecto vacía
        // Aquí podrías renderizar:
        // - Partículas de humo
        // - Efectos de chispas
        // - Aura de advertencia cuando está crítica
    }

    /**
     * Libera recursos
     */
    public void dispose() {
        if (texture != null) texture.dispose();
        if (criticalTexture != null) criticalTexture.dispose();
    }

    // ==================== GETTERS ====================
    public float getHealth() { return health; }
    public float getMaxHealth() { return maxHealth; }
    public float getHealthPercentage() { return health / maxHealth; }
    public boolean isDestroyed() { return isDestroyed; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }

    // ==================== SETTERS PARA TEXTURAS ====================
    public void setTexture(Texture texture) { this.texture = texture; }
    public void setCriticalTexture(Texture criticalTexture) { this.criticalTexture = criticalTexture; }
}
