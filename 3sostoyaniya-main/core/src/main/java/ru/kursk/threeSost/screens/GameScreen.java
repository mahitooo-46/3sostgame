package ru.kursk.threeSost.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.kursk.threeSost.MyGame;
import ru.kursk.threeSost.objects.Player;
import ru.kursk.threeSost.objects.World;

import static ru.kursk.threeSost.GameSettings.*;
import static ru.kursk.threeSost.GameResources.*;

public class GameScreen extends ScreenAdapter {
    private final MyGame myGdxGame;
    private Player playerObject;

    public GameScreen(MyGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        playerObject = new Player(
            SCREEN_WIDTH / 2,
            100,
            PLAYER_WIDTH,
            PLAYER_HEIGHT,
            PLAYER_IMG_PATH,
            myGdxGame.world
        );
    }

    @Override
    public void render(float delta) {
        MyGame.stepWorld();
        draw();
    }

    @Override
    public void dispose() {
        playerObject.dispose();
    }

    private void draw() {
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        myGdxGame.batch.begin();
        playerObject.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }
}
