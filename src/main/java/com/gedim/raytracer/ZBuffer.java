package com.gedim.raytracer;

import com.gedim.raytracer.primitive.BasePrimitive;

public class ZBuffer {

	private int width, height;
	private BasePrimitive[][] buffer;

	public ZBuffer(int width, int height) {
		this.width = width;
		this.height = height;
		buffer = new BasePrimitive[width][height];
	}

	public void setPrimitiveAt(int width, int height, BasePrimitive primitive) {
		buffer[width][height] = primitive;
	}

	public BasePrimitive getPrimitiveAt(int width, int height) {
		return buffer[width][height];
	}

	public void clearBuffer() {
		buffer = new BasePrimitive[width][height];
	}

	public BasePrimitive[][] getBuffer() {
		return buffer;
	}
}
