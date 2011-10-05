package com.gedim.raytracer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.light.PointLight;
import com.gedim.raytracer.material.Material;
import com.gedim.raytracer.primitive.Plane;
import com.gedim.raytracer.primitive.Sphere;
import com.gedim.raytracer.sampler.Sample;
import com.gedim.raytracer.sampler.Sampler;
import com.gedim.raytracer.sampler.StratifiedSampler;
import com.gedim.raytracer.shader.PhongShader;
import com.gedim.raytracer.shader.Shader;
import com.gedim.raytracer.ui.RenderPanel;
import com.gedim.raytracer.util.Vector3;

public class Raytracer {

	private final static Logger	log	= LoggerFactory.getLogger(Raytracer.class);

	public void render(final Scene scene, final RenderOptions options) throws IOException {

		log.info("Rendering...");

		final Vector3 rayOrigin = scene.getCamera().getPosition();

		int xRes = options.getResolution().getX();
		int yRes = options.getResolution().getY();
		BufferedImage bufferedImage = new BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB);

		double u = -5;
		double v = -5;
		double fu = 10.0 / (double) xRes;
		double fv = 10.0 / (double) yRes;

		Sampler sampler = new StratifiedSampler();
		for (int y = 0; y < xRes; y++) {
			for (int x = 0; x < yRes; x++) {
				Raytrace raytrace = new Raytrace();
				Sample sample = sampler.samplePixel(u, v, 1);
				Ray ray = new Ray(rayOrigin, new Vector3(sample.getX(), sample.getY(), 1.0).subtract(rayOrigin));
				raytrace = raytrace(scene, ray.normalize(), options.getMaxRayDepth(), raytrace, 1000.0);
				bufferedImage.getRaster().setPixel(x, y, raytrace.getIntensity().asArray(255.0));
				u += fu;
			}
			u = -5;
			v += fv;
		}

		RenderPanel renderPanel = new RenderPanel(options, bufferedImage);
		renderPanel.showRenderPanel();
		log.info("Finished");
	}

	private Raytrace raytrace(final Scene scene, final Ray ray, final int traceDepth, final Raytrace raytrace,
			final double distance) {
		if (traceDepth == 0) {
			return raytrace;
		}

		IntersectionResult intersectionResult = scene.findClosestIntersection(ray, distance);

		if (intersectionResult != null && intersectionResult.getIntersectionPoint() != null) {

			Shader materialShader = intersectionResult.getPrimitive().getMaterial().getShader();
			RGB totalIntensity = new RGB();
			for (Light light : scene.getLights()) {

				double shadowIntensity = 0.0d;
				RGB intensity = materialShader.calculateIntensity(intersectionResult.getRay(),
						intersectionResult.getPrimitive(), intersectionResult.getIntersectionNormal(), light, shadowIntensity);
				totalIntensity = totalIntensity.add(intensity);
			}

			Raytrace result = new Raytrace(intersectionResult.getPrimitive(), totalIntensity);
			return result;
		} else {
			return raytrace;
		}
	}

	public static void main(String[] args) throws IOException {
		RenderOptions options = new RenderOptions();
		options.setSamplesPerPixel(8);
		options.setMaxRayDepth(4);
		options.setResolution(new Resolution(320, 320));

		Camera camera = new Camera(new Vector3(10.0, 10.0, -50.0));
		Scene scene = new Scene();
		scene.setCamera(camera);

		Material material = new Material();
		material.setAmbient(0.1);
		material.setDiffuse(0.9);
		material.setSpecularity(0.1);
		material.setGlossiness(0.1);
		material.setShader(new PhongShader());
		material.setColor(new RGB(0.1, 0.3, 0.5));

		Material material2 = new Material();
		material2.setAmbient(0.5);
		material2.setDiffuse(0.5);
		material2.setSpecularity(0.1);
		material2.setGlossiness(0.1);
		material2.setShader(new PhongShader());
		material2.setColor(new RGB(0.1, 0.3, 0.5));

		Plane plane = new Plane(0.1, new Vector3(0.0, 3.0, 0.0), new Vector3(0.0, -1.0, 0.0));
		plane.setMaterial(material2);

		Sphere sphere1 = new Sphere(new Vector3(0.0d, 0.0d, 0.0d), 0.35d);
		sphere1.setMaterial(material);

		Sphere sphere2 = new Sphere(new Vector3(3.0d, 3.0d, 0.0d), 0.25d);
		sphere2.setMaterial(material);

		 scene.addPrimitive(sphere1);
		 scene.addPrimitive(sphere2);
		scene.addPrimitive(plane);

		PointLight light = new PointLight(new Vector3(-5d, 10d, -10d), new RGB(0.8, 0.8, 0.8), .9);
		scene.addLight(light);

		new Raytracer().render(scene, options);
	}
}
