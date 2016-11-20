package org.ah.libgdx.rpi.desktop;

import org.ah.libgdx.rpi.RPiLibGDXExample;

import com.badlogic.gdx.backends.jogamp.JoglNewtApplication;
import com.badlogic.gdx.backends.jogamp.JoglNewtApplicationConfiguration;

public class TestDesktopLauncherJogl {
    public static void main(String[] arg) {
        JoglNewtApplicationConfiguration config = new JoglNewtApplicationConfiguration();
        config.width = 640;
        config.height = 480;

        new JoglNewtApplication(new RPiLibGDXExample(), config);
    }
}
