package org.ah.libgdx.rog.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.ah.libgdx.rog.RayOfGod;

public class DesktopLauncherLWJGL {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new RayOfGod(), config);
	}
}
