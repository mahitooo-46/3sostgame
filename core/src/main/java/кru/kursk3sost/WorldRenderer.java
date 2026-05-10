package кru.kursk3sost;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
    private World world;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont font;

    // Свои константы для UI (избегаем зависимости от GameScreen)
    private static final float SCREEN_WIDTH = 800;
    private static final float SCREEN_HEIGHT = 600;

    public WorldRenderer(World world) {
        this.world = world;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(1.5f);
    }

    public void render(Camera camera) {
        renderGameObjects(camera);
        renderUI();
    }

    private void renderGameObjects(Camera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Filled);

        // Рисуем платформы (коричневые)
        shapeRenderer.setColor(new Color(0.4f, 0.3f, 0.2f, 1)); // Коричневый
        for (Platform platform : world.getPlatforms()) {
            shapeRenderer.rect(platform.getX(), platform.getY(),
                platform.getWidth(), platform.getHeight());
        }

        // Рисуем монеты (желтые)
        for (Coin coin : world.getCoins()) {
            if (!coin.isCollected()) {
                shapeRenderer.setColor(Color.YELLOW);
                shapeRenderer.circle(coin.getX() + Coin.SIZE/2,
                    coin.getY() + Coin.SIZE/2,
                    Coin.SIZE/2);
            }
        }

        // Рисуем игрока (синий)
        Player player = world.getPlayer();
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(player.getX(), player.getY(), Player.WIDTH, Player.HEIGHT);

        // Рисуем глазки игрока
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(player.getX() + 22, player.getY() + 12, 4);
        shapeRenderer.circle(player.getX() + 8, player.getY() + 12, 4);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(player.getX() + 23, player.getY() + 13, 2);
        shapeRenderer.circle(player.getX() + 9, player.getY() + 13, 2);

        shapeRenderer.end();
    }

    private void renderUI() {
        batch.begin();

        // Счет монет
        font.setColor(Color.BLACK); // Черный текст
        font.draw(batch, "Монеты: " + world.getScore() + " / " + world.getTotalCoins(),
            20, SCREEN_HEIGHT - 20);

        // Сообщение о победе/поражении
        if (world.isGameFinished()) {
            String message;
            if (world.isGameWin()) {
                message = "ПОБЕДА! Нажми R для рестарта";
            } else {
                message = "ВЫ УПАЛИ! Нажми R для рестарта";
            }
            font.setColor(Color.RED);
            font.draw(batch, message, SCREEN_WIDTH/2 - 150, SCREEN_HEIGHT/2);
        }

        batch.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
