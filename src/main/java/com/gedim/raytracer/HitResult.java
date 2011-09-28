package com.gedim.raytracer;

public class HitResult {

	private int hit;
	private double distance;

	public HitResult(int hit, double distance) {
		super();
		this.hit = hit;
		this.distance = distance;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
