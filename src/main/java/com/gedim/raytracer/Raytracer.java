package com.gedim.raytracer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gedim.raytracer.io.PngWriter;
import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.light.PointLight;
import com.gedim.raytracer.material.Material;
import com.gedim.raytracer.primitive.Sphere;
import com.gedim.raytracer.shader.Shader;
import com.gedim.raytracer.util.Vector3;

public class Raytracer {

	private final static Logger log = LoggerFactory.getLogger(Raytracer.class);

	private Framebuffer framebuffer;

	public void render(final Scene scene, final RenderOptions options)
			throws IOException {

		log.info("Rendering...");

		final Vector3 rayOrigin = scene.getCamera().getPosition();

		int xRes = options.getResolution().getX();
		int yRes = options.getResolution().getY();
		framebuffer = new Framebuffer(xRes, yRes);
		BufferedImage bufferedImage = new BufferedImage(xRes, yRes,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < xRes; x++) {
			for (int y = 0; y < yRes; y++) {
				Raytrace raytrace = new Raytrace(null, new RGB(0, 0, 0));
				RGB pixel = new RGB(Math.random(), Math.random(), Math.random());
				framebuffer.setPixel(x, y, pixel);

				raytrace = raytrace(scene, new Ray(rayOrigin), 0, raytrace,
						1000.0);
				bufferedImage.setRGB(x, y, (int) (raytrace.getIntensity()
						.getRed() * 255.0));
			}
		}

		new PngWriter().saveImage(bufferedImage, "c:/image.png");
	}

	private Raytrace raytrace(final Scene scene, final Ray ray,
			final int traceDepth, final Raytrace raytrace, final double distance) {
		if (traceDepth == 0) {
			return raytrace;
		}

		IntersectionResult intersectionResult = scene.findClosestIntersection(
				ray, distance);

		if (intersectionResult != null) {

			Shader materialShader = intersectionResult.getPrimitive()
					.getMaterial().getShader();

			RGB totalIntensity = new RGB();
			for (Light light : scene.getLights()) {

				double shadowIntensity = 0.0d;

				RGB intensity = materialShader.calculateIntensity(
						intersectionResult.getIntersectionNormal(), light,
						shadowIntensity);
				totalIntensity = totalIntensity.add(intensity);

			}
			Raytrace result = new Raytrace(intersectionResult.getPrimitive(),
					totalIntensity);

			return result;
		} else {
			return raytrace;
		}
	}

	public static void main(String[] args) throws IOException {
		RenderOptions options = new RenderOptions();
		options.setSamplesPerPixel(8);
		options.setMaxRayDepth(4);
		options.setResolution(new Resolution(640, 640));

		Camera camera = new Camera(new Vector3(8.0, 8.0, -50.0));
		Scene scene = new Scene();
		scene.setCamera(camera);

		Material material = new Material();
		material.setAmbient(0.5);
		
		Sphere sphere1 = new Sphere(new Vector3(4d, 0d, 0d), 2d);
		sphere1.setMaterial(material);
		
		Sphere sphere2 = new Sphere(new Vector3(-3d, 0d, 3d), 2d);
		sphere2.setMaterial(material);
		
		Sphere sphere3 = new Sphere(new Vector3(-3d, 3d, 3d), 2d);
		sphere3.setMaterial(material);

		scene.addPrimitive(sphere1);
		scene.addPrimitive(sphere2);
		scene.addPrimitive(sphere3);

		PointLight light = new PointLight(new Vector3(10d, 10d, 10d), new RGB(
				0.5, 0.5, 1.0), 1.0);
		scene.addLight(light);

		new Raytracer().render(scene, options);
	}
}
