package com.gedim.raytracer.shader;

import com.gedim.raytracer.RGB;
import com.gedim.raytracer.Ray;
import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.primitive.Primitive;
import com.gedim.raytracer.util.Vector3;

public class PhongShader implements Shader {

	public RGB calculateIntensity(Ray ray, Primitive primitive, Vector3 normal, Light light, double shadowIntensity) {
		return calculatePhongIntensity(ray, primitive, normal, light, shadowIntensity);
	}

	private RGB calculatePhongIntensity(Ray ray, Primitive primitive, Vector3 normal, Light light, double shadowIntensity) {
		RGB ambientIntensity = calculateAmbientIntensity(primitive);
		RGB diffuseIntensity = calculateDiffuseIntensity(primitive, normal, light, shadowIntensity);
		RGB specularIntensity = calculateSpecularIntensity(ray, primitive, normal, light, shadowIntensity);
		return ambientIntensity.add(diffuseIntensity);//.add(specularIntensity);
	}

	private RGB calculateAmbientIntensity(Primitive primitive) {
		return new RGB(primitive.getMaterial().getColor().multiply(primitive.getMaterial().getAmbient()));
	}

	private RGB calculateDiffuseIntensity(Primitive primitive, Vector3 normal, Light light, double shadowIntensity) {
		double nxl = normal.normalize().dotProduct(light.getPosition().normalize());
		if (primitive.getMaterial().getDiffuse() == 0.0) {
			return new RGB();
		} else if (light.getIntensity() == 0.0) {
			return new RGB();
		} else if (nxl < 0.0) {
			return new RGB();
		} else {
			RGB color = light.getColor().multiply(primitive.getMaterial().getColor()).multiply(nxl)
					.multiply(primitive.getMaterial().getDiffuse()).multiply(light.getIntensity());
			return color;
		}
	}

	private RGB calculateSpecularIntensity(Ray ray, Primitive primitive, Vector3 normal, Light light,
			double shadowIntensity) {
		if (primitive.getMaterial().getSpecularity() == 0.0) {
			return new RGB();
		} else if (light.getIntensity() == 0.0) {
			return new RGB();
		} else {
			Vector3 reflected = light.getPosition().getReflected(normal).normalize();
			double dot = reflected.dotProduct(ray.getDirection());

			if (dot < 0.0) {
				return new RGB();
			}

			double spec = light.getIntensity()
					* Math.pow(dot, (primitive.getMaterial().getGlossiness() + Double.MIN_VALUE) * 100.0);
			return new RGB(spec, spec, spec);
		}
	}
}
