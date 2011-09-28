package com.gedim.raytracer.primitive;

import com.gedim.raytracer.HitResult;
import com.gedim.raytracer.Ray;
import com.gedim.raytracer.material.Material;
import com.gedim.raytracer.util.Vector3;

public interface Primitive {

	Vector3 getPosition();
	
	Material getMaterial();

	/**
	 * Get the primitives normal in the given point
	 * 
	 * @param IntersectionPoint
	 *            The point where the normal is calculated
	 * @return The vector at the given point
	 */
	Vector3 getNormalAt(Vector3 point);

	HitResult intersectRay(final Ray ray, final double distance);
}
