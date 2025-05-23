package co.edu.poli.ScrapZone.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import co.edu.poli.ScrapZone.entity.Health;

public class Render {
        private Texture texture;
        private float width, height;

        public Render(float width, float height) {
            this.width = width;
            this.height = height;
        }

        public void render(SpriteBatch batch, float x, float y) {
            if (texture != null) {
                batch.draw(texture,
                    x - width/2, y - height/2,
                    width, height
                );
            }
        }

        public Rectangle getBounds(float x, float y) {
            return new Rectangle(x - width/2, y - height/2, width, height);
        }

        public boolean contains(float x, float y, float pointX, float pointY) {
            return getBounds(x, y).contains(pointX, pointY);
        }

        public void dispose() {
            if (texture != null) {
                texture.dispose();
            }
        }

        public float getWidth() { return width; }
        public float getHeight() { return height; }
        public Texture getTexture() { return texture; }
        public void setTexture(Texture texture) { this.texture = texture; }
    }

