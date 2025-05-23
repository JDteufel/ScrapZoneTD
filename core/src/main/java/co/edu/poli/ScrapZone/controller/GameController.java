package co.edu.poli.ScrapZone.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.czyzby.autumn.annotation.Inject;
import com.github.czyzby.autumn.mvc.component.ui.controller.ViewRenderer;
import com.github.czyzby.autumn.mvc.component.ui.controller.ViewResizer;
import com.github.czyzby.autumn.mvc.component.ui.controller.impl.StandardViewShower;
import com.github.czyzby.autumn.mvc.stereotype.View;
import co.edu.poli.ScrapZone.service.EntityManager;

/**
 * Renders Box2D world.
 */
@View(id = "game", value = "ui/templates/game.lml", themes = "music/theme.ogg")
public class GameController extends StandardViewShower implements ViewResizer, ViewRenderer {
    @Inject private EntityManager box2d;
    private final Box2DDebugRenderer renderer = new Box2DDebugRenderer();

    @Override
    public void show(final Stage stage, final Action action) {
        box2d.create();
        super.show(stage, Actions.sequence(action, Actions.run(new Runnable() {
            @Override
            public void run() {
                final InputMultiplexer inputMultiplexer = new InputMultiplexer(stage);
                Gdx.input.setInputProcessor(inputMultiplexer);
            }
        })));
    }

    @Override
    public void resize(final Stage stage, final int width, final int height) {
        box2d.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(final Stage stage, final float delta) {
        box2d.update(delta);
        renderer.render(box2d.getWorld(), box2d.getViewport().getCamera().combined);
        stage.act(delta);
        stage.draw();
    }
}
