/**
 * 
 */
package eu.tondryk.jsfol;

import java.io.Serializable;

import eu.tondryk.jsfol.geom.Geometry;
import eu.tondryk.jsfol.style.Style;

/**
 * This class corresponds to ol-class <code>ol.Feature</code>.
 * 
 * @author ptondryk
 *
 */
public class Feature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4583832582441524562L;

	/**
	 * string in geo-json format
	 */
	private Geometry geometry;

	/**
	 * style of this feature
	 */
	private Style style;

	/**
	 * empty constructor
	 */
	public Feature() {
		super();
	}

	/**
	 * constructor
	 * 
	 * @param geometry
	 * @param style
	 */
	public Feature(Geometry geometry, Style style) {
		super();
		this.geometry = geometry;
		this.style = style;
	}

	/**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * @param geometry
	 *            the geometry to set
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	/**
	 * @return the style
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(Style style) {
		this.style = style;
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
		Feature other = (Feature) obj;
		if (geometry == null) {
			if (other.geometry != null)
				return false;
		} else if (!geometry.equals(other.geometry))
			return false;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
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
		return "Feature [geometry=" + this.geometry + ", style=" + this.style
				+ "]";
	}

}
