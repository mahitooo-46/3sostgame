package ru.kursk.threeSost;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;  // ← добавь этот импорт
import com.badlogic.gdx.utils.viewport.FitViewport;

import ru.kursk.threeSost.screens.GameScreen;
import ru.kursk.threeSost.objects.World;

public class MyGame extends Game {
    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 480;

    public OrthographicCamera camera;
    public FitViewport viewport;
    public SpriteBatch batch;         // ← добавлено поле batch
    public FPSLogger fpsLogger;

    public static World world;

    public static void stepWorld() {
        if (world != null) {
            // например: world.step(1f / 60f);
        }
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);

        batch = new SpriteBatch();   // создаем SpriteBatch
        fpsLogger = new FPSLogger();

        world = new World();

        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.6f, 0.9f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fpsLogger.log();
        super.render();
    }

    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
        if (world != null) {
            world.dispose();
        }
        super.dispose();
    }
}
