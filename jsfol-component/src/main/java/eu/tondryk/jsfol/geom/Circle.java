/**
 * 
 */
package eu.tondryk.jsfol.geom;

import eu.tondryk.jsfol.type.Coordinate;

/**
 * This class corresponds to ol-class <code>ol.geom.Circle</code>.
 * 
 * @author ptondryk
 *
 */
public class Circle extends SimpleGeometry {

	/**
	 * 
	 */
	private Coordinate center;

	/**
	 * 
	 */
	private double radius;

	/**
	 * constructor
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 */
	public Circle(double x, double y, double radius) {
		super();
		this.center = new Coordinate(x, y);
		this.radius = radius;
	}

	/**
	 * constructor
	 * 
	 * @param center
	 * @param radius
	 */
	public Circle(Coordinate center, double radius) {
		super();
		this.center = center;
		this.radius = radius;
	}

	/**
	 * @return the center
	 */
	public Coordinate getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
	 */
	public void setCenter(Coordinate center) {
		this.center = center;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

}
