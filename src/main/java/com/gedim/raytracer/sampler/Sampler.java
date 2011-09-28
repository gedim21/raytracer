package com.gedim.raytracer.sampler;

public interface Sampler {

	public Sample samplePixel(double x, double y, int numOfSamples);

}
