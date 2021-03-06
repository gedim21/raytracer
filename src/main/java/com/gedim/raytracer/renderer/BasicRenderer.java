package com.gedim.raytracer.renderer;

import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gedim.raytracer.IntersectionResult;
import com.gedim.raytracer.Ray;
import com.gedim.raytracer.Raytrace;
import com.gedim.raytracer.RenderOptions;
import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.material.Material;
import com.gedim.raytracer.math.Vector3;
import com.gedim.raytracer.sampler.Sample;
import com.gedim.raytracer.sampler.Sampler;
import com.gedim.raytracer.sampler.StratifiedSampler;
import com.gedim.raytracer.scene.Scene;
import com.gedim.raytracer.shader.Shader;
import com.gedim.raytracer.ui.RenderPanel;
import com.gedim.raytracer.util.RGB;

public class BasicRenderer implements Renderer {

	private final static Logger	log	= LoggerFactory.getLogger(BasicRenderer.class);

	public void render(final Scene scene, final RenderOptions options) {
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
		for (int y = 0; y < yRes; y++) {
			for (int x = 0; x < xRes; x++) {
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
		Ray ray = new Ray(rayOrigin, new Vector3(sample.getX(), sample.getY(), 5.0).subtract(rayOrigin));
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

			// Lighting calculations
			for (Light light : scene.getLights()) {
				double shadowIntensity = 0.0d;
				RGB intensity = materialShader.calculateIntensity(intersectionResult.getRay(),
						intersectionResult.getPrimitive(), intersectionResult.getIntersectionNormal(), light,
						shadowIntensity);
				totalIntensity = totalIntensity.add(intensity);
			}

			// Reflections calucations
			if (material.getReflection() > 0.0 && traceDepth != 0) {
				Vector3 reflectedVector = ray.getDirection().getReflected(intersectionResult.getIntersectionNormal());
				RGB reflectionIntensity = new RGB();
				Ray newRay = new Ray(intersectionResult.getIntersectionPoint().add(reflectedVector.multiply(0.0001)),
						reflectedVector);
				Raytrace reflectionResult = raytrace(scene, newRay.normalize(), traceDepth - 1, raytrace, distance);
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

	// private RGB calculateLightsContribution() {
	// return new RGB();
	// }
	//
	// private RGB calculateReflectionsContribution() {
	// return new RGB();
	// }
}
