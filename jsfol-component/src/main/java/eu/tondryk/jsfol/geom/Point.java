/**
 * 
 */
package eu.tondryk.jsfol.geom;

import java.io.Serializable;

import eu.tondryk.jsfol.type.Coordinate;

/**
 * This class corresponds to ol-class <code>ol.geom.Point</code>.
 * 
 * @author ptondryk
 *
 */
public class Point extends SimpleGeometry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -707142456452626827L;
	/**
	 * 
	 */
	private Coordinate coordinate;

	/**
	 * constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		super();
		this.coordinate = new Coordinate(x, y);
	}

	/**
	 * constructor
	 * 
	 * @param coordinate
	 */
	public Point(Coordinate coordinate) {
		super();
		this.coordinate = coordinate;
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 *            the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinate == null) ? 0 : coordinate.hashCode());
		return result;
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
		Point other = (Point) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
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
		return "Point [coordinate=" + coordinate + "]";
	}

}
