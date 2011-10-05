package com.gedim.raytracer;

public class HitResult {

	public static final int	NO_HIT			= 0;
	public static final int	INSIDE_HIT	= -1;
	public static final int	OUTSIDE_HIT	= 1;

	private int							hit;
	private double					distance;

	public HitResult() {
	}

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
