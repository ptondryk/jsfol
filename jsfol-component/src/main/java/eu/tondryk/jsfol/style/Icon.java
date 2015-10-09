/**
 * 
 */
package eu.tondryk.jsfol.style;

import java.util.Arrays;

import eu.tondryk.jsfol.type.Size;

/**
 * This class corresponds to ol-class <code>ol.style.Icon</code>.
 * 
 * @author ptondryk
 *
 */
public class Icon extends Image {

	/**
	 * 
	 */
	private Double[] anchor;

	/**
	 * 
	 */
	private String anchorOrigin;

	/**
	 * 
	 */
	private String anchorXUnits;

	/**
	 * 
	 */
	private String anchorYUnits;

	/**
	 * 
	 */
	private String crossOrigin;

	/**
	 * 
	 */
	private String img;

	/**
	 * 
	 */
	private Double[] offset;

	/**
	 * 
	 */
	private String offsetOrigin;

	/**
	 * 
	 */
	private Size size;

	/**
	 * 
	 */
	private Size imgSize;

	/**
	 * 
	 */
	private String src;

	/**
	 * empty constructor
	 */
	public Icon() {
		super();
	}

	/**
	 * constructor
	 * 
	 * @param opacity
	 * @param rotateWithView
	 * @param rotation
	 * @param scale
	 * @param snapToPixel
	 * @param anchor
	 * @param anchorOrigin
	 * @param anchorXUnits
	 * @param anchorYUnits
	 * @param crossOrigin
	 * @param img
	 * @param offset
	 * @param offsetOrigin
	 * @param size
	 * @param imgSize
	 * @param src
	 */
	public Icon(Double opacity, Boolean rotateWithView, Double rotation,
			Double scale, Boolean snapToPixel, Double[] anchor,
			String anchorOrigin, String anchorXUnits, String anchorYUnits,
			String crossOrigin, String img, Double[] offset,
			String offsetOrigin, Size size, Size imgSize, String src) {
		super(opacity, rotateWithView, rotation, scale, snapToPixel);
		this.anchor = anchor;
		this.anchorOrigin = anchorOrigin;
		this.anchorXUnits = anchorXUnits;
		this.anchorYUnits = anchorYUnits;
		this.crossOrigin = crossOrigin;
		this.img = img;
		this.offset = offset;
		this.offsetOrigin = offsetOrigin;
		this.size = size;
		this.imgSize = imgSize;
		this.src = src;
	}

	/**
	 * @return the anchor
	 */
	public Double[] getAnchor() {
		return anchor;
	}

	/**
	 * @param anchor
	 *            the anchor to set
	 */
	public void setAnchor(Double[] anchor) {
		this.anchor = anchor;
	}

	/**
	 * @return the anchorOrigin
	 */
	public String getAnchorOrigin() {
		return anchorOrigin;
	}

	/**
	 * @param anchorOrigin
	 *            the anchorOrigin to set
	 */
	public void setAnchorOrigin(String anchorOrigin) {
		this.anchorOrigin = anchorOrigin;
	}

	/**
	 * @return the anchorXUnits
	 */
	public String getAnchorXUnits() {
		return anchorXUnits;
	}

	/**
	 * @param anchorXUnits
	 *            the anchorXUnits to set
	 */
	public void setAnchorXUnits(String anchorXUnits) {
		this.anchorXUnits = anchorXUnits;
	}

	/**
	 * @return the anchorYUnits
	 */
	public String getAnchorYUnits() {
		return anchorYUnits;
	}

	/**
	 * @param anchorYUnits
	 *            the anchorYUnits to set
	 */
	public void setAnchorYUnits(String anchorYUnits) {
		this.anchorYUnits = anchorYUnits;
	}

	/**
	 * @return the crossOrigin
	 */
	public String getCrossOrigin() {
		return crossOrigin;
	}

	/**
	 * @param crossOrigin
	 *            the crossOrigin to set
	 */
	public void setCrossOrigin(String crossOrigin) {
		this.crossOrigin = crossOrigin;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img
	 *            the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the offset
	 */
	public Double[] getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(Double[] offset) {
		this.offset = offset;
	}

	/**
	 * @return the offsetOrigin
	 */
	public String getOffsetOrigin() {
		return offsetOrigin;
	}

	/**
	 * @param offsetOrigin
	 *            the offsetOrigin to set
	 */
	public void setOffsetOrigin(String offsetOrigin) {
		this.offsetOrigin = offsetOrigin;
	}

	/**
	 * @return the size
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Size size) {
		this.size = size;
	}

	/**
	 * @return the imgSize
	 */
	public Size getImgSize() {
		return imgSize;
	}

	/**
	 * @param imgSize
	 *            the imgSize to set
	 */
	public void setImgSize(Size imgSize) {
		this.imgSize = imgSize;
	}

	/**
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src
	 *            the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
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
		result = prime * result + Arrays.hashCode(anchor);
		result = prime * result
				+ ((anchorOrigin == null) ? 0 : anchorOrigin.hashCode());
		result = prime * result
				+ ((anchorXUnits == null) ? 0 : anchorXUnits.hashCode());
		result = prime * result
				+ ((anchorYUnits == null) ? 0 : anchorYUnits.hashCode());
		result = prime * result
				+ ((crossOrigin == null) ? 0 : crossOrigin.hashCode());
		result = prime * result + ((img == null) ? 0 : img.hashCode());
		result = prime * result + ((imgSize == null) ? 0 : imgSize.hashCode());
		result = prime * result + Arrays.hashCode(offset);
		result = prime * result
				+ ((offsetOrigin == null) ? 0 : offsetOrigin.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((src == null) ? 0 : src.hashCode());
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
		Icon other = (Icon) obj;
		if (!Arrays.equals(anchor, other.anchor))
			return false;
		if (anchorOrigin == null) {
			if (other.anchorOrigin != null)
				return false;
		} else if (!anchorOrigin.equals(other.anchorOrigin))
			return false;
		if (anchorXUnits == null) {
			if (other.anchorXUnits != null)
				return false;
		} else if (!anchorXUnits.equals(other.anchorXUnits))
			return false;
		if (anchorYUnits == null) {
			if (other.anchorYUnits != null)
				return false;
		} else if (!anchorYUnits.equals(other.anchorYUnits))
			return false;
		if (crossOrigin == null) {
			if (other.crossOrigin != null)
				return false;
		} else if (!crossOrigin.equals(other.crossOrigin))
			return false;
		if (img == null) {
			if (other.img != null)
				return false;
		} else if (!img.equals(other.img))
			return false;
		if (imgSize == null) {
			if (other.imgSize != null)
				return false;
		} else if (!imgSize.equals(other.imgSize))
			return false;
		if (!Arrays.equals(offset, other.offset))
			return false;
		if (offsetOrigin == null) {
			if (other.offsetOrigin != null)
				return false;
		} else if (!offsetOrigin.equals(other.offsetOrigin))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (src == null) {
			if (other.src != null)
				return false;
		} else if (!src.equals(other.src))
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
		return "Icon [anchor=" + Arrays.toString(anchor) + ", anchorOrigin="
				+ anchorOrigin + ", anchorXUnits=" + anchorXUnits
				+ ", anchorYUnits=" + anchorYUnits + ", crossOrigin="
				+ crossOrigin + ", img=" + img + ", offset="
				+ Arrays.toString(offset) + ", offsetOrigin=" + offsetOrigin
				+ ", size=" + size + ", imgSize=" + imgSize + ", src=" + src
				+ "]";
	}

}
