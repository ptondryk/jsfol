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

	/* (non-Javadoc)
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
		LineString other = (LineString) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LineString [coordinates=" + coordinates + "]";
	}

}
