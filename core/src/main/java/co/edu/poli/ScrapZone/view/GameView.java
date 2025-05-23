package co.edu.poli.ScrapZone.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import co.edu.poli.ScrapZone.entity.Enemy;
import co.edu.poli.ScrapZone.entity.Game;
import co.edu.poli.ScrapZone.entity.Material;
import co.edu.poli.ScrapZone.entity.Projectile;
import co.edu.poli.ScrapZone.entity.Weapon;

public class GameView {
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    public GameView(Stage stage) {
        this.stage = stage;
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
    }

    public void render(Game modelo) {
        Gdx.gl.glClearColor(0.3f, 0.2f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderPaths();
        renderEntities(modelo);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void renderEntities(Game modelo) {
        batch.begin();
        font.setColor(Color.RED);
        for (Enemy e : modelo.enemigos)
            font.draw(batch, "🤖", e.getX() - 10, e.getY() + 10);

        font.setColor(Color.BLUE);
        for (Weapon w : modelo.armas)
            font.draw(batch, "🔧", w.getX() - 10, w.getY() + 10);

        font.setColor(Color.YELLOW);
        for (Projectile p : modelo.proyectiles)
            font.draw(batch, "•", p.getX(), p.getY());

        for (Material s : modelo.materiales) {
            switch (s.getType()) {
                case SCREW: font.setColor(Color.GRAY); font.draw(batch, "🔩", s.getX(), s.getY()); break;
                case METAL_PLATE: font.setColor(Color.DARK_GRAY); font.draw(batch, "▬", s.getX(), s.getY()); break;
                case WIRE: font.setColor(Color.YELLOW); font.draw(batch, "〰", s.getX(), s.getY()); break;
            }
        }

        font.setColor(Color.WHITE);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
    }
}
