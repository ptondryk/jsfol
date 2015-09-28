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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Style [fill=" + fill + ", image=" + image + ", stroke="
				+ stroke + ", text=" + text + "]";
	}

}
