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
				Raytrace raytrace = samplePixel(u, v, fu, fv, sampler, rayOrigin, scene, options);
				bufferedImage.getRaster().setPixel(x, y, raytrace.getIntensity().asArray(255.0));
				u += fu;
			}
			u = -5;
			v += fv;
		}

		RenderPanel renderPanel = new RenderPanel(options, bufferedImage);
		renderPanel.showRenderPanel();
		log.info("Finished rendering");
	}

	private Raytrace samplePixel(double u, double v, double fu, double fv, Sampler sampler, Vector3 rayOrigin,
			Scene scene, RenderOptions options) {
		
		Raytrace raytrace = new Raytrace();
		Sample sample = sampler.samplePixel(u, v, 1);
		Ray ray = new Ray(rayOrigin, new Vector3(sample.getX(), sample.getY(), 1.0).subtract(rayOrigin));
		raytrace = raytrace(scene, ray.normalize(), options.getMaxRayDepth(), raytrace, 1000.0);
		return raytrace;
	}

	private Raytrace raytrace(final Scene scene, final Ray ray, final int traceDepth, final Raytrace raytrace,
			final double distance) {
		if (traceDepth == 0) {
			return raytrace;
		}

		IntersectionResult intersectionResult = scene.findClosestIntersection(ray, distance);

		if (intersectionResult != null && intersectionResult.getIntersectionPoint() != null) {

			Material material = intersectionResult.getPrimitive().getMaterial();
			Shader materialShader = material.getShader();
			RGB totalIntensity = new RGB();
			for (Light light : scene.getLights()) {

				double shadowIntensity = 0.0d;
				RGB intensity = materialShader.calculateIntensity(intersectionResult.getRay(),
						intersectionResult.getPrimitive(), intersectionResult.getIntersectionNormal().normalize(), light,
						shadowIntensity);
				totalIntensity = totalIntensity.add(intensity);
			}

			if (material.getReflection() > 0.0 && traceDepth != 0) {
				Vector3 reflectedVector = ray.getDirection().normalize()
						.getReflected(intersectionResult.getIntersectionNormal()).normalize();
				RGB reflectionIntensity = new RGB();
				Ray newRay = new Ray(intersectionResult.getIntersectionPoint().add(reflectedVector.multiply(Double.MIN_VALUE)));
				Raytrace reflectionResult = raytrace(scene, newRay, traceDepth - 1, raytrace, distance);
				reflectionIntensity = reflectionResult.getIntensity().multiply(material.getReflection())
						.multiply(material.getColor());
				totalIntensity = totalIntensity.add(reflectionIntensity);
			}

			Raytrace result = new Raytrace(totalIntensity);
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

		Camera camera = new Camera(new Vector3(0.0, 0.0, -500.0));
		Scene scene = new Scene();
		scene.setCamera(camera);

		Material material1 = new Material();
		material1.setAmbient(0.1);
		material1.setDiffuse(0.4);
		material1.setSpecularity(0.1);
		material1.setGlossiness(0.1);
		material1.setShader(new PhongShader());
		material1.setColor(new RGB(0.1, 0.3, 0.5));
		material1.setReflection(0.1);

		Material material2 = new Material();
		material2.setAmbient(0.5);
		material2.setDiffuse(0.5);
		material2.setSpecularity(0.1);
		material2.setGlossiness(0.1);
		material2.setShader(new PhongShader());
		material2.setColor(new RGB(0.1, 0.3, 0.5));

		Plane plane = new Plane(0.1, new Vector3(0.0, 0.0, 0.0), new Vector3(0.0, -1.0, 0.0));
		plane.setMaterial(material2);

		Sphere sphere1 = new Sphere(new Vector3(0.0, -1.5, 0.0), 0.35d);
		sphere1.setMaterial(material1);

		Sphere sphere2 = new Sphere(new Vector3(1.5, 0.0, 0.0), 0.35d);
		sphere2.setMaterial(material1);

		Sphere sphere3 = new Sphere(new Vector3(-1.5, 0.0, 0.0), 0.35d);
		sphere3.setMaterial(material1);

		scene.addPrimitive(sphere1);
		scene.addPrimitive(sphere2);
		scene.addPrimitive(sphere3);
		// scene.addPrimitive(plane);

		PointLight light1 = new PointLight(new Vector3(-5d, -10d, -10d), new RGB(0.9, 0.9, 0.9), .9);
		PointLight light2 = new PointLight(new Vector3(5d, -10d, -10d), new RGB(0.8, 0.9, 0.8), .4);
		PointLight light3 = new PointLight(new Vector3(5d, 10d, -10d), new RGB(0.8, 0.8, 0.8), .1);

		scene.addLight(light1);
		scene.addLight(light2);
		scene.addLight(light3);

		new Raytracer().render(scene, options);
	}
}
