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
import com.gedim.raytracer.sampler.Sample;
import com.gedim.raytracer.sampler.Sampler;
import com.gedim.raytracer.sampler.StratifiedSampler;
import com.gedim.raytracer.shader.PhongShader;
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
				BufferedImage.TYPE_INT_BGR);

		double u = -5;
		double v = -5;
		double fu = 10.0 / (double) xRes;
		double fv = 10.0 / (double) yRes;

		Sampler sampler = new StratifiedSampler();
		for (int y = 0; y < xRes; y++) {
			for (int x = 0; x < yRes; x++) {
				Raytrace raytrace = new Raytrace(null, new RGB(0, 0, 0));
				RGB pixel = new RGB(Math.random(), Math.random(), Math.random());
				framebuffer.setPixel(x, y, pixel);

				Sample sample = sampler.samplePixel(u, v, 1);
				Ray ray = new Ray(rayOrigin, new Vector3(sample.getX(),
						sample.getY(), 1.0).subtract(rayOrigin));
				raytrace = raytrace(scene, ray.normalize(),
						options.getMaxRayDepth(), raytrace, 1000.0);
				bufferedImage.setRGB(x, y, (int) (raytrace.getIntensity()
						.getRed() * 255.0));

				u += fu;
			}
			u = -5;
			v += fv;
		}

		log.info("Writing image...");
		new PngWriter().saveImage(bufferedImage, "/home/gedim/image.png");
		log.info("Finished");
	}

	private Raytrace raytrace(final Scene scene, final Ray ray,
			final int traceDepth, final Raytrace raytrace, final double distance) {
		if (traceDepth == 0) {
			return raytrace;
		}

		IntersectionResult intersectionResult = scene.findClosestIntersection(
				ray, distance);

		if (intersectionResult != null
				&& intersectionResult.getIntersectionPoint() != null) {

			Shader materialShader = intersectionResult.getPrimitive()
					.getMaterial().getShader();

			RGB totalIntensity = new RGB();
			for (Light light : scene.getLights()) {

				double shadowIntensity = 0.0d;

//				RGB intensity = materialShader.calculateIntensity(
//						intersectionResult.getRay(),
//						intersectionResult.getPrimitive(),
//						intersectionResult.getIntersectionNormal(), light,
//						shadowIntensity);
				 RGB intensity = new RGB(1.0,1.0,1.0);
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

		Camera camera = new Camera(new Vector3(10.0, 10.0, -50.0));
		Scene scene = new Scene();
		scene.setCamera(camera);

		Material material = new Material();
		material.setAmbient(0.3);
		material.setShader(new PhongShader());
		material.setColor(new RGB(0.1,0.3,0.5));

		Sphere sphere1 = new Sphere(new Vector3(0.0d, 0.0d, 0.0d), 0.25d);
		sphere1.setMaterial(material);

		scene.addPrimitive(sphere1);

		PointLight light = new PointLight(new Vector3(-10d, 10d, -10d), new RGB(
				0.5, 0.5, 0.5), 1.0);
		scene.addLight(light);

		new Raytracer().render(scene, options);
	}
}
