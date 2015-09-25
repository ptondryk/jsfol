/**
 * 
 */
package eu.tondryk.jsfol.type;

/**
 * This class corresponds to ol-type <code>ol.Color</code>.
 * 
 * @author ptondryk
 *
 */
public class Color {

	/**
	 * 
	 */
	private String literal;

	/**
	 * 
	 */
	private int green;

	/**
	 * 
	 */
	private int blue;

	/**
	 * 
	 */
	private int red;

	/**
	 * 
	 */
	private float alpha;

	/**
	 * constructor
	 * 
	 * @param green
	 * @param blue
	 * @param red
	 * @param alpha
	 */
	public Color(int green, int blue, int red, float alpha) {
		super();
		this.green = this.adjustColor(green);
		this.blue = this.adjustColor(blue);
		this.red = this.adjustColor(red);
		this.alpha = this.adjustAlpha(alpha);
	}

	/**
	 * constructor
	 * 
	 * @param literal
	 */
	public Color(String literal) {
		this.literal = literal;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isLiteral() {
		return this.literal != null;
	}

	/**
	 * @return the literal
	 */
	public String getLiteral() {
		return literal;
	}

	/**
	 * @return the green
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * @return the blue
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * @return the red
	 */
	public int getRed() {
		return red;
	}

	/**
	 * @return the alpha
	 */
	public float getAlpha() {
		return alpha;
	}

	/**
	 * This method makes sure that the values of blue/green/red are between
	 * 0-255.
	 * 
	 * @param colorValue
	 * @return
	 */
	private int adjustColor(int colorValue) {
		if (colorValue < 0)
			return 0;
		else if (colorValue > 255)
			return 255;
		else
			return colorValue;
	}

	/**
	 * This method makes sure that the value alpha is between 0.0 and 1.0.
	 * 
	 * @param alphaValue
	 * @return
	 */
	private float adjustAlpha(float alphaValue) {
		if (alphaValue < 0.0f)
			return 0.0f;
		else if (alphaValue > 1.0f)
			return 1.0f;
		else
			return alphaValue;
	}
}
