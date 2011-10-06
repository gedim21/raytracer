package com.gedim.raytracer;

import com.gedim.raytracer.primitive.Primitive;

public class Raytrace {

	private RGB intensity;

	public Raytrace() {
		this.intensity = new RGB();
	}
	
	public Raytrace( RGB intensity) {
		super();
		this.intensity = intensity;
	}

	public RGB getIntensity() {
		return intensity;
	}

	public void setIntensity(RGB intensity) {
		this.intensity = intensity;
	}
}
