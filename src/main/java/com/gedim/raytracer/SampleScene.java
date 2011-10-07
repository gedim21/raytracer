package com.gedim.raytracer;

import com.gedim.raytracer.light.PointLight;
import com.gedim.raytracer.material.Material;
import com.gedim.raytracer.primitive.Plane;
import com.gedim.raytracer.primitive.Sphere;
import com.gedim.raytracer.shader.PhongShader;
import com.gedim.raytracer.util.Vector3;

public class SampleScene extends Scene {

	public SampleScene() {
		Camera camera = new Camera(new Vector3(0.0, 0.0, -500.0));
		this.setCamera(camera);

		Material material1 = new Material();
		material1.setAmbient(0.1);
		material1.setDiffuse(0.5);
		material1.setSpecularity(0.1);
		material1.setGlossiness(0.1);
		material1.setShader(new PhongShader());
		material1.setColor(new RGB(0.9, 0.0, 0.0));
		material1.setReflection(0.3);

		Material material2 = new Material();
		material2.setAmbient(0.1);
		material2.setDiffuse(0.5);
		material2.setSpecularity(0.1);
		material2.setGlossiness(0.1);
		material2.setShader(new PhongShader());
		material2.setColor(new RGB(0.0, 0.0, 0.9));
		material2.setReflection(0.3);

		Material material3 = new Material();
		material3.setAmbient(0.1);
		material3.setDiffuse(0.5);
		material3.setSpecularity(0.1);
		material3.setGlossiness(0.1);
		material3.setShader(new PhongShader());
		material3.setColor(new RGB(0.0, 0.9, 0.0));
		material3.setReflection(0.3);
		
		Plane plane = new Plane(0.1, new Vector3(0.0, 0.0, 0.0), new Vector3(0.0, -1.0, 0.0));
		plane.setMaterial(material2);

		Sphere sphere1 = new Sphere(new Vector3(0.0, -1.5, -5.0), 0.35d);
		sphere1.setMaterial(material1);

		Sphere sphere2 = new Sphere(new Vector3(1.5, 0.0, 0.0), 0.35d);
		sphere2.setMaterial(material2);

		Sphere sphere3 = new Sphere(new Vector3(-1.5, 0.0, 0.0), 0.35d);
		sphere3.setMaterial(material3);

		this.addPrimitive(sphere1);
		this.addPrimitive(sphere2);
		this.addPrimitive(sphere3);
		// this.addPrimitive(plane);

		PointLight light1 = new PointLight(new Vector3(-5d, -10d, -10d), new RGB(0.9, 0.9, 0.9), .9);
		PointLight light2 = new PointLight(new Vector3(5d, -10d, -10d), new RGB(0.8, 0.9, 0.8), .4);
		PointLight light3 = new PointLight(new Vector3(5d, 10d, -10d), new RGB(0.8, 0.8, 0.8), .1);

		this.addLight(light1);
		this.addLight(light2);
		this.addLight(light3);
	}
}
