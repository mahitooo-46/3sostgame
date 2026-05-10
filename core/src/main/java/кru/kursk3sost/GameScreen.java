package кru.kursk3sost;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private final PlatformerGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private World world;
    private WorldRenderer renderer;
    private GameInputProcessor inputProcessor;

    // Константы экрана
    public static final float SCREEN_WIDTH = 800;
    public static final float SCREEN_HEIGHT = 600;

    public GameScreen(PlatformerGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
        camera.update(); // ВАЖНО: обновляем камеру

        world = new World();
        renderer = new WorldRenderer(world);
        inputProcessor = new GameInputProcessor(world);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        // Обновление логики
        world.update(delta);

        // ОЧИСТКА ЭКРАНА - здесь задается цвет фона!
        // RGB значения: 0.2f = 20% красного, 0.7f = 70% зеленого, 0.8f = 80% синего = голубой
        Gdx.gl.glClearColor(0.2f, 0.7f, 0.8f, 1); // Голубой фон как небо
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Отрисовка
        camera.update();
        renderer.render(camera);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}
