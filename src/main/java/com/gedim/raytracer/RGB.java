package com.gedim.raytracer;

public class RGB {

	private double red, green, blue;

	public RGB() {
		this(0d, 0d, 0d);
	}

	public RGB(double red, double green, double blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public RGB(RGB color) {
		this(color.getRed(), color.getGreen(), color.getBlue());
	}

	public RGB add(RGB value) {
		return new RGB(this.red + value.red, this.green + value.green,
				this.blue + value.blue);
	}

	public RGB subtract(RGB value) {
		return new RGB(this.red - value.red, this.green - value.green,
				this.blue - value.blue);
	}

	public RGB multiply(double value) {
		return new RGB(this.red * value, this.green * value, this.blue * value);
	}

	public RGB divide(double value) {
		return new RGB(this.red / value, this.green / value, this.blue / value);
	}

	public double getRed() {
		return red;
	}

	public double getGreen() {
		return green;
	}

	public double getBlue() {
		return blue;
	}
}
