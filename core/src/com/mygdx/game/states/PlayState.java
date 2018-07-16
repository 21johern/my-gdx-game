package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Controller;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Player;

import javax.swing.JOptionPane;

public class PlayState extends State{
    OrthogonalTiledMapRenderer renderer;
    private TiledMap map;
    private Player player;
    private Controller controller;
    private ShapeRenderer shapeRenderer;
    public static final String TAG = PlayState.class.getName();

    public World world;
    public Box2DDebugRenderer debugRenderer;





    // Creates lava objects from map then fetches the ones specifically in the lava layer.
//    public TiledMapTileLayer lavaCollider;
//    public MapObjects lavaObjects;
//
//    public TiledMapTileLayer waterCollider;
//    public MapObjects waterObjects;
//
//    public TiledMapTileLayer chestCollider;
//    public MapObjects chestObjects;
//
//    public TiledMapTileLayer doorCollider;
//    public MapObjects doorObjects;
//
//    public TiledMapTileLayer leftWallCollider;
//    public MapObjects leftWallObjects;
//
//    public TiledMapTileLayer rightWallCollider;
//    public MapObjects rightWallObjects;
//
//    public TiledMapTileLayer ceilingCollider;
//    public MapObjects ceilingObjects;
//
//    public TiledMapTileLayer floorCollider;
//    public MapObjects floorObjects;


    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        // Change image to the correct background once we have it
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);

        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        player = new Player(224, 320, this);



        // Change image to the correct background once we have it
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);


        map = new TmxMapLoader().load("MenuMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        shapeRenderer = new ShapeRenderer();
        Gdx.app.log(TAG, "Application Listener Created");
        controller = new Controller();

        // Gets Lava objects and draws polygons.
//        lavaCollider = (TiledMapTileLayer)map.getLayers().get("Lava");
//        lavaObjects = lavaCollider.getObjects();
//
//        waterCollider = (TiledMapTileLayer)map.getLayers().get("Water");
//        waterObjects = waterCollider.getObjects();
//
//        chestCollider = (TiledMapTileLayer)map.getLayers().get("Chest");
//        chestObjects = chestCollider.getObjects();
//
//        doorCollider = (TiledMapTileLayer)map.getLayers().get("Exit");
//        doorObjects = doorCollider.getObjects();
//
//        leftWallCollider = (TiledMapTileLayer)map.getLayers().get("leftWall");
//        leftWallObjects = leftWallCollider.getObjects();
//
//        rightWallCollider = (TiledMapTileLayer)map.getLayers().get("rightWall");
//        rightWallObjects = rightWallCollider.getObjects();
//
//        ceilingCollider = (TiledMapTileLayer)map.getLayers().get("Ceiling");
//        ceilingObjects = rightWallCollider.getObjects();
//
//        floorCollider = (TiledMapTileLayer)map.getLayers().get("Ground");
//        floorObjects = floorCollider.getObjects();

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        if(controller.isUpPressed()) {
            player.jump();
        } else if(controller.isDownPressed()) {

        } else if(controller.isLeftPressed()) {
            player.walkLeft();
        } else if(controller.isRightPressed()) {
            player.walkRight();
        }
        player.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        debugRenderer.render(world, cam.combined);

        renderer.setView(cam);
        renderer.render();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(player.getTexture(),player.getPosition().x,player.getPosition().y,player.getWidth(), player.getHeight());
        sb.end();
        controller.draw();

        world.step(1/60f, 6, 2);
    }

    @Override
    public void dispose() {

    }
}