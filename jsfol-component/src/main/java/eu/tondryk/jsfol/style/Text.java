package eu.tondryk.jsfol.style;

/**
 * This class corresponds to ol-class <code>ol.style.Text</code>.
 * 
 * @author ptondryk
 *
 */
public class Text {

	/**
	 * 
	 */
	private String font;

	/**
	 * 
	 */
	private String text;

	/**
	 * 
	 */
	private String textAlign;

	/**
	 * 
	 */
	private String textBaseLine;

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
	private Integer offsetX;

	/**
	 * 
	 */
	private Integer offsetY;

	/**
	 * 
	 */
	private Integer scale;

	/**
	 * 
	 */
	private Double roration;

	/**
	 * @param font
	 * @param text
	 * @param textAlign
	 * @param textBaseLine
	 * @param fill
	 * @param stroke
	 * @param offsetX
	 * @param offsetY
	 * @param scale
	 * @param roration
	 */
	public Text(String font, String text, String textAlign,
			String textBaseLine, Fill fill, Stroke stroke, Integer offsetX,
			Integer offsetY, Integer scale, Double roration) {
		super();
		this.font = font;
		this.text = text;
		this.textAlign = textAlign;
		this.textBaseLine = textBaseLine;
		this.fill = fill;
		this.stroke = stroke;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.scale = scale;
		this.roration = roration;
	}

	/**
	 * @return the font
	 */
	public String getFont() {
		return font;
	}

	/**
	 * @param font
	 *            the font to set
	 */
	public void setFont(String font) {
		this.font = font;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the textAlign
	 */
	public String getTextAlign() {
		return textAlign;
	}

	/**
	 * @param textAlign
	 *            the textAlign to set
	 */
	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}

	/**
	 * @return the textBaseLine
	 */
	public String getTextBaseLine() {
		return textBaseLine;
	}

	/**
	 * @param textBaseLine
	 *            the textBaseLine to set
	 */
	public void setTextBaseLine(String textBaseLine) {
		this.textBaseLine = textBaseLine;
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
	 * @return the offsetX
	 */
	public Integer getOffsetX() {
		return offsetX;
	}

	/**
	 * @param offsetX
	 *            the offsetX to set
	 */
	public void setOffsetX(Integer offsetX) {
		this.offsetX = offsetX;
	}

	/**
	 * @return the offsetY
	 */
	public Integer getOffsetY() {
		return offsetY;
	}

	/**
	 * @param offsetY
	 *            the offsetY to set
	 */
	public void setOffsetY(Integer offsetY) {
		this.offsetY = offsetY;
	}

	/**
	 * @return the scale
	 */
	public Integer getScale() {
		return scale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(Integer scale) {
		this.scale = scale;
	}

	/**
	 * @return the roration
	 */
	public Double getRotation() {
		return roration;
	}

	/**
	 * @param roration
	 *            the roration to set
	 */
	public void setRoration(Double roration) {
		this.roration = roration;
	}

}
