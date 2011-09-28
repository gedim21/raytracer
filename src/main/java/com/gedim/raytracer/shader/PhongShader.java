package com.gedim.raytracer.shader;

import com.gedim.raytracer.RGB;
import com.gedim.raytracer.Ray;
import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.primitive.Primitive;
import com.gedim.raytracer.util.Vector3;

public class PhongShader implements Shader {

	public RGB calculateIntensity(Ray ray, Primitive primitive, Vector3 normal,
			Light light, double shadowIntensity) {
		return calculatePhongIntensity(ray, primitive, normal, light,
				shadowIntensity);
	}

	private RGB calculatePhongIntensity(Ray ray, Primitive primitive,
			Vector3 normal, Light light, double shadowIntensity) {
		return calculateAmbientIntensity(primitive).add(
				calculateDiffuseIntensity(primitive, normal, light,
						shadowIntensity)).add(calculateSpecularIntensity());
	}

	private RGB calculateAmbientIntensity(Primitive primitive) {

		return new RGB(primitive.getMaterial().getColor()
				.multiply(primitive.getMaterial().getAmbient()));

	}

	private RGB calculateDiffuseIntensity(Primitive primitive, Vector3 normal,
			Light light, double shadowIntensity) {
		if( primitive.getMaterial().getDiffuse() == 0.0) {
			return new RGB();
		} else if( light.getIntensity() == 0.0 ) {
			return new RGB();
		} else {
			return new RGB();
		}
	}

	private RGB calculateSpecularIntensity() {
		return new RGB();
	}
}
