package com.gedim.raytracer.sampler;

public class StratifiedSampler implements Sampler {

	private double pixelSize, xJitter, yJitter;

	public Sample samplePixel(double x, double y, int numOfSamples) {
		for (int ix = 0; ix < numOfSamples; ix++) {
			for (int iy = 0; iy < numOfSamples; iy++) {
				double dx = (10.0 / 640.0) * ((double) ix)
						/ ((double) numOfSamples);
				double dy = (10.0 / 640.0) * ((double) iy)
						/ ((double) numOfSamples);
				return new Sample(dx + x, dy + y, 1, 1, 1);
			}
		}
		return new Sample(0, 0, 0, 0, 0);
	}
}
