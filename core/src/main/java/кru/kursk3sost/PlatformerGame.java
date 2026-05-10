package кru.kursk3sost;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

public class PlatformerGame extends Game {
    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        if (getScreen() != null) {
            getScreen().dispose();
        }
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    private class GameScreen implements Screen {
        public GameScreen(PlatformerGame platformerGame) {
        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta) {

        }

        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {

        }
    }
}
