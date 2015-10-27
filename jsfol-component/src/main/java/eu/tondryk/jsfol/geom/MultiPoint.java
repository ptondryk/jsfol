/**
 * 
 */
package eu.tondryk.jsfol.geom;

import java.util.ArrayList;
import java.util.List;

/**
 * This class corresponds to ol-class <code>ol.geom.MultiPoint</code>.
 * 
 * @author ptondryk
 *
 */
public class MultiPoint extends SimpleGeometry {

	/**
	 * 
	 */
	private List<Point> points;

	/**
	 * empty constructor
	 */
	public MultiPoint() {
		super();
		this.points = new ArrayList<>();
	}

	/**
	 * constructor
	 * 
	 * @param points
	 */
	public MultiPoint(List<Point> points) {
		super();
		this.points = points;
	}

	/**
	 * @return the points
	 */
	public List<Point> getPoints() {
		return points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(List<Point> points) {
		this.points = points;
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
		MultiPoint other = (MultiPoint) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
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
		return "MultiPoint [points=" + points + "]";
	}

}
