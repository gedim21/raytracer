package com.gedim.raytracer;

public class Framebuffer {

	private Integer width, height;
	private RGB[][] buffer;

	public Framebuffer() {
		this.width = 640;
		this.height = 640;
		buffer = new RGB[width][height];
	}

	public Framebuffer(Integer width, Integer height) {
		this.width = width;
		this.height = height;
		buffer = new RGB[width][height];
	}

	public void setPixel(Integer x, Integer y, RGB pixel) {
		buffer[x][y] = pixel;
	}

	public RGB getPixel(Integer x, Integer y) {
		return buffer[x][y];
	}

	public RGB[][] getBuffer() {
		return buffer;
	}
}
