package кru.kursk3sost;

import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;

public class World {
    private Player player;
    private ArrayList<Platform> platforms;
    private ArrayList<Coin> coins;
    private int score;
    private boolean gameFinished;
    private boolean gameWin;

    // Константы
    public static final float SCREEN_WIDTH = 800;
    public static final float SCREEN_HEIGHT = 600;

    public World() {
        initWorld();
    }

    private void initWorld() {
        player = new Player(50, 500);
        platforms = new ArrayList<>();
        coins = new ArrayList<>();
        score = 0;
        gameFinished = false;
        gameWin = false;

        createLevel();
    }

    private void createLevel() {
        // Земля
        platforms.add(new Platform(0, 550, 800, 20));
        // Платформы
        platforms.add(new Platform(150, 470, 100, 20));
        platforms.add(new Platform(350, 400, 100, 20));
        platforms.add(new Platform(550, 330, 100, 20));
        platforms.add(new Platform(250, 250, 100, 20));
        platforms.add(new Platform(550, 170, 100, 20));

        // Монеты
        coins.add(new Coin(200, 430));
        coins.add(new Coin(390, 360));
        coins.add(new Coin(590, 290));
        coins.add(new Coin(290, 210));
        coins.add(new Coin(590, 130));
    }

    public void update(float delta) {
        if (gameFinished) return;

        // Горизонтальное движение
        if (player.isLeftPressed()) {
            player.setVelX(-5);
        } else if (player.isRightPressed()) {
            player.setVelX(5);
        } else {
            player.setVelX(0);
        }

        // Гравитация
        player.setVelY(player.getVelY() + Player.GRAVITY);

        // Временное перемещение
        float newX = player.getX() + player.getVelX();
        float newY = player.getY() + player.getVelY();

        // Проверка столкновений по X
        player.setX(newX);
        checkCollisions(true);

        // Проверка столкновений по Y
        player.setY(newY);
        checkCollisions(false);

        // Прыжок
        if (player.isJumpPressed() && player.isOnGround() && !gameFinished) {
            player.setVelY(-10);
            player.setOnGround(false);
            player.setJumpPressed(false);
        }

        // Сбор монет
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            if (!coin.isCollected() && player.getBounds().overlaps(coin.getBounds())) {
                coin.setCollected(true);
                score++;
            }
        }

        // Проверка победы
        boolean allCollected = true;
        for (Coin coin : coins) {
            if (!coin.isCollected()) {
                allCollected = false;
                break;
            }
        }

        if (allCollected && !gameFinished) {
            gameFinished = true;
            gameWin = true;
        }

        // Проверка падения
        if (player.getY() > SCREEN_HEIGHT) {
            gameFinished = true;
            gameWin = false;
        }
    }

    private void checkCollisions(boolean isXAxis) {
        Rectangle playerBounds = player.getBounds();

        for (Platform platform : platforms) {
            if (playerBounds.overlaps(platform.getBounds())) {
                if (isXAxis) {
                    // Отталкивание по X
                    if (player.getVelX() > 0) {
                        player.setX(platform.getX() - Player.WIDTH);
                    } else if (player.getVelX() < 0) {
                        player.setX(platform.getX() + platform.getWidth());
                    }
                    playerBounds = player.getBounds();
                } else {
                    // Отталкивание по Y
                    if (player.getVelY() > 0) {
                        player.setY(platform.getY() - Player.HEIGHT);
                        player.setVelY(0);
                        player.setOnGround(true);
                        playerBounds = player.getBounds();
                    } else if (player.getVelY() < 0) {
                        player.setY(platform.getY() + platform.getHeight());
                        player.setVelY(0);
                        playerBounds = player.getBounds();
                    }
                }
            }
        }
    }

    public void restart() {
        initWorld();
        gameFinished = false;
        gameWin = false;
        player.resetInput();
    }

    // Геттеры
    public Player getPlayer() { return player; }
    public ArrayList<Platform> getPlatforms() { return platforms; }
    public ArrayList<Coin> getCoins() { return coins; }
    public int getScore() { return score; }
    public boolean isGameFinished() { return gameFinished; }
    public boolean isGameWin() { return gameWin; }
    public int getTotalCoins() { return coins.size(); }
}
