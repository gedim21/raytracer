package com.gedim.raytracer;

import com.gedim.raytracer.math.Vector3;

public class Camera {

	private Vector3 position;

	public Camera() {
	}

	public Camera(Vector3 position) {
		this.position = position;
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}
}
