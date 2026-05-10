package кru.kursk3sost;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {
    private World world;

    public GameInputProcessor(World world) {
        this.world = world;
    }

    @Override
    public boolean keyDown(int keycode) {
        Player player = world.getPlayer();

        switch (keycode) {
            case Keys.LEFT:
            case Keys.A:
                player.setLeftPressed(true);
                break;
            case Keys.RIGHT:
            case Keys.D:
                player.setRightPressed(true);
                break;
            case Keys.SPACE:
                player.setJumpPressed(true);
                break;
            case Keys.R:
                world.restart();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        Player player = world.getPlayer();

        switch (keycode) {
            case Keys.LEFT:
            case Keys.A:
                player.setLeftPressed(false);
                break;
            case Keys.RIGHT:
            case Keys.D:
                player.setRightPressed(false);
                break;
            case Keys.SPACE:
                player.setJumpPressed(false);
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Для Android можно добавить управление через касание
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override
    public boolean scrolled(float amountX, float amountY) { return false; }
}
