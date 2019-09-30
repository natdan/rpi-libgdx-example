package org.ah.libgdx.rog.desktop;

import com.badlogic.gdx.backends.jogamp.JoglNewtApplication;
import com.badlogic.gdx.backends.jogamp.JoglNewtApplicationConfiguration;
import org.ah.libgdx.rog.RayOfGod;

public class DesktopLauncherJOGL {
	public static void main (String[] args) {

		int width = 640;
		int height = 480;

		if (args.length > 0) {
			int i = args[0].indexOf("x");
			if (i > 0) {
				width = Integer.parseInt(args[0].substring(0, i));
				height = Integer.parseInt(args[0].substring(i + 1));
			}
		}

		System.out.println("Setting up display as " + width + "x" + height);
		System.out.println("Use <width>x<height> as parameter to change it");

		JoglNewtApplicationConfiguration config = new JoglNewtApplicationConfiguration();
		config.useGL30 = true;
		config.width = width;
		config.height = height;
		config.x = 0;
		config.y = 0;
		new JoglNewtApplication(new RayOfGod(), config);

	}
}
