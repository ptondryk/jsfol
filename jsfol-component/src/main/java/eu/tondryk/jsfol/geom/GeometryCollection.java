/**
 * 
 */
package eu.tondryk.jsfol.geom;

import java.util.List;

/**
 * This class corresponds to ol-class <code>ol.geom.GeometryCollection</code>.
 * 
 * @author ptondryk
 *
 */
public class GeometryCollection extends Geometry {

	/**
	 * 
	 */
	private List<Geometry> geometries;

	/**
	 * @param geometries
	 */
	public GeometryCollection(List<Geometry> geometries) {
		super();
		this.geometries = geometries;
	}

	/**
	 * @return the geometries
	 */
	public List<Geometry> getGeometries() {
		return geometries;
	}

	/**
	 * @param geometries
	 *            the geometries to set
	 */
	public void setGeometries(List<Geometry> geometries) {
		this.geometries = geometries;
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
				+ ((geometries == null) ? 0 : geometries.hashCode());
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
		GeometryCollection other = (GeometryCollection) obj;
		if (geometries == null) {
			if (other.geometries != null)
				return false;
		} else if (!geometries.equals(other.geometries))
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
		return "GeometryCollection [geometries=" + geometries + "]";
	}

}
