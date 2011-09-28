package com.gedim.raytracer.light;

import com.gedim.raytracer.RGB;
import com.gedim.raytracer.util.Vector3;

public class AreaLight extends BaseLight implements Light {

	private double size;

	public AreaLight(Vector3 position, RGB color, double intensity) {
		super(position, color, intensity);
		size = 0.5d;
	}

	public double getSize() {
		return size;
	}
	
	@Override
	public Vector3 getNormal() {
		// TODO Auto-generated method stub
		return super.getNormal();
	}

}
