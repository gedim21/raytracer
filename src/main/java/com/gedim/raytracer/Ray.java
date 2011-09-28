package com.gedim.raytracer;

import com.gedim.raytracer.util.Vector3;

public class Ray {

	private Vector3 origin;
	private Vector3 direction;

	public Ray(Vector3 origin, Vector3 direction) {
		super();
		this.origin = origin;
		this.direction = direction;
	}

	public Ray(Vector3 direction) {
		this.origin = new Vector3();
		this.direction = new Vector3(direction.getX(), direction.getY(),
				direction.getZ());
	}

	public Ray normalize() {
		return new Ray(origin, direction.normalize());
	}

	public Vector3 getOrigin() {
		return origin;
	}

	public void setOrigin(Vector3 origin) {
		this.origin = origin;
	}

	public Vector3 getDirection() {
		return direction;
	}

	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}
}
