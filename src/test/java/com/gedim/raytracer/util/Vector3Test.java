package com.gedim.raytracer.util;

import org.junit.Test;

import junit.framework.TestCase;

public class Vector3Test extends TestCase {

	private final static double PRECISION = 0.0000001;

	@Test
	public void testAddImmutability() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = new Vector3(0.2, 0.4, 0.6);
		Vector3 vecC = vecA.add(vecB);

		assertNotSame(vecC, vecA);
		assertNotSame(vecC, vecB);
	}

	@Test
	public void testAddResult() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = new Vector3(0.2, 0.4, 0.6);
		Vector3 vecC = vecA.add(vecB);

		assertEquals(0.3, vecC.getX(), PRECISION);
		assertEquals(0.7, vecC.getY(), PRECISION);
		assertEquals(1.1, vecC.getZ(), PRECISION);
	}

	@Test
	public void testSubtractImmutability() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = new Vector3(0.2, 0.4, 0.6);
		Vector3 vecC = vecA.subtract(vecB);

		assertNotSame(vecC, vecA);
		assertNotSame(vecC, vecB);
	}

	@Test
	public void testSubtractResult() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = new Vector3(0.2, 0.4, 0.6);
		Vector3 vecC = vecA.subtract(vecB);

		assertEquals(-0.1, vecC.getX(), PRECISION);
		assertEquals(-0.1, vecC.getY(), PRECISION);
		assertEquals(-0.1, vecC.getZ(), PRECISION);
	}

	@Test
	public void testMultiplyImmutability() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = vecA.multiply(0.5);

		assertNotSame(vecB, vecA);
	}

	@Test
	public void testMultiplyResult() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = vecA.multiply(0.5);

		assertEquals(0.05, vecB.getX(), PRECISION);
		assertEquals(0.15, vecB.getY(), PRECISION);
		assertEquals(0.25, vecB.getZ(), PRECISION);
	}

	@Test
	public void testDivideImmutability() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = vecA.divide(2.0);

		assertNotSame(vecB, vecA);
	}

	@Test
	public void testDivideResult() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = vecA.divide(2.0);

		assertEquals(0.05, vecB.getX(), PRECISION);
		assertEquals(0.15, vecB.getY(), PRECISION);
		assertEquals(0.25, vecB.getZ(), PRECISION);
	}

	@Test
	public void testCrossProductImmutability() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = new Vector3(0.2, 0.4, 0.6);
		Vector3 vecC = vecA.crossProduct(vecB);

		assertNotSame(vecC, vecA);
		assertNotSame(vecC, vecB);
	}

	@Test
	public void testCrossProductResult() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = new Vector3(0.2, 0.4, 0.6);
		Vector3 vecC = vecA.crossProduct(vecB);

		assertEquals(-0.02, vecC.getX(), PRECISION);
		assertEquals(0.04, vecC.getY(), PRECISION);
		assertEquals(-0.02, vecC.getZ(), PRECISION);
	}

	@Test
	public void testCrossProductReverseResults() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = new Vector3(0.2, 0.4, 0.6);
		Vector3 vecC = vecB.crossProduct(vecA);

		assertEquals(0.02, vecC.getX(), PRECISION);
		assertEquals(-0.04, vecC.getY(), PRECISION);
		assertEquals(0.02, vecC.getZ(), PRECISION);
	}

	@Test
	public void testDotProductResult() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = new Vector3(0.2, 0.4, 0.6);
		double dotProduct = vecA.dotProduct(vecB);

		assertEquals(0.44, dotProduct, PRECISION);
	}

	@Test
	public void testLengthResult() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		double length = vecA.length();

		assertEquals(0.5916079783, length, PRECISION);
	}

	@Test
	public void testNormalizeImmutability() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = vecA.normalize();

		assertNotSame(vecA, vecB);
	}

	@Test
	public void testNormalizeResult() {
		Vector3 vecA = new Vector3(0.1, 0.3, 0.5);
		Vector3 vecB = vecA.normalize();

		assertEquals(0.16903085, vecB.getX(), PRECISION);
		assertEquals(0.50709255, vecB.getY(), PRECISION);
		assertEquals(0.84515425, vecB.getZ(), PRECISION);
	}
}
