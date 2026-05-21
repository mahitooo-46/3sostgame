package ru.kursk.threeSost.objects;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.Map;

import ru.kursk.threeSost.managers.InputManager;

public class World implements Disposable {
    World world;

    private Texture blockTexture;

    private float lastTimeStep = 1f / 60f;

    public World() {
        this.world = new World(new Vector2(0, -9.81f), true);
        this.bodies = new Array<>();
        this.blockTexture = new Texture("badlogic.jpg");

        createBoundaries();
        createPlatforms();
    }

    public World(Vector2 vector2, boolean b) {

    }

    private void createBoundaries() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;

        // нижняя граница
        Body ground = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(400, 5);
        ground.createFixture(shape, 0f);
        shape.dispose();

        // левая стена
        Body leftWall = world.createBody(def);
        shape = new PolygonShape();
        shape.setAsBox(5, 240);
        leftWall.createFixture(shape, 0f);
        shape.dispose();

        // правая стена
        Body RightWall = world.createBody(def);
        shape = new PolygonShape();
        shape.setAsBox(-5, -240);
        leftWall.createFixture(shape, 0f);
        shape.dispose();

    }

    Body createBody(BodyDef def) {
        return null;
    }

    private void createPlatforms() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;

        float x = 200, y = 120;
        float w = 100, h = 20;

        for (int i = 0; i < 5; i++) {
            Body body = world.createBody(def);
            body.setTransform(x, y, 0);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(w / 2, h / 2);
            body.createFixture(shape, 0f);
            shape.dispose();

            Platform Pзlatform = new Platform(body, x, y, w, h);
            bodies.add(body);
        }
    }

    public void step(float dt) {
        world.step(lastTimeStep, 6, 3);
    }

    private void step(float lastTimeStep, int i, int i1) {
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < world.getBodyCount(); i++) {
            Body body = (Body) world.getBodyList().get(i);
            Vector2 pos = body.getPosition();
            float width = 0, height = 0;

            for (Fixture f : body.getFixtureList()) {
                Shape s = f.getShape();
                if (s instanceof PolygonShape) {
                    PolygonShape poly = (PolygonShape) s;
                    float[] verts = new float[poly.getVertexCount() * 2];
                    for (int j = 0; j < poly.getVertexCount(); j++) {
                        poly.getVertex(j, new Vector2());
                        // очень упрощённый рендер — можно вынести в Tile
                    }
                    batch.draw(blockTexture, pos.x - 10, pos.y - 10, 20, 20);
                }
            }
        }

        // рендер игрока (вместо этого можно передать Player в World)
    }

    private Map<Object, Object> getBodyList() {


        return Map.of();
    }

    private int getBodyCount() {
                return 0;
    }

    private Array<Body> bodies;

    public OrthographicCamera getCamera() {
        // здесь можно вернуть свою камеру или добавить её в World
        return new OrthographicCamera();
    }

    public InputManager getCameraInputProcessor() {
        // пока без полноценного тача камеры, можно расширить
        return null;
    }

    @Override
    public void dispose() {
        world.dispose();
        blockTexture.dispose();
    }

    public com.badlogic.gdx.physics.box2d.World getBox2DWorld() {
        return null;
    }
}
