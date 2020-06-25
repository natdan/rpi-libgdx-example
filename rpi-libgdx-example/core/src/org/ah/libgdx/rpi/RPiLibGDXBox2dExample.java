package org.ah.libgdx.rpi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class RPiLibGDXBox2dExample extends ApplicationAdapter implements InputProcessor {
    SpriteBatch batch;
    Texture img;
    RayHandler rayHandler;
    float angle = 0f;
    PointLight light;
    float radius = 1f;
    
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        World world = new World(new Vector2(0, 0), true);
        rayHandler = new RayHandler(world);
        light = new PointLight(rayHandler, 10, new Color(1, 1, 1, 1), 10, 3, 4);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        angle = angle + 0.1f;
        if (angle > 360f) { angle = angle - 360f; }
        
        float x = (float)(radius * Math.sin(angle));
        float y = (float)(radius * Math.cos(angle));
        
        light.setPosition(x, y);
        
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        // rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        rayHandler.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.ESCAPE) {
            System.exit(0);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}
