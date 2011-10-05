package com.gedim.raytracer.light;

import com.gedim.raytracer.RGB;
import com.gedim.raytracer.util.Vector3;

public interface Light {

	public Vector3 getDirection();

	public Vector3 getPosition();

	public RGB getColor();

	public double getIntensity();
}
