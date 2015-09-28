/**
 * 
 */
package eu.tondryk.jsfol.geom;

import java.util.List;

import eu.tondryk.jsfol.type.Coordinate;

/**
 * This class corresponds to ol-class <code>ol.geom.Polygon</code>.
 * 
 * @author ptondryk
 *
 */
public class Polygon extends SimpleGeometry {

	/**
	 * 
	 */
	private List<List<Coordinate>> coordinates;

	/**
	 * @param coordinates
	 */
	public Polygon(List<List<Coordinate>> coordinates) {
		super();
		this.coordinates = coordinates;
	}

	/**
	 * @return the coordinates
	 */
	public List<List<Coordinate>> getCoordinates() {
		return this.coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	public void setCoordinates(List<List<Coordinate>> coordinates) {
		this.coordinates = coordinates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Polygon [coordinates=" + coordinates + "]";
	}

}
