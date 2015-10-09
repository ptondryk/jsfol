/**
 * 
 */
package eu.tondryk.jsfol.style;

import java.util.Arrays;

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
	private Integer width;

	/**
	 * @param color
	 * @param lineCap
	 * @param lineJoin
	 * @param lineDash
	 * @param miterLimit
	 * @param width
	 */
	public Stroke(Color color, String lineCap, String lineJoin,
			Integer[] lineDash, Integer miterLimit, Integer width) {
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
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
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
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((lineCap == null) ? 0 : lineCap.hashCode());
		result = prime * result + Arrays.hashCode(lineDash);
		result = prime * result
				+ ((lineJoin == null) ? 0 : lineJoin.hashCode());
		result = prime * result
				+ ((miterLimit == null) ? 0 : miterLimit.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		Stroke other = (Stroke) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (lineCap == null) {
			if (other.lineCap != null)
				return false;
		} else if (!lineCap.equals(other.lineCap))
			return false;
		if (!Arrays.equals(lineDash, other.lineDash))
			return false;
		if (lineJoin == null) {
			if (other.lineJoin != null)
				return false;
		} else if (!lineJoin.equals(other.lineJoin))
			return false;
		if (miterLimit == null) {
			if (other.miterLimit != null)
				return false;
		} else if (!miterLimit.equals(other.miterLimit))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
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
		return "Stroke [color=" + color + ", lineCap=" + lineCap
				+ ", lineJoin=" + lineJoin + ", lineDash="
				+ Arrays.toString(lineDash) + ", miterLimit=" + miterLimit
				+ ", width=" + width + "]";
	}

}
