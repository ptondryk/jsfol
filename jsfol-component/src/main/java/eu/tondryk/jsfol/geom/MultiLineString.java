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

}
