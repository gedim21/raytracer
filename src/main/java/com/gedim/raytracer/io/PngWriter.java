package com.gedim.raytracer.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PngWriter {

	private final static Logger log = LoggerFactory.getLogger(PngWriter.class);

	public void saveImage(BufferedImage bufferedImage, String filename) {

		try {
			File output = new File(filename);
			ImageIO.write(bufferedImage, "PNG", output);
		} catch (IOException e) {
			log.error("Cannot write image to file. :" + e.getMessage());
		}
	}
}
