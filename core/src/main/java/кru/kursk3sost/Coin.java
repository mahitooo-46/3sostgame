package кru.kursk3sost;

import com.badlogic.gdx.math.Rectangle;

public class Coin {
    private float x, y;
    private boolean collected;
    public static final float SIZE = 12;

    public Coin(float x, float y) {
        this.x = x;
        this.y = y;
        this.collected = false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    // Геттеры и сеттеры
    public float getX() { return x; }
    public float getY() { return y; }
    public boolean isCollected() { return collected; }
    public void setCollected(boolean collected) { this.collected = collected; }
}
