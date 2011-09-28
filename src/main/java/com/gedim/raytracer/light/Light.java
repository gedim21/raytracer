package com.gedim.raytracer.light;

import com.gedim.raytracer.util.Vector3;

public interface Light {

	public Vector3 getNormal();

	public double getIntensity();
}
