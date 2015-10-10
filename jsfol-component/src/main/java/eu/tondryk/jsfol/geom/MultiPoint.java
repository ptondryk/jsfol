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

}
