package org.ah.libgdx.rpi.desktop;

import org.ah.libgdx.rpi.RPiLibGDXFreeTypeExample;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class RPiLibGDXFreeTypeDesktopLauncherLwjgl3 {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(640, 480);
        new Lwjgl3Application(new RPiLibGDXFreeTypeExample(), config);
    }
}
