package org.ah.libgdx.rog;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.TimeUtils;

public class RayOfGod extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private Texture texture;
    private FrameBuffer textureFrameBuffer;
    private Texture bufferTexture;
    private ShaderProgram shaderProgram;
    private Mesh mesh;
    private float[] mousePosition = new float[2];
    private BitmapFont font;
    private long lastTimeCounted;
    private float sinceChange;
    private float frameRate;

    @Override
    public void create () {
        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        
        font = new BitmapFont();
        batch = new SpriteBatch();
        texture = new Texture("picture.png");

        textureFrameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, texture.getWidth(), texture.getHeight(), false);

        bufferTexture = textureFrameBuffer.getColorBufferTexture();

        shaderProgram = new ShaderProgram(Gdx.files.internal("rog.vs"), Gdx.files.internal("rog.fs"));

        mesh = createRect(0, 0, texture.getWidth(), texture.getHeight());

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render () {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if (sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
        
        textureFrameBuffer.begin();
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 0, 0);
        font.draw(batch, (int) frameRate + " fps", 3, Gdx.graphics.getHeight() - 3);
        batch.end();
        textureFrameBuffer.end();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE0);
        bufferTexture.bind();

        shaderProgram.begin();
        shaderProgram.setUniformi("u_image", 0);
        shaderProgram.setUniform2fv("u_mouse_position", mousePosition , 0, 2);
//        System.out.println("mousePosition:" + mousePosition[0] + "," + mousePosition[1]);

        mesh.render(shaderProgram, GL20.GL_TRIANGLES);
        shaderProgram.end();

    }

    protected Mesh createRect(float x, float y, float width, float height) {
        Mesh mesh = new Mesh(true, 4, 6,
                VertexAttribute.Position(),
                VertexAttribute.ColorUnpacked(),
                VertexAttribute.TexCoords(0));

        mesh.setVertices(new float[] {
                -1, -1, 0,         1, 1, 1, 1, 0, 0,
                 1, -1, 0,         1, 1, 1, 1, 1, 0,
                 1,  1, 0,         1, 1, 1, 1, 1, 1,
                -1,  1, 0,         1, 1, 1, 1, 0, 1
        });

        mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
        return mesh;
    }

    @Override
    public void dispose () {
        batch.dispose();
        texture.dispose();
        textureFrameBuffer.dispose();
        bufferTexture.dispose();
        shaderProgram.dispose();
        mesh.dispose();
        font.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition[0] = ((float)screenX) / texture.getWidth();
        mousePosition[1] = ((float)(texture.getHeight() - screenY)) / texture.getHeight();
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
