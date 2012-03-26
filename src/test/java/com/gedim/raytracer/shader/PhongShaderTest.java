package com.gedim.raytracer.shader;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gedim.raytracer.Ray;
import com.gedim.raytracer.light.AreaLight;
import com.gedim.raytracer.light.Light;
import com.gedim.raytracer.material.Material;
import com.gedim.raytracer.math.Vector3;
import com.gedim.raytracer.primitive.Sphere;
import com.gedim.raytracer.util.RGB;

/**
 * The class <code>PhongShaderTest</code> contains tests for the class
 * <code>{@link PhongShader}</code>.
 * 
 * @generatedBy CodePro at 26/3/2012 3:03 μμ
 * @author dimtsas
 * @version $Revision: 1.0 $
 */
public class PhongShaderTest {
  /**
   * Run the RGB calculateIntensity(Ray,Primitive,Vector3,Light,double) method
   * test.
   * 
   * @throws Exception
   * 
   * @generatedBy CodePro at 26/3/2012 3:03 μμ
   */
  @Test
  public void testCalculateIntensity() throws Exception {
    PhongShader fixture = new PhongShader();

    Ray ray = new Ray(new Vector3(1, 0, 1));
    Sphere primitive = new Sphere(new Vector3(3, 0, 3), 0.5);
    Material mat = new Material();

    Vector3 normal = primitive.getNormalAt(ray.getDirection()
        .multiply(primitive.intersectRay(ray, 1000).getDistance()));
    Light light = new AreaLight(new Vector3(), new RGB(), 1.0);
    double shadowIntensity = 1.0;

    RGB result = fixture.calculateIntensity(ray,
                                            primitive,
                                            normal,
                                            light,
                                            shadowIntensity);

    // add additional test code here
    assertNotNull(result);
  }

  /**
   * Perform pre-test initialization.
   * 
   * @throws Exception if the initialization fails for some reason
   * 
   * @generatedBy CodePro at 26/3/2012 3:03 μμ
   */
  @Before
  public void setUp() throws Exception {
    // add additional set up code here
  }

  /**
   * Perform post-test clean-up.
   * 
   * @throws Exception if the clean-up fails for some reason
   * 
   * @generatedBy CodePro at 26/3/2012 3:03 μμ
   */
  @After
  public void tearDown() throws Exception {
    // Add additional tear down code here
  }

  /**
   * Launch the test.
   * 
   * @param args the command line arguments
   * 
   * @generatedBy CodePro at 26/3/2012 3:03 μμ
   */
  public static void main(String[] args) {
    new org.junit.runner.JUnitCore().run(PhongShaderTest.class);
  }
}