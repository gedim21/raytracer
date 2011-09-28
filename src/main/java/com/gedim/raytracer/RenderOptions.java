package com.gedim.raytracer;

public class RenderOptions {

	private Resolution resolution;
	private int samplesPerPixel;
	private int maxRayDepth;

	public RenderOptions() {
	}

	public RenderOptions(Resolution resolution, int samplesPerPixel,
			int maxRayDepth) {
		super();
		this.resolution = resolution;
		this.samplesPerPixel = samplesPerPixel;
		this.maxRayDepth = maxRayDepth;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public int getSamplesPerPixel() {
		return samplesPerPixel;
	}

	public void setSamplesPerPixel(int samplesPerPixel) {
		this.samplesPerPixel = samplesPerPixel;
	}

	public int getMaxRayDepth() {
		return maxRayDepth;
	}

	public void setMaxRayDepth(int maxRayDepth) {
		this.maxRayDepth = maxRayDepth;
	}

}
