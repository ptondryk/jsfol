/**
 * 
 */
package eu.tondryk.jsfol.geom;

import java.io.Serializable;
import java.util.List;

import eu.tondryk.jsfol.type.Coordinate;

/**
 * This class corresponds to ol-class <code>ol.geom.Polygon</code>.
 * 
 * @author ptondryk
 *
 */
public class Polygon extends SimpleGeometry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4779488735465618309L;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
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
		Polygon other = (Polygon) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
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
		return "Polygon [coordinates=" + coordinates + "]";
	}

}
