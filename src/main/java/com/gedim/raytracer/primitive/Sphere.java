package com.gedim.raytracer.primitive;

import com.gedim.raytracer.HitResult;
import com.gedim.raytracer.Ray;
import com.gedim.raytracer.util.Vector3;

public class Sphere extends BasePrimitive implements Primitive {

	private double radius;

	public Sphere(Vector3 position, double radius) {
		super(position);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public Vector3 getNormalAt(Vector3 point) {
		return point.subtract(getPosition());
	}

	public HitResult intersectRay(final Ray ray, final double distance) {
		Vector3 v = ray.getOrigin().subtract(this.getPosition());
		double b = v.dotProduct(ray.getDirection());
		double det = (b * b) - v.dotProduct(v) + (radius * 2);

		double hitDistance = distance;
		int hitResult = 0; // no hit
		if (det > 0) {
			det = Math.sqrt(det);
			double i1 = b - det;
			double i2 = b + det;
			if (i2 > 0) {
				if (i1 < 0) {
					if (i2 < distance) {
						hitDistance = i2;
						hitResult = -1; // hit form the inside
					}
				} else {
					if (i1 < distance) {
						hitDistance = i1;
						hitResult = 1; // hit from the outside
					}
				}
			}
		}

		return new HitResult(hitResult, hitDistance);
	}
}
