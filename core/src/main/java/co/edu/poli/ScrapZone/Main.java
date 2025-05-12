package co.edu.poli.ScrapZone;

import co.edu.poli.ScrapZone.view.GameScreen;
import com.badlogic.gdx.Game;

public class Main extends Game {
    public static final int WIDTH = 450, HEIGHT = 600;

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
