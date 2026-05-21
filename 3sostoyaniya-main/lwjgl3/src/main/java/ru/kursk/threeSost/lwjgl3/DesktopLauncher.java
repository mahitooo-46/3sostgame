package ru.kursk.threeSost.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import ru.kursk.threeSost.MyGame;


public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(MyGame.V_WIDTH, MyGame.V_HEIGHT);
        config.setTitle("Platformer (Box2D)");
        new Lwjgl3Application(new MyGame(), config);
    }
}
