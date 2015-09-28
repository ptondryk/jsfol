package eu.tondryk.jsfol.style;

/**
 * This class corresponds to ol-class <code>ol.style.Image</code>.
 * 
 * @author ptondryk
 *
 */
public abstract class Image {

	/**
	 * 
	 */
	private Double opacity;

	/**
	 * 
	 */
	private Boolean rotateWithView;

	/**
	 * 
	 */
	private Double rotation;

	/**
	 * 
	 */
	private Double scale;

	/**
	 * 
	 */
	private Boolean snapToPixel;

	/**
	 * empty constructor
	 */
	public Image() {
		super();
	}

	/**
	 * @param opacity
	 * @param rotateWithView
	 * @param rotation
	 * @param scale
	 * @param snapToPixel
	 */
	public Image(Double opacity, Boolean rotateWithView, Double rotation,
			Double scale, Boolean snapToPixel) {
		super();
		this.opacity = opacity;
		this.rotateWithView = rotateWithView;
		this.rotation = rotation;
		this.scale = scale;
		this.snapToPixel = snapToPixel;
	}

	/**
	 * @return the opacity
	 */
	public Double getOpacity() {
		return opacity;
	}

	/**
	 * @param opacity
	 *            the opacity to set
	 */
	public void setOpacity(Double opacity) {
		this.opacity = opacity;
	}

	/**
	 * @return the rotateWithView
	 */
	public Boolean isRotateWithView() {
		return rotateWithView;
	}

	/**
	 * @param rotateWithView
	 *            the rotateWithView to set
	 */
	public void setRotateWithView(Boolean rotateWithView) {
		this.rotateWithView = rotateWithView;
	}

	/**
	 * @return the rotation
	 */
	public Double getRotation() {
		return rotation;
	}

	/**
	 * @param rotation
	 *            the rotation to set
	 */
	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	/**
	 * @return the scale
	 */
	public Double getScale() {
		return scale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(Double scale) {
		this.scale = scale;
	}

	/**
	 * @return the snapToPixel
	 */
	public Boolean isSnapToPixel() {
		return snapToPixel;
	}

	/**
	 * @param snapToPixel
	 *            the snapToPixel to set
	 */
	public void setSnapToPixel(Boolean snapToPixel) {
		this.snapToPixel = snapToPixel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Image [opacity=" + opacity + ", rotateWithView="
				+ rotateWithView + ", rotation=" + rotation + ", scale="
				+ scale + ", snapToPixel=" + snapToPixel + "]";
	}

}
