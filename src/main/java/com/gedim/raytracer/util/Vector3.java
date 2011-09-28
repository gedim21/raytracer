package com.gedim.raytracer.util;

public class Vector3 {

	private double x, y, z;

	public Vector3() {
		this(0.0, 0.0, 0.0);
	}

	public Vector3(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3 add(final Vector3 vec) {
		return new Vector3(this.x + vec.x, this.y + vec.y, this.z + vec.z);
	}

	public Vector3 subtract(final Vector3 vec) {
		return new Vector3(this.x - vec.x, this.y - vec.y, this.z - vec.z);
	}

	public Vector3 multiply(final double value) {
		return new Vector3(this.x * value, this.y * value, this.z * value);
	}

	public Vector3 divide(final double value) {
		return new Vector3(this.x / value, this.y / value, this.z / value);
	}

	public Vector3 crossProduct(final Vector3 vec) {
		double x = (this.y * vec.z) - (this.z * vec.y);
		double y = (this.z * vec.x) - (this.x * vec.z);
		double z = (this.x * vec.y) - (this.y * vec.x);
		return new Vector3(x, y, z);
	}

	public double dotProduct(final Vector3 vec) {
		return (this.x * vec.x) + (this.y * vec.y) + (this.z * vec.z);
	}

	public double length() {
		return Math.sqrt(this.dotProduct(this));
	}

	public Vector3 normalize() {
		double length = this.length();
		if (length == 0.0) {
			return new Vector3();
		} else {
			return this.divide(length);
		}
	}

	@Override
	public String toString() {
		return "Vector3{" + x + "," + y + "," + z + "}";
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
}
