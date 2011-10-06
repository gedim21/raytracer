package com.gedim.raytracer;

public class RGB {

	private double	red, green, blue;

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
		return new RGB(this.red + value.red, this.green + value.green, this.blue + value.blue);
	}

	public RGB subtract(RGB value) {
		return new RGB(this.red - value.red, this.green - value.green, this.blue - value.blue);
	}

	public RGB multiply(double value) {
		return new RGB(this.red * value, this.green * value, this.blue * value);
	}

	public RGB multiply(RGB color) {
		return new RGB(this.red * color.red, this.green * color.green, this.blue * color.blue);
	}

	public RGB divide(double value) {
		return new RGB(this.red / value, this.green / value, this.blue / value);
	}

	public double[] asArray() {
		return new double[] { red, green, blue };
	}

	public double[] asArray(double multiplant) {
		double[] array = new double[] { red * (double) multiplant, green * (double) multiplant, blue * (double) multiplant };
		for( int i = 0 ; i < 2 ; ++i) {
			array[i] = array[i] > multiplant ? multiplant : array[i];
		}
		return array;
	}

	@Override
	public String toString() {
		return "RGB{red: " + red + ", green: " + green + ", blue: " + blue + "}";
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
