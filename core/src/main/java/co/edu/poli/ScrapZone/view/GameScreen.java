package co.edu.poli.ScrapZone.view;

import co.edu.poli.ScrapZone.controller.GameController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {

    private final GameController gameController;
    private final Stage stage;

    public GameScreen() {
        this.stage = new Stage(new ScreenViewport());
        this.gameController = new GameController();
        this.gameController.show(stage, null);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.8f, 1f); // Azul en lugar de negro
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        gameController.render(stage, delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        gameController.resize(stage, width, height);
    }

    @Override
    public void pause() {
        // Puedes pausar el juego aquí si lo necesitas
    }

    @Override
    public void resume() {
        // Retomar después de pausa
    }

    @Override
    public void hide() {
        // Llamado cuando se cambia a otra pantalla
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
