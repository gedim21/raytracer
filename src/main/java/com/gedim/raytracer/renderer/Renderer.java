package com.gedim.raytracer.renderer;

import com.gedim.raytracer.RenderOptions;
import com.gedim.raytracer.scene.Scene;

public interface Renderer {

	void render(Scene scene, RenderOptions options);
}
