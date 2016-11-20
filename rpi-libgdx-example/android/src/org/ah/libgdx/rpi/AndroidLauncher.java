package org.ah.libgdx.rpi;

import android.os.Bundle;

import org.ah.libgdx.rpi.RPiLibGDXExample;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new RPiLibGDXExample(), config);
	}
}
