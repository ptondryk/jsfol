/**
 * 
 */
package eu.tondryk.jsfol.converter;

import java.util.List;

import eu.tondryk.jsfol.Feature;
import eu.tondryk.jsfol.geom.Geometry;
import eu.tondryk.jsfol.geom.GeometryCollection;
import eu.tondryk.jsfol.geom.Polygon;
import eu.tondryk.jsfol.style.Circle;
import eu.tondryk.jsfol.style.Fill;
import eu.tondryk.jsfol.style.Icon;
import eu.tondryk.jsfol.style.Image;
import eu.tondryk.jsfol.style.Stroke;
import eu.tondryk.jsfol.style.Style;
import eu.tondryk.jsfol.style.Text;
import eu.tondryk.jsfol.type.Color;
import eu.tondryk.jsfol.type.Coordinate;
import eu.tondryk.jsfol.type.Size;

/**
 * @author ptondryk
 *
 */
public class JsfolFormatter {

	/**
	 * This method converts given {@link Feature} object into string
	 * representing definition of javascript <code>ol.Feature</code>.
	 * 
	 * @param feature
	 * @return
	 */
	public static String convertFeature(Feature feature) {
		return "new ol.Feature({"
				+ JsfolFormatter.convertGeometry(feature.getGeometry()) + "})";
	}

	/**
	 * This method convert given {@link Style} object into string representing
	 * definition of javascript <code>ol.style.Style</code>.
	 * 
	 * @param style
	 * @return
	 */
	public static String convertStyle(Style style) {
		String result = "new ol.style.Style({";
		if (style.getFill() != null) {
			result += JsfolFormatter.convertFill(style.getFill()) + ",";
		}
		if (style.getImage() != null) {
			result += JsfolFormatter.convertImage(style.getImage()) + ",";
		}
		if (style.getStroke() != null) {
			result += JsfolFormatter.convertStroke(style.getStroke()) + ",";
		}
		if (style.getText() != null) {
			result += JsfolFormatter.convertText(style.getText());
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		result += "})";
		return result;
	}

	/**
	 * This method converts given {@link Geometry} object into string
	 * representing definition of javascript <code>ol.geom.Geometry</code>.
	 * 
	 * @param geometry
	 * @return
	 */
	private static String convertGeometry(Geometry geometry) {
		String result = "geometry : ";
		if (geometry instanceof Polygon) {
			result += JsfolFormatter.convertPolygon((Polygon) geometry);
		} else {
			result += JsfolFormatter
					.convertGeometryCollection((GeometryCollection) geometry);
		}
		// TODO implement other SimpleGeometry types
		return result;
	}

	/**
	 * TODO comment
	 * 
	 * @param polygon
	 */
	private static String convertPolygon(Polygon polygon) {
		String result = "new ol.geom.Polygon(";
		String coordinatesArray = "";
		for (List<Coordinate> coordinates : polygon.getCoordinates()) {
			if (!coordinatesArray.isEmpty()) {
				coordinatesArray += ", ";
			}
			coordinatesArray += JsfolFormatter.convertJsArray(coordinates);
		}
		result += "[" + coordinatesArray + "])";
		return result;
	}

	/**
	 * TODO comment
	 * 
	 * @param geometry
	 */
	private static String convertGeometryCollection(
			GeometryCollection geometries) {
		String result = "";
		for (Geometry geometry : geometries.getGeometries()) {
			if (!result.isEmpty()) {
				result += ",";
			}
			result += JsfolFormatter.convertGeometry(geometry);
		}
		return "[" + result + "]";
	}

	/**
	 * This method convert given {@link Style} object into string representing
	 * definition of javascript <code>ol.style.Style</code>.
	 * 
	 * @param style
	 * @return
	 */
	private static String convertFill(Fill fill) {
		String result = "fill : new ol.style.Fill({";
		if (fill.getColor() != null) {
			result += "color : '"
					+ JsfolFormatter.convertColor(fill.getColor()) + "'";
		}
		result += "})";
		return result;
	}

	/**
	 * This method convert given {@link Image} object into string representing
	 * definition of javascript <code>ol.style.Image</code>.
	 * 
	 * @param style
	 * @return
	 */
	private static String convertImage(Image image) {
		String result = null;
		if (image instanceof Icon) {
			result = JsfolFormatter.convertIcon((Icon) image);
		} else if (image instanceof Circle) {
			result = JsfolFormatter.convertCircle((Circle) image);
		}
		return result;
	}

	/**
	 * TODO comment
	 * 
	 * @param circle
	 * @return
	 */
	private static String convertCircle(Circle circle) {
		String result = "image : new ol.style.Circle({";
		if (circle.getOpacity() != null) {
			result += "opacity : " + circle.getOpacity() + ",";
		}
		if (circle.getRadius() != null) {
			result += "radius : " + circle.getRadius() + ",";
		}
		if (circle.getRotation() != null) {
			result += "rotation : " + circle.getRotation() + ",";
		}
		if (circle.getScale() != null) {
			result += "scale : " + circle.getScale() + ",";
		}
		if (circle.getFill() != null) {
			result += "fill : " + JsfolFormatter.convertFill(circle.getFill())
					+ ",";
		}
		if (circle.getStroke() != null) {
			result += "stroke : "
					+ JsfolFormatter.convertStroke(circle.getStroke());
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		result += "})";
		return result;
	}

	/**
	 * TODO comment
	 * 
	 * @param icon
	 * @return
	 */
	private static String convertIcon(Icon icon) {
		String result = "image : new ol.style.Icon({";
		if (icon.getAnchor() != null) {
			result += "anchor : "
					+ JsfolFormatter.convertJsArray(icon.getAnchor()) + ",";
		}
		if (icon.getAnchorOrigin() != null) {
			result += "anchorOrigin : '" + icon.getAnchorOrigin() + "',";
		}
		if (icon.getAnchorXUnits() != null) {
			result += "anchorXUnits : '" + icon.getAnchorXUnits() + "',";
		}
		if (icon.getAnchorYUnits() != null) {
			result += "anchorYUnits : '" + icon.getAnchorYUnits() + "',";
		}
		if (icon.getCrossOrigin() != null) {
			result += "crossOrigin : '" + icon.getCrossOrigin() + "',";
		}
		if (icon.getImg() != null) {
			result += "img : '" + icon.getImg() + "',";
		}
		if (icon.getOffsetOrigin() != null) {
			result += "offsetOrigin : '" + icon.getOffsetOrigin() + "',";
		}
		if (icon.getOpacity() != null) {
			result += "opacity : " + icon.getOpacity() + ",";
		}
		if (icon.getRotation() != null) {
			result += "rotation : " + icon.getRotation() + ",";
		}
		if (icon.getScale() != null) {
			result += "scale : " + icon.getScale() + ",";
		}
		if (icon.getSrc() != null) {
			result += "src : '" + icon.getSrc() + "',";
		}
		if (icon.getImgSize() != null) {
			result += "imgSize : "
					+ JsfolFormatter.convertSize(icon.getImgSize()) + ",";
		}
		if (icon.getSize() != null) {
			result += "size : " + JsfolFormatter.convertSize(icon.getSize())
					+ ",";
		}
		if (icon.getOffset() != null) {
			result += "offset : "
					+ JsfolFormatter.convertJsArray(icon.getOffset());
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		result += "})";
		return result;
	}

	/**
	 * This method convert given {@link Stroke} object into string representing
	 * definition of javascript <code>ol.style.Stroke</code>.
	 * 
	 * @param style
	 * @return
	 */
	private static String convertStroke(Stroke stroke) {
		String result = "stroke : new ol.style.Stroke({";
		if (stroke.getColor() != null) {
			result += "color : '"
					+ JsfolFormatter.convertColor(stroke.getColor()) + "',";
		}
		if (stroke.getLineCap() != null) {
			result += "lineCap : '" + stroke.getLineCap() + "',";
		}
		if (stroke.getLineJoin() != null) {
			result += "lineJoin : '" + stroke.getLineJoin() + "',";
		}
		if (stroke.getMiterLimit() != null) {
			result += "miterLimit : " + stroke.getMiterLimit() + ",";
		}
		if (stroke.getWidth() != null) {
			result += "width : " + stroke.getWidth() + ",";
		}
		if (stroke.getLineDash() != null) {
			result += "lineDash : "
					+ JsfolFormatter.convertJsArray(stroke.getLineDash()) + ",";
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		result += "})";
		return result;
	}

	/**
	 * This method convert given {@link Text} object into string representing
	 * definition of javascript <code>ol.style.Text</code>.
	 * 
	 * @param style
	 * @return
	 */
	private static String convertText(Text text) {
		String result = "text : new ol.style.Text({";
		if (text.getFill() != null) {
			result += "fill : " + JsfolFormatter.convertFill(text.getFill())
					+ ",";
		}
		if (text.getFont() != null) {
			result += "font : '" + text.getFont() + "',";
		}
		if (text.getText() != null) {
			result += "text : '" + text.getText() + "',";
		}
		if (text.getTextAlign() != null) {
			result += "textAlign : '" + text.getTextAlign() + "',";
		}
		if (text.getTextBaseLine() != null) {
			result += "textBaseLine : '" + text.getTextBaseLine() + "',";
		}
		if (text.getOffsetX() != null) {
			result += "offsetX : " + text.getOffsetX() + ",";
		}
		if (text.getOffsetY() != null) {
			result += "offsetY : " + text.getOffsetY() + ",";
		}
		if (text.getRotation() != null) {
			result += "rotation : " + text.getRotation() + ",";
		}
		if (text.getScale() != null) {
			result += "scale : " + text.getScale() + ",";
		}
		if (text.getStroke() != null) {
			result += "stroke : "
					+ JsfolFormatter.convertStroke(text.getStroke()) + ",";
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		result += "})";
		return result;
	}

	/**
	 * TODO comment
	 * 
	 * @param color
	 * @return
	 */
	private static String convertColor(Color color) {
		String result = null;
		if (color.isLiteral()) {
			result = color.getLiteral();
		} else {
			result = "rgba(" + color.getRed() + ", " + color.getGreen() + ", "
					+ color.getBlue() + ", " + color.getAlpha() + ")";
		}
		return result;
	}

	/**
	 * TODO
	 * 
	 * @param size
	 * @return
	 */
	private static String convertSize(Size size) {
		return "[" + size.getWidth() + ", " + size.getHeight() + "]";
	}

	/**
	 * TODO comment
	 * 
	 * @param coord
	 * @return
	 */
	private static String convertCoordinate(Coordinate coord) {
		return "ol.proj.transform([" + coord.getX() + ", " + coord.getY()
				+ "], 'EPSG:4326', 'EPSG:3857')";
	}

	/**
	 * TODO comment
	 * 
	 * @param array
	 * @return
	 */
	private static String convertJsArray(Integer[] array) {
		String result = "";
		for (Integer element : array) {
			if (!result.isEmpty()) {
				result += ", ";
			}
			result += element;
		}
		return "[" + result + "]";
	}

	/**
	 * TODO comment
	 * 
	 * @param array
	 * @return
	 */
	private static String convertJsArray(Double[] array) {
		String result = "";
		for (Double element : array) {
			if (!result.isEmpty()) {
				result += ", ";
			}
			result += element;
		}
		return "[" + result + "]";
	}

	/**
	 * TODO comment
	 * 
	 * @param array
	 * @return
	 */
	private static String convertJsArray(List<Coordinate> array) {
		String result = "";
		for (Coordinate element : array) {
			if (!result.isEmpty()) {
				result += ", ";
			}
			result += JsfolFormatter.convertCoordinate(element);
		}
		return "[" + result + "]";
	}
}
