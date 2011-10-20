package com.gedim.raytracer.light;

import com.gedim.raytracer.math.Vector3;
import com.gedim.raytracer.util.RGB;

public interface Light {

	public Vector3 getDirection();

	public Vector3 getPosition();

	public RGB getColor();

	public double getIntensity();
}
