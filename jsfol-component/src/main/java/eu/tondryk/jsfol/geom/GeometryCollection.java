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

}
