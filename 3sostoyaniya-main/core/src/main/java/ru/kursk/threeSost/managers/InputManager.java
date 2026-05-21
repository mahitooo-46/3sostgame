package ru.kursk.threeSost.managers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ru.kursk.threeSost.objects.Player;

public class InputManager extends InputAdapter {
    private Player player;
    private OrthographicCamera cam;
    private Rectangle leftBtn, rightBtn, jumpBtn;

    public InputManager(Player player, OrthographicCamera cam) {
        this.player = player;
        this.cam = cam;

        // пример кнопок внизу экрана
        float w = cam.viewportWidth / 3;
        float h = 80;
        float y = 10;

        leftBtn  = new Rectangle(10, y, w, h);
        rightBtn = new Rectangle(10 + w, y, w, h);
        jumpBtn  = new Rectangle(10 + 2 * w, y, w, h);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldPos = cam.unproject(new Vector3(screenX, screenY, 0));

        if (leftBtn.contains(worldPos.x, worldPos.y)) {
            player.moveLeft();
        }
        if (rightBtn.contains(worldPos.x, worldPos.y)) {
            player.moveRight();
        }
        if (jumpBtn.contains(worldPos.x, worldPos.y)) {
            player.jump();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // если нужно, сбросить скорость по X
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == com.badlogic.gdx.Input.Keys.LEFT)  player.moveLeft();
        if (keycode == com.badlogic.gdx.Input.Keys.RIGHT) player.moveRight();
        if (keycode == com.badlogic.gdx.Input.Keys.SPACE) player.jump();
        return true;
    }
}
