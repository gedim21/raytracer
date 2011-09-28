package com.gedim.raytracer.shader;

import com.gedim.raytracer.RGB;
import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.util.Vector3;

public interface Shader {

	RGB calculateIntensity(Vector3 normal, Light light,
			double shadowIntensity);
}
