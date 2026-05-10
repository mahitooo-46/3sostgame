package кru.kursk3sost;

import com.badlogic.gdx.math.Rectangle;

public class Player {
    public static final float WIDTH = 30;
    public static final float HEIGHT = 30;
    public static final float GRAVITY = 0.6f;

    private float x, y;
    private float velX, velY;
    private boolean onGround;
    private boolean leftPressed, rightPressed, jumpPressed;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.velX = 0;
        this.velY = 0;
        this.onGround = false;
        resetInput();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void resetInput() {
        leftPressed = false;
        rightPressed = false;
        jumpPressed = false;
    }

    // Геттеры и сеттеры
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
    public float getVelX() { return velX; }
    public void setVelX(float velX) { this.velX = velX; }
    public float getVelY() { return velY; }
    public void setVelY(float velY) { this.velY = velY; }
    public boolean isOnGround() { return onGround; }
    public void setOnGround(boolean onGround) { this.onGround = onGround; }
    public boolean isLeftPressed() { return leftPressed; }
    public void setLeftPressed(boolean leftPressed) { this.leftPressed = leftPressed; }
    public boolean isRightPressed() { return rightPressed; }
    public void setRightPressed(boolean rightPressed) { this.rightPressed = rightPressed; }
    public boolean isJumpPressed() { return jumpPressed; }
    public void setJumpPressed(boolean jumpPressed) { this.jumpPressed = jumpPressed; }
}
