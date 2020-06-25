package org.ah.libgdx.rpi.desktop;

import org.ah.libgdx.rpi.RPiLibGDXBox2dExample;

import com.badlogic.gdx.backends.jogamp.JoglNewtApplication;
import com.badlogic.gdx.backends.jogamp.JoglNewtApplicationConfiguration;

public class RPiLibGDXBox2DDesktopLauncherJogl {
    public static void main(String[] arg) {
        JoglNewtApplicationConfiguration config = new JoglNewtApplicationConfiguration();
        config.width = 640;
        config.height = 480;

        new JoglNewtApplication(new RPiLibGDXBox2dExample(), config);
    }
}
