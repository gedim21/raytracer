package com.gedim.raytracer;

import java.io.IOException;

import com.gedim.raytracer.renderer.BasicRenderer;
import com.gedim.raytracer.renderer.Renderer;
import com.gedim.raytracer.scene.Scene;
import com.gedim.raytracer.scene.SceneParser;

public class Raytracer {

	public static void main(String[] args) throws IOException {
		RenderOptions options = new RenderOptions();
		options.setSamplesPerPixel(8);
		options.setMaxRayDepth(4);
		options.setResolution(new Resolution(320, 320));

		SceneParser parser = new SceneParser();
		Scene scene = parser.parse(null);

		Renderer renderer = new BasicRenderer();

		renderer.render(scene, options);
	}
}
