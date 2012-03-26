package com.gedim.raytracer.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


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
	
	public RGB clamp(double max) {
	  RGB result = new RGB(this);
	  result.red = result.red > max ? max : result.red;
	  result.green = result.green > max ? max : result.green;
	  result.blue = result.blue > max ? max : result.blue;
	  return result;
	}

	public double[] asArray(double multiplant) {
	    final RGB res = this.multiply(multiplant).clamp(multiplant);
		double[] array = new double[] { res.red , res.green , res.blue  };
		return array;
	}

	@Override
	public String toString() {
		return "RGB{red: " + red + ", green: " + green + ", blue: " + blue + "}";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		RGB rhs = (RGB) obj;
		return new EqualsBuilder().append(red, rhs.red).append(green, rhs.green).append(blue, rhs.blue).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(red).append(green).append(blue).toHashCode();
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
