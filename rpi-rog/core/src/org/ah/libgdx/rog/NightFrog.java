package org.ah.libgdx.rog;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class NightFrog extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private Texture img;
    private FrameBuffer textureFrameBuffer;
    private Texture bufferTexture;
    private ShaderProgram shaderProgram;
    // private ShaderProgram shaderProgram2;
    private Mesh mesh;
    private float[] mousePosition = new float[2];


    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("picture.png");

        textureFrameBuffer = new FrameBuffer(Format.RGBA8888, img.getWidth(), img.getHeight(), false);

        bufferTexture = textureFrameBuffer.getColorBufferTexture();

        shaderProgram = new ShaderProgram(Gdx.files.internal("rog.vs"), Gdx.files.internal("rog.fs"));
        // shaderProgram = new ShaderProgram(Gdx.files.internal("image.vs"), Gdx.files.internal("image.fs"));

        // mesh = createRect(0, 0, img.getWidth(), img.getHeight());
        mesh = createRect(0, 0, img.getWidth(), img.getHeight());

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        textureFrameBuffer.begin();
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
        textureFrameBuffer.end();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE0);
        bufferTexture.bind();

        shaderProgram.begin();
        shaderProgram.setUniformi("u_image", 0);
        shaderProgram.setUniform2fv("u_mouse_position", mousePosition , 0, 2);
        System.out.println("mousePosition:" + mousePosition[0] + "," + mousePosition[1]);

        mesh.render(shaderProgram, GL20.GL_TRIANGLES);
//        batch.begin();
//        batch.setShader(shaderProgram);
//        batch.draw(bufferTexture, 0, 0);
//        batch.end();
        shaderProgram.end();

    }

    protected Mesh createRect(float x, float y, float width, float height) {
        Mesh mesh = new Mesh(true, 4, 6,
                VertexAttribute.Position(),
                VertexAttribute.ColorUnpacked(),
                VertexAttribute.TexCoords(0));

        mesh.setVertices(new float[] {
                -1, -1, 0,                  1, 1, 1, 1, 0, 0,
                 1, -1, 0,          1, 1, 1, 1, 1, 0,
                 1,  1, 0, 1, 1, 1, 1, 1, 1,
                -1,  1, 0,         1, 1, 1, 1, 0, 1
           });
//        mesh.setVertices(new float[] {
//                x, y, 0,                  1, 1, 1, 1, 0, 0,
//                x + width, y, 0,          1, 1, 1, 1, 1, 0,
//                x + width, y + height, 0, 1, 1, 1, 1, 1, 1,
//                x, y + height, 0,         1, 1, 1, 1, 0, 1
//           });

        mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
        return mesh;
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
        mousePosition[0] = ((float)screenX) / img.getWidth();
        mousePosition[1] = ((float)(img.getHeight() - screenY)) / img.getHeight();
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
