/**
 * 
 */
package eu.tondryk.jsfol.style;

import eu.tondryk.jsfol.type.Color;

/**
 * This class corresponds to ol-class <code>ol.style.Stroke</code>.
 * 
 * @author ptondryk
 *
 */
public class Stroke {

	/**
	 * 
	 */
	private Color color;

	/**
	 * 
	 */
	private String lineCap;

	/**
	 * 
	 */
	private String lineJoin;

	/**
	 * 
	 */
	private Integer[] lineDash;

	/**
	 * 
	 */
	private Integer miterLimit;

	/**
	 * 
	 */
	private Long width;

	/**
	 * @param color
	 * @param lineCap
	 * @param lineJoin
	 * @param lineDash
	 * @param miterLimit
	 * @param width
	 */
	public Stroke(Color color, String lineCap, String lineJoin,
			Integer[] lineDash, Integer miterLimit, Long width) {
		super();
		this.color = color;
		this.lineCap = lineCap;
		this.lineJoin = lineJoin;
		this.lineDash = lineDash;
		this.miterLimit = miterLimit;
		this.width = width;
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

	/**
	 * @return the lineCap
	 */
	public String getLineCap() {
		return lineCap;
	}

	/**
	 * @param lineCap
	 *            the lineCap to set
	 */
	public void setLineCap(String lineCap) {
		this.lineCap = lineCap;
	}

	/**
	 * @return the lineJoin
	 */
	public String getLineJoin() {
		return lineJoin;
	}

	/**
	 * @param lineJoin
	 *            the lineJoin to set
	 */
	public void setLineJoin(String lineJoin) {
		this.lineJoin = lineJoin;
	}

	/**
	 * @return the lineDash
	 */
	public Integer[] getLineDash() {
		return lineDash;
	}

	/**
	 * @param lineDash
	 *            the lineDash to set
	 */
	public void setLineDash(Integer[] lineDash) {
		this.lineDash = lineDash;
	}

	/**
	 * @return the miterLimit
	 */
	public Integer getMiterLimit() {
		return miterLimit;
	}

	/**
	 * @param miterLimit
	 *            the miterLimit to set
	 */
	public void setMiterLimit(Integer miterLimit) {
		this.miterLimit = miterLimit;
	}

	/**
	 * @return the width
	 */
	public Long getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Long width) {
		this.width = width;
	}

}
