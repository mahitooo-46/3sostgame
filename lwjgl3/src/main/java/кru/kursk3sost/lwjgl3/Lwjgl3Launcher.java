package кru.kursk3sost.lwjgl3;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Платформер - Собери монеты");
        config.setWindowedMode(800, 600);
        config.setForegroundFPS(60);
        config.setResizable(false);
        new Lwjgl3Application(new PlatformerGame(), config);
    }

    private static class PlatformerGame implements ApplicationListener {
        @Override
        public void create() {

        }

        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void render() {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void dispose() {

        }
    }
}
