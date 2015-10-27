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
		MultiPolygon other = (MultiPolygon) obj;
		if (polygons == null) {
			if (other.polygons != null)
				return false;
		} else if (!polygons.equals(other.polygons))
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
		return "MultiPolygon [polygons=" + polygons + "]";
	}

}
