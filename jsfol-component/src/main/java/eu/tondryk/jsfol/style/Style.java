/**
 * 
 */
package eu.tondryk.jsfol.style;

/**
 * This class corresponds to ol-class <code>ol.style.Style</code>.
 * 
 * @author ptondryk
 *
 */
public class Style {

	/**
	 * 
	 */
	private Fill fill;

	/**
	 * 
	 */
	private Image image;

	/**
	 * 
	 */
	private Stroke stroke;

	/**
	 * 
	 */
	private Text text;

	/**
	 * empty constructor
	 */
	public Style() {
		super();
	}

	/**
	 * constructor
	 * 
	 * @param fill
	 * @param image
	 * @param stroke
	 * @param text
	 */
	public Style(Fill fill, Image image, Stroke stroke, Text text) {
		super();
		this.fill = fill;
		this.image = image;
		this.stroke = stroke;
		this.text = text;
	}

	/**
	 * @return the fill
	 */
	public Fill getFill() {
		return fill;
	}

	/**
	 * @param fill
	 *            the fill to set
	 */
	public void setFill(Fill fill) {
		this.fill = fill;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the stroke
	 */
	public Stroke getStroke() {
		return stroke;
	}

	/**
	 * @param stroke
	 *            the stroke to set
	 */
	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}

	/**
	 * @return the text
	 */
	public Text getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(Text text) {
		this.text = text;
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
		result = prime * result + ((fill == null) ? 0 : fill.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((stroke == null) ? 0 : stroke.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Style other = (Style) obj;
		if (fill == null) {
			if (other.fill != null)
				return false;
		} else if (!fill.equals(other.fill))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (stroke == null) {
			if (other.stroke != null)
				return false;
		} else if (!stroke.equals(other.stroke))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
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
		return "Style [fill=" + fill + ", image=" + image + ", stroke="
				+ stroke + ", text=" + text + "]";
	}

}
