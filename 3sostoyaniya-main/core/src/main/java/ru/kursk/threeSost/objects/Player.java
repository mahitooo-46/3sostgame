package ru.kursk.threeSost.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Player {
    private final World world;
    private Body body;
    private Sprite sprite;
    private float moveSpeed = 3f;
    private float jumpImpulse = 8f;

    // ОСНОВНОЙ конструктор — под GameScreen
    public Player(
        int x, int y,
        int width, int height,
        String imagePath,
        World world
    ) {
        this.world = world;
        this.sprite = new Sprite(new Texture(imagePath));
        sprite.setSize(width, height);
        createBody(x, y);
    }

    // Второй конструктор — если хочешь использовать только World
    public Player(World world) {
        this.world = world;
        this.sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setSize(32, 32); // примерный размер
        createBody(100, 150);
    }

    private void createBody(int x, int y) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);

        body = world.world.createBody(def); // world.world — твой Box2D-мир

        CircleShape shape = new CircleShape();
        shape.setRadius(sprite.getWidth() / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.1f;
        fixtureDef.friction = 0.5f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void update(float dt) {
        Vector2 vel = body.getLinearVelocity();

        float moveX = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  moveX -= moveSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveX += moveSpeed;
        body.setLinearVelocity(moveX, vel.y);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (onGround()) {
                body.applyLinearImpulse(new Vector2(0, jumpImpulse), body.getPosition(), true);
            }
        }

        Vector2 pos = body.getPosition();
        sprite.setPosition(pos.x - sprite.getWidth() / 2f, pos.y - sprite.getHeight() / 2f);
    }

    private boolean onGround() {
        com.badlogic.gdx.physics.box2d.World box2dWorld = world.getBox2DWorld();
        for (Contact contact : box2dWorld.getContactList()) {
            if (contact.isTouching()) {
                Fixture fa = contact.getFixtureA();
                Fixture fb = contact.getFixtureB();
                if (fa.getBody() == body || fb.getBody() == body) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void moveLeft()  { body.setLinearVelocity(-moveSpeed, body.getLinearVelocity().y); }
    public void moveRight() { body.setLinearVelocity(moveSpeed, body.getLinearVelocity().y); }
    public void jump() {
        if (onGround()) {
            body.applyLinearImpulse(new Vector2(0, jumpImpulse), body.getPosition(), true);
        }
    }

    public void dispose() {
        // если нужно, удалять тело, шейп и т.п.
    }
}
