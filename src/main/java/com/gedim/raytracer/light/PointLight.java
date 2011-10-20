package com.gedim.raytracer.light;

import com.gedim.raytracer.math.Vector3;
import com.gedim.raytracer.util.RGB;

public class PointLight extends BaseLight implements Light {

	public PointLight(Vector3 position, RGB color, double intensity) {
		super(position, color, intensity);
	}

	public Vector3 getDirection() {
		return null;
	}
}
