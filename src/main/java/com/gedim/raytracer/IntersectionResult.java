package com.gedim.raytracer;

import com.gedim.raytracer.primitive.BasePrimitive;
import com.gedim.raytracer.primitive.Primitive;
import com.gedim.raytracer.util.Vector3;

public class IntersectionResult {

	private Primitive intersectedPrimitive;
	private Ray intersectingRay;
	private Vector3 intersectionPoint;
	private Vector3 intersectionNormal;
	// the distance from the ray's origin where the intersection occured
	private double intersectionDistance;

	public IntersectionResult(Ray intersectingRay,
			Primitive intersectedPrimitive, double intersectionDistance) {
		super();
		this.intersectedPrimitive = intersectedPrimitive;
		this.intersectionDistance = intersectionDistance;

		intersectionPoint = intersectingRay.getOrigin().add(
				intersectingRay.getDirection().multiply(intersectionDistance));
		intersectionNormal = intersectedPrimitive
				.getNormalAt(intersectionPoint);
	}

	public Primitive getPrimitive() {
		return intersectedPrimitive;
	}

	public void setPrimitive(BasePrimitive primitive) {
		this.intersectedPrimitive = primitive;
	}

	public Ray getRay() {
		return intersectingRay;
	}

	public void setRay(Ray ray) {
		this.intersectingRay = ray;
	}

	public Vector3 getIntersectionPoint() {
		return intersectionPoint;
	}

	public void setIntersectionPoint(Vector3 intersectionPoint) {
		this.intersectionPoint = intersectionPoint;
	}

	public Vector3 getIntersectionNormal() {
		return intersectionNormal;
	}

	public void setIntersectionNormal(Vector3 intersectionNormal) {
		this.intersectionNormal = intersectionNormal;
	}

	public double getDistance() {
		return intersectionDistance;
	}

	public void setDistance(double distance) {
		this.intersectionDistance = distance;
	}

}