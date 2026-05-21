package ru.kursk.threeSost.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Platform{
    private final Body body;
    private final float x, y, width, height;
    private Sprite sprite;

    public  Platform (Body body, float x, float y, float width, float height) {
        this.body = body;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setPosition(x - width / 2, y - height / 2);
        sprite.setSize(width, height);
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Body getBody() { return body; }
}
