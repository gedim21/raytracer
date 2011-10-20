package com.gedim.raytracer.scene;

import java.io.File;

public class SceneParser {

	public Scene parse(File sceneFile) {
		return new SampleScene();
	}
}
