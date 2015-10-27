/**
 * 
 */
package eu.tondryk.jsfol.geom;

import java.util.ArrayList;
import java.util.List;

/**
 * This class corresponds to ol-class <code>ol.geom.MultiLineString</code>.
 * 
 * @author ptondryk
 *
 */
public class MultiLineString extends SimpleGeometry {

	/**
	 * 
	 */
	private List<LineString> lineStrings;

	/**
	 * empty constructor
	 */
	public MultiLineString() {
		super();
		this.lineStrings = new ArrayList<>();
	}

	/**
	 * constructor
	 * 
	 * @param lineStrings
	 */
	public MultiLineString(List<LineString> lineStrings) {
		super();
		this.lineStrings = lineStrings;
	}

	/**
	 * @return the lineStrings
	 */
	public List<LineString> getLineStrings() {
		return lineStrings;
	}

	/**
	 * @param lineStrings
	 *            the lineStrings to set
	 */
	public void setLineStrings(List<LineString> lineStrings) {
		this.lineStrings = lineStrings;
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
		MultiLineString other = (MultiLineString) obj;
		if (lineStrings == null) {
			if (other.lineStrings != null)
				return false;
		} else if (!lineStrings.equals(other.lineStrings))
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
		return "MultiLineString [lineStrings=" + lineStrings + "]";
	}

}
