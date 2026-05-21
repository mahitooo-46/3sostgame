package ru.kursk.threeSost;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.kursk.threeSost.managers.InputManager;
import ru.kursk.threeSost.objects.Player;
import ru.kursk.threeSost.objects.World;

public class GameScreen extends ScreenAdapter {
    private final MyGame game;
    private SpriteBatch batch;
    private Texture texture;
    private World world;
    private Player player;
    private InputManager input;

    public GameScreen(MyGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.texture = new Texture("badlogic.jpg"); // замени на свои спрайты
    }

    @Override
    public void show() {
        world = new World();
        player = new Player(world);
        input = new InputManager(player, (OrthographicCamera) game.viewport.getCamera());

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(input);
        multiplexer.addProcessor(world.getCameraInputProcessor()); // для камеры / тача
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float dt) {
        game.viewport.apply();
        world.step(dt);
        player.update(dt);

        Gdx.gl.glClearColor(0.4f, 0.6f, 0.9f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.viewport.getCamera().update();
        batch.setProjectionMatrix(game.viewport.getCamera().combined);
        batch.begin();

        world.render(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        world.dispose();
    }
}
