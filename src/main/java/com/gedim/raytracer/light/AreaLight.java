package com.gedim.raytracer.light;

import com.gedim.raytracer.math.Vector3;
import com.gedim.raytracer.util.RGB;

public class AreaLight extends BaseLight implements Light {

	private double	size;
	private Vector3	direction;

	public AreaLight(Vector3 position, RGB color, double intensity) {
		super(position, color, intensity);
		size = 0.5d;
	}

	public double getSize() {
		return size;
	}

	public Vector3 getDirection() {
		return direction;
	}
}
