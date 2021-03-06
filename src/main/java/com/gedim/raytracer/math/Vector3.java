package com.gedim.raytracer.math;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Vector3 {

  private double x, y, z;

  public Vector3() {
    this(0.0, 0.0, 0.0);
  }

  public Vector3(double x, double y, double z) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector3(Vector3 vector) {
    this.x = vector.getX();
    this.y = vector.getY();
    this.z = vector.getZ();
  }

  public Vector3 add(final Vector3 vec) {
    return new Vector3(this.x + vec.x, this.y + vec.y, this.z + vec.z);
  }

  public Vector3 subtract(final Vector3 vec) {
    return new Vector3(this.x - vec.x, this.y - vec.y, this.z - vec.z);
  }

  public Vector3 multiply(final double value) {
    return new Vector3(this.x * value, this.y * value, this.z * value);
  }

  public Vector3 divide(final double value) {
    return new Vector3(this.x / value, this.y / value, this.z / value);
  }

  public Vector3 crossProduct(final Vector3 vec) {
    double x = (this.y * vec.z) - (this.z * vec.y);
    double y = (this.z * vec.x) - (this.x * vec.z);
    double z = (this.x * vec.y) - (this.y * vec.x);
    return new Vector3(x, y, z);
  }

  public double dotProduct(final Vector3 vec) {
    return (this.x * vec.x) + (this.y * vec.y) + (this.z * vec.z);
  }

  public double length() {
    return Math.sqrt(this.dotProduct(this));
  }

  public Vector3 normalize() {
    double length = this.length();
    if (length == 0.0) {
      return new Vector3();
    } else {
      return this.divide(length);
    }
  }

  public Vector3 negate() {
    return new Vector3(-x, -y, -z);
  }

  public Vector3 negateLocal() {
    this.x = -x;
    this.y = -y;
    this.z = -z;
    return this;
  }

  public Vector3 getReflected(Vector3 normal) {
    Vector3 reflectedVector = this.subtract(normal.multiply(2.0 * normal.dotProduct(this)));
    return reflectedVector;
  }

  @Override
  public String toString() {
    return "Vector3{" + x + ", " + y + ", " + z + "}";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }

    Vector3 rhs = (Vector3) obj;
    return new EqualsBuilder().append(x, rhs.x)
        .append(y, rhs.y)
        .append(z, rhs.z)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).appendSuper(super.hashCode())
        .append(x)
        .append(y)
        .append(z)
        .toHashCode();
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getZ() {
    return z;
  }

  public void setZ(double z) {
    this.z = z;
  }
}
