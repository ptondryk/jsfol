/**
 * 
 */
package eu.tondryk.jsfol.style;

/**
 * This class corresponds to ol-class <code>ol.style.Circle</code>.
 * 
 * @author ptondryk
 *
 */
public class Circle extends Image {

	/**
	 * 
	 */
	private Fill fill;

	/**
	 * 
	 */
	private Stroke stroke;

	/**
	 * 
	 */
	private Double radius;

	/**
	 * empty constructor
	 */
	public Circle() {
		super();
	}

	/**
	 * @param opacity
	 * @param rotateWithView
	 * @param rotation
	 * @param scale
	 * @param snapToPixel
	 * @param fill
	 * @param stroke
	 * @param radius
	 */
	public Circle(Double opacity, Boolean rotateWithView, Double rotation,
			Double scale, Boolean snapToPixel, Fill fill, Stroke stroke,
			Double radius) {
		super(opacity, rotateWithView, rotation, scale, snapToPixel);
		this.fill = fill;
		this.stroke = stroke;
		this.radius = radius;
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
	 * @return the radius
	 */
	public Double getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(Double radius) {
		this.radius = radius;
	}

}
