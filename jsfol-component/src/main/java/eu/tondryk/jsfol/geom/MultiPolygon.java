package eu.tondryk.jsfol.geom;

import java.util.ArrayList;
import java.util.List;

/**
 * This class corresponds to ol-class <code>ol.geom.MultiPolygon</code>.
 * 
 * @author ptondryk
 *
 */
public class MultiPolygon extends SimpleGeometry {

	/**
	 * 
	 */
	private List<Polygon> polygons;

	/**
	 * empty constructor
	 */
	public MultiPolygon() {
		super();
		this.polygons = new ArrayList<>();
	}

	/**
	 * constructor
	 * 
	 * @param polygons
	 */
	public MultiPolygon(List<Polygon> polygons) {
		super();
		this.polygons = polygons;
	}

	/**
	 * @return the polygons
	 */
	public List<Polygon> getPolygons() {
		return polygons;
	}

	/**
	 * @param polygons
	 *            the polygons to set
	 */
	public void setPolygons(List<Polygon> polygons) {
		this.polygons = polygons;
	}

}
