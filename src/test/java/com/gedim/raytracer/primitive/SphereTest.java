package com.gedim.raytracer.primitive;

import org.junit.Test;

import com.gedim.raytracer.BaseTest;
import com.gedim.raytracer.HitResult;
import com.gedim.raytracer.Ray;
import com.gedim.raytracer.util.Vector3;

public class SphereTest extends BaseTest {

	@Test
	public void testIntersetRayOutsideHit() {
		// given
		Sphere sphere = new Sphere(new Vector3(0.0, 0.0, 0.0), 1.0);
		Ray ray = new Ray(new Vector3(0.0, 0.0, -2.0), new Vector3(0.0, 0.0,
				1.0));
		double distance = 10.0;

		// when
		HitResult hitResult = sphere.intersectRay(ray.normalize(), distance);

		// then
		assertEquals(HitResult.OUTSIDE_HIT, hitResult.getHit());
	}

	@Test
	public void testIntersetRayInsideHit() {
		// given
		Sphere sphere = new Sphere(new Vector3(0.0, 0.0, 0.0), 2.0);
		Ray ray = new Ray(new Vector3(0.0, 0.0, -1.0), new Vector3(0.0, 0.0,
				1.0)).normalize();
		double distance = 100.0;

		// when
		HitResult hitResult = sphere.intersectRay(ray, distance);

		// then
		assertEquals(HitResult.INSIDE_HIT, hitResult.getHit());
		assertEquals(3.0, hitResult.getDistance(), PRECISION);
	}
	
	@Test
	public void testIntersetRayNoHit() {
		// given
		Sphere sphere = new Sphere(new Vector3(0.0, 0.0, 0.0), 1.0);
		Ray ray = new Ray(new Vector3(0.0, 0.0, -2.0), new Vector3(1.0, 0.0,
				1.0));
		double distance = 10.0;

		// when
		HitResult hitResult = sphere.intersectRay(ray.normalize(), distance);

		// then
		assertEquals(HitResult.NO_HIT, hitResult.getHit());
	}
}
