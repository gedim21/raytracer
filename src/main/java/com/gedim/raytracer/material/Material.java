package com.gedim.raytracer.material;

import com.gedim.raytracer.shader.Shader;
import com.gedim.raytracer.util.RGB;

public class Material {

	private RGB color;
	private Shader shader;
	private double ambient;
	private double diffuse;
	private double specularity;
	private double glossiness;
	private double reflection;
	private double transparency;
	private double transparencyIndex;
	private double translucency;

	public Material() {
	}

	public Material(RGB color) {
		this.color = color;
	}

	public RGB getColor() {
		return color;
	}

	public void setColor(RGB color) {
		this.color = color;
	}

	public Shader getShader() {
		return shader;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}

	public double getAmbient() {
		return ambient;
	}

	public void setAmbient(double ambient) {
		this.ambient = ambient;
	}

	public double getDiffuse() {
		return diffuse;
	}

	public void setDiffuse(double diffuse) {
		this.diffuse = diffuse;
	}

	public double getSpecularity() {
		return specularity;
	}

	public void setSpecularity(double specularity) {
		this.specularity = specularity;
	}

	public double getGlossiness() {
		return glossiness;
	}

	public void setGlossiness(double glossiness) {
		this.glossiness = glossiness;
	}

	public double getReflection() {
		return reflection;
	}

	public void setReflection(double reflection) {
		this.reflection = reflection;
	}

	public double getTransparency() {
		return transparency;
	}

	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}

	public double getTransparencyIndex() {
		return transparencyIndex;
	}

	public void setTransparencyIndex(double transparencyIndex) {
		this.transparencyIndex = transparencyIndex;
	}

	public double getTranslucency() {
		return translucency;
	}

	public void setTranslucency(double translucency) {
		this.translucency = translucency;
	}

}
