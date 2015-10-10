/**
 * 
 */
package eu.tondryk.jsfol.geom;

import java.util.ArrayList;
import java.util.List;

import eu.tondryk.jsfol.type.Coordinate;

/**
 * This class corresponds to ol-class <code>ol.geom.LineString</code>.
 * 
 * @author ptondryk
 *
 */
public class LineString extends SimpleGeometry {

	/**
	 * 
	 */
	private List<Coordinate> coordinates;

	/**
	 * empty constructor
	 */
	public LineString() {
		super();
		this.coordinates = new ArrayList<>();
	}

	/**
	 * @param coordinates
	 */
	public LineString(List<Coordinate> coordinates) {
		super();
		this.coordinates = coordinates;
	}

	/**
	 * @return the coordinates
	 */
	public List<Coordinate> getCoordinates() {
		if (this.coordinates == null) {
			this.coordinates = new ArrayList<>();
		}
		return coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

}
