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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (Double.doubleToLongBits(radius) != Double
				.doubleToLongBits(other.radius))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Circle [center=" + center + ", radius=" + radius + "]";
	}

}
