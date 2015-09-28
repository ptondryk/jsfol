/**
 * 
 */
package eu.tondryk.jsfol.style;

import eu.tondryk.jsfol.type.Color;

/**
 * This class corresponds to ol-class <code>ol.style.Fill</code>.
 * 
 * @author ptondryk
 *
 */
public class Fill {

	/**
	 * 
	 */
	private Color color;

	/**
	 * @param color
	 */
	public Fill(Color color) {
		super();
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fill [color=" + color + "]";
	}

}
