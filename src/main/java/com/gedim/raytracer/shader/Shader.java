package com.gedim.raytracer.shader;

import com.gedim.raytracer.Ray;
import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.primitive.Primitive;
import com.gedim.raytracer.util.RGB;
import com.gedim.raytracer.util.Vector3;

public interface Shader {

	RGB calculateIntensity(Ray ray, Primitive primitive, Vector3 normal,
			Light light, double shadowIntensity);
}
