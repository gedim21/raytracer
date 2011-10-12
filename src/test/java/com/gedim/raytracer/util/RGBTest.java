package com.gedim.raytracer.util;

import org.junit.Test;

import com.gedim.raytracer.BaseTest;
import com.gedim.raytracer.util.RGB;

public class RGBTest extends BaseTest {

	@Test
	public void testAdd() {
		// given
		RGB colorA = new RGB(.1, .2, .3);
		RGB colorB = new RGB(.4, .5, .6);
		// when
		RGB result = colorA.add(colorB);

		// then
		assertEquals(.5, result.getRed(), PRECISION);
		assertEquals(.7, result.getGreen(), PRECISION);
		assertEquals(.9, result.getBlue(), PRECISION);
	}

	@Test
	public void testSubtract() {
		// given
		RGB colorA = new RGB(.1, .2, .3);
		RGB colorB = new RGB(.4, .5, .6);
		// when
		RGB result = colorB.subtract(colorA);

		// then
		assertEquals(.3, result.getRed(), PRECISION);
		assertEquals(.3, result.getGreen(), PRECISION);
		assertEquals(.3, result.getBlue(), PRECISION);
	}

	@Test
	public void testMultiplyDouble() {
		// given
		RGB colorA = new RGB(.1, .2, .3);
		// when
		RGB result = colorA.multiply(2.0);

		// then
		assertEquals(.2, result.getRed(), PRECISION);
		assertEquals(.4, result.getGreen(), PRECISION);
		assertEquals(.6, result.getBlue(), PRECISION);
	}

	@Test
	public void testMultiplyRGB() {
		// given
		RGB colorA = new RGB(.1, .2, .3);
		RGB colorB = new RGB(.1, .2, .3);

		// when
		RGB result = colorA.multiply(colorB);

		// then
		assertEquals(.01, result.getRed(), PRECISION);
		assertEquals(.04, result.getGreen(), PRECISION);
		assertEquals(.09, result.getBlue(), PRECISION);
	}

	@Test
	public void testDivide() {
		// given
		RGB colorA = new RGB(.1, .2, .3);
		// when
		RGB result = colorA.divide(2.0);

		// then
		assertEquals(.05, result.getRed(), PRECISION);
		assertEquals(.1, result.getGreen(), PRECISION);
		assertEquals(.15, result.getBlue(), PRECISION);
	}

	@Test
	public void testAsArray() {
		// given
		RGB colorA = new RGB(.1, .2, .3);
		// when
		double[] result = colorA.asArray();

		// then
		assertEquals(.1, result[0], PRECISION);
		assertEquals(.2, result[1], PRECISION);
		assertEquals(.3, result[2], PRECISION);
	}

	@Test
	public void testAsArrayDouble() {
		// given
		RGB colorA = new RGB(.1, .2, .3);
		// when
		double[] result = colorA.asArray(255.0);

		// then
		assertEquals(25.5, result[0], PRECISION);
		assertEquals(51.0, result[1], PRECISION);
		assertEquals(76.5, result[2], PRECISION);
	}

}
