package com.gedim.raytracer.light;

import com.gedim.raytracer.math.Vector3;
import com.gedim.raytracer.util.RGB;

public abstract class BaseLight implements Light {

	private Vector3 position;
	private RGB color;
	private double intensity;

	public BaseLight(Vector3 position, RGB color, double intensity) {
		super();
		this.position = position;
		this.color = color;
		this.intensity = intensity;
	}

	public Vector3 getPosition() {
		return position;
	}

	public RGB getColor() {
		return color;
	}

	public double getIntensity() {
		return intensity;
	}
}
