package com.gedim.raytracer.primitive;

import com.gedim.raytracer.HitResult;
import com.gedim.raytracer.Ray;
import com.gedim.raytracer.math.Vector3;

public class Plane extends BasePrimitive implements Primitive {

	private double	size;
	private Vector3	direction;
	
	public Plane(double size, Vector3 position, Vector3 direction) {
		super(position);
		this.size = size;
		this.direction = direction;
	}

	public HitResult intersectRay(Ray ray, double distance) {
		HitResult hitResult = new HitResult();
		double d = direction.normalize().dotProduct(ray.getDirection().normalize());
		if( d != 0.0 ) {
			double dist = -(direction.normalize().dotProduct(ray.getOrigin()) - size) / d;
			if( dist > 0.0 ) {
				if( dist < distance ) {
					hitResult.setDistance(dist);
					hitResult.setHit(1);
				}
			}
		}
		return hitResult;
	}

	public Vector3 getDirection() {
		return direction;
	}

	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

}
