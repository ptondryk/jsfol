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
	private Double scale;

	/**
	 * 
	 */
	private Double rotation;

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
			Integer offsetY, Double scale, Double roration) {
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
		this.rotation = roration;
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
	 * @return the rotation
	 */
	public Double getRotation() {
		return rotation;
	}

	/**
	 * @param rotation
	 *            the rotation to set
	 */
	public void setRoration(Double rotation) {
		this.rotation = rotation;
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
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + ((offsetX == null) ? 0 : offsetX.hashCode());
		result = prime * result + ((offsetY == null) ? 0 : offsetY.hashCode());
		result = prime * result
				+ ((rotation == null) ? 0 : rotation.hashCode());
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
		result = prime * result + ((stroke == null) ? 0 : stroke.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((textAlign == null) ? 0 : textAlign.hashCode());
		result = prime * result
				+ ((textBaseLine == null) ? 0 : textBaseLine.hashCode());
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
		Text other = (Text) obj;
		if (fill == null) {
			if (other.fill != null)
				return false;
		} else if (!fill.equals(other.fill))
			return false;
		if (font == null) {
			if (other.font != null)
				return false;
		} else if (!font.equals(other.font))
			return false;
		if (offsetX == null) {
			if (other.offsetX != null)
				return false;
		} else if (!offsetX.equals(other.offsetX))
			return false;
		if (offsetY == null) {
			if (other.offsetY != null)
				return false;
		} else if (!offsetY.equals(other.offsetY))
			return false;
		if (rotation == null) {
			if (other.rotation != null)
				return false;
		} else if (!rotation.equals(other.rotation))
			return false;
		if (scale == null) {
			if (other.scale != null)
				return false;
		} else if (!scale.equals(other.scale))
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
		if (textAlign == null) {
			if (other.textAlign != null)
				return false;
		} else if (!textAlign.equals(other.textAlign))
			return false;
		if (textBaseLine == null) {
			if (other.textBaseLine != null)
				return false;
		} else if (!textBaseLine.equals(other.textBaseLine))
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
		return "Text [font=" + font + ", text=" + text + ", textAlign="
				+ textAlign + ", textBaseLine=" + textBaseLine + ", fill="
				+ fill + ", stroke=" + stroke + ", offsetX=" + offsetX
				+ ", offsetY=" + offsetY + ", scale=" + scale + ", rotation="
				+ rotation + "]";
	}

}
