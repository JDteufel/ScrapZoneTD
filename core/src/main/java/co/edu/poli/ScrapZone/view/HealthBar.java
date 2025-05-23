package co.edu.poli.ScrapZone.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import co.edu.poli.ScrapZone.entity.Health;

public class HealthBar {
    public void render(SpriteBatch batch, Health health,
                       float x, float y, float width,
                       Texture backgroundTexture, Texture foregroundTexture) {
        if (health.isDead()) return;

        float barHeight = 4f;
        float barX = x - width/2;
        float barY = y + width/2 + 5f;

        batch.draw(backgroundTexture, barX, barY, width, barHeight);

        float healthPercentage = health.getHealthPercentage();
        batch.draw(foregroundTexture, barX, barY, width * healthPercentage, barHeight);
    }
}
