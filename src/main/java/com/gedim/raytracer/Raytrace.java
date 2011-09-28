package com.gedim.raytracer;

import com.gedim.raytracer.primitive.Primitive;

public class Raytrace {

	private Primitive primitive;
	private RGB intensity;

	public Raytrace(Primitive primitive, RGB intensity) {
		super();
		this.primitive = primitive;
		this.intensity = intensity;
	}

	public Primitive getPrimitive() {
		return primitive;
	}

	public void setPrimitive(Primitive primitive) {
		this.primitive = primitive;
	}

	public RGB getIntensity() {
		return intensity;
	}

	public void setIntensity(RGB intensity) {
		this.intensity = intensity;
	}
}
