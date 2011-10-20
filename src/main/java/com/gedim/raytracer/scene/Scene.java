package com.gedim.raytracer.scene;

import java.util.ArrayList;
import java.util.List;

import com.gedim.raytracer.Camera;
import com.gedim.raytracer.HitResult;
import com.gedim.raytracer.IntersectionResult;
import com.gedim.raytracer.Ray;
import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.primitive.Primitive;

public class Scene {

	private Camera camera = new Camera();
	private List<Light> lights = new ArrayList<Light>();
	private List<Primitive> primitives = new ArrayList<Primitive>();

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Camera getCamera() {
		return camera;
	}

	public void addLight(Light light) {
		lights.add(light);
	}

	public List<Light> getLights() {
		return lights;
	}

	public void addPrimitive(Primitive primitive) {
		primitives.add(primitive);
	}

	public List<Primitive> getPrimitives() {
		return primitives;
	}

	public IntersectionResult findClosestIntersection(final Ray ray,
			final double distance) {

		Primitive closestPrimitive = null;
		double closestIntersectionDistance = distance;
		for (Primitive primitive : primitives) {
			HitResult hitResult = primitive.intersectRay(ray, distance);
			if (hitResult.getHit() != 0
					&& hitResult.getDistance() < closestIntersectionDistance) {
				closestIntersectionDistance = hitResult.getDistance();
				closestPrimitive = primitive;
			}
		}

		return new IntersectionResult(ray, closestPrimitive,
				closestIntersectionDistance);
	}
}
