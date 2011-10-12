package com.gedim.raytracer.light;

import com.gedim.raytracer.util.RGB;
import com.gedim.raytracer.util.Vector3;

public class PointLight extends BaseLight implements Light {

	public PointLight(Vector3 position, RGB color, double intensity) {
		super(position, color, intensity);
	}

	public Vector3 getDirection() {
		return null;
	}
}
