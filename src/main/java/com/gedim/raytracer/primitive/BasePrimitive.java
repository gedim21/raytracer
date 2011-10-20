package com.gedim.raytracer.primitive;

import com.gedim.raytracer.material.Material;
import com.gedim.raytracer.math.Vector3;

public abstract class BasePrimitive implements Primitive {

	private Vector3 position;
	private Material material;

	public BasePrimitive() {
	}

	public BasePrimitive(final Vector3 position) {
		this.position = new Vector3(position);
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(final Vector3 position) {
		this.position = position;
	}

	public Vector3 getNormalAt(final Vector3 point) {
		return new Vector3();
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
