package com.gedim.raytracer.sampler;

public class Sample {

	private double x, y, u, v, time;

	public Sample(double x, double y, double u, double v, double time) {
		super();
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
		this.time = time;
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

	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
}
