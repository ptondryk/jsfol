/**
 * 
 */
package eu.tondryk.jsfol.converter;

import java.util.List;

import eu.tondryk.jsfol.Feature;
import eu.tondryk.jsfol.geom.Geometry;
import eu.tondryk.jsfol.geom.GeometryCollection;
import eu.tondryk.jsfol.geom.LineString;
import eu.tondryk.jsfol.geom.MultiLineString;
import eu.tondryk.jsfol.geom.MultiPoint;
import eu.tondryk.jsfol.geom.MultiPolygon;
import eu.tondryk.jsfol.geom.Point;
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
		return "new ol.Feature({geometry : "
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
		String result = "";
		if (geometry instanceof Polygon) {
			result += JsfolFormatter.convertPolygon((Polygon) geometry);
		} else if (geometry instanceof Point) {
			result += JsfolFormatter.convertPoint((Point) geometry);
		} else if (geometry instanceof eu.tondryk.jsfol.geom.Circle) {
			result += JsfolFormatter
					.convertCircle((eu.tondryk.jsfol.geom.Circle) geometry);
		} else if (geometry instanceof LineString) {
			result += JsfolFormatter.convertLineString((LineString) geometry);
		} else if (geometry instanceof MultiPoint) {
			result += JsfolFormatter.convertMultiPoint((MultiPoint) geometry);
		} else if (geometry instanceof MultiLineString) {
			result += JsfolFormatter
					.convertMultiLineString((MultiLineString) geometry);
		} else if (geometry instanceof MultiPolygon) {
			result += JsfolFormatter
					.convertMultiPolygon((MultiPolygon) geometry);
		} else {
			result += JsfolFormatter
					.convertGeometryCollection((GeometryCollection) geometry);
		}
		return result;
	}

	/**
	 * This method convert given {@link eu.tondryk.jsfol.geom.Circle} object
	 * into string representing definition of javascript
	 * <code>ol.geom.Circle</code>.
	 * 
	 * @param circle
	 * @return
	 */
	private static String convertCircle(eu.tondryk.jsfol.geom.Circle circle) {
		return "new ol.geom.Circle("
				+ JsfolFormatter.convertCoordinate(circle.getCenter()) + ","
				+ circle.getRadius() + ")";
	}

	/**
	 * This method convert given {@link Point} object into string representing
	 * definition of javascript <code>ol.geom.Point</code>.
	 * 
	 * @param point
	 * @return
	 */
	private static String convertPoint(Point point) {
		return "new ol.geom.Point("
				+ JsfolFormatter.convertCoordinate(point.getCoordinate()) + ")";
	}

	/**
	 * This method convert given {@link LineString} object into string
	 * representing definition of javascript <code>ol.geom.LineString</code>.
	 * 
	 * @param lineString
	 * @return
	 */
	private static String convertLineString(LineString lineString) {
		return "new ol.geom.LineString("
				+ JsfolFormatter.convertJsArray(lineString.getCoordinates())
				+ ")";
	}

	/**
	 * This method convert given {@link Polygon} object into string representing
	 * definition of javascript <code>ol.geom.Polygon</code>.
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
	 * This method convert given {@link MultiPoint} object into string
	 * representing definition of javascript <code>ol.geom.MutliPoint</code>.
	 * 
	 * @param multiPoint
	 * @return
	 */
	private static String convertMultiPoint(MultiPoint multiPoint) {
		String points = "";
		for (Point point : multiPoint.getPoints()) {
			if (!points.isEmpty()) {
				points += ",";
			}
			points += JsfolFormatter.convertCoordinate(point.getCoordinate());
		}
		return "new ol.geom.MultiPoint([" + points + "])";
	}

	/**
	 * This method convert given {@link MultiLineString} object into string
	 * representing definition of javascript
	 * <code>ol.geom.MultiLineString</code>.
	 * 
	 * @param multiLineString
	 * @return
	 */
	private static String convertMultiLineString(MultiLineString multiLineString) {
		String lineStrings = "";
		for (LineString lineString : multiLineString.getLineStrings()) {
			if (!lineStrings.isEmpty()) {
				lineStrings += ",";
			}
			lineStrings += JsfolFormatter.convertJsArray(lineString
					.getCoordinates());
		}
		return "new ol.geom.MultiLineString([" + lineStrings + "])";
	}

	/**
	 * This method convert given {@link MultiPolygon} object into string
	 * representing definition of javascript <code>ol.geom.MultiPolygon</code>.
	 * 
	 * @param multiPolygon
	 * @return
	 */
	private static String convertMultiPolygon(MultiPolygon multiPolygon) {
		String polygons = "";
		for (Polygon polygon : multiPolygon.getPolygons()) {
			if (!polygons.isEmpty()) {
				polygons += ",";
			}
			String tmp = "";
			for (List<Coordinate> linearRing : polygon.getCoordinates()) {
				if (!tmp.isEmpty()) {
					tmp += ",";
				}
				tmp += JsfolFormatter.convertJsArray(linearRing);
			}
			polygons += "[" + tmp + "]";
		}
		return "new ol.geom.MultiPolygon([" + polygons + "])";
	}

	/**
	 * This method convert given {@link GeometryCollection} object into string
	 * representing definition of javascript
	 * <code>ol.geom.GeometryCollection</code>.
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
		return "new ol.geom.GeometryCollection([" + result + "])";
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
	 * This method convert given {@link Circle} object into string representing
	 * definition of javascript <code>ol.style.Circle</code>.
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
			result += JsfolFormatter.convertFill(circle.getFill()) + ",";
		}
		if (circle.getStroke() != null) {
			result += JsfolFormatter.convertStroke(circle.getStroke());
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		result += "})";
		return result;
	}

	/**
	 * This method convert given {@link Icon} object into string representing
	 * definition of javascript <code>ol.style.Icon</code>.
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
			result += JsfolFormatter.convertFill(text.getFill()) + ",";
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
			result += JsfolFormatter.convertStroke(text.getStroke()) + ",";
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		result += "})";
		return result;
	}

	/**
	 * This method convert given {@link Color} object into string representing
	 * definition of javascript <code>ol.Color</code>.
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
	 * This method convert given {@link Size} object into string representing
	 * definition of javascript array containing 2 elements: width and heigth.
	 * 
	 * @param size
	 * @return
	 */
	private static String convertSize(Size size) {
		return "[" + size.getWidth() + ", " + size.getHeight() + "]";
	}

	/**
	 * This method convert given {@link Coordinate} object into string
	 * representing definition of javascript array containing 2 elements: x
	 * (logitude) and y (latitude).
	 * 
	 * @param coord
	 * @return
	 */
	private static String convertCoordinate(Coordinate coord) {
		return "[" + coord.getX() + ", " + coord.getY() + "]";
	}

	/**
	 * This method convert given {@link Object} array into string representing
	 * definition of javascript array.
	 * 
	 * @param array
	 * @return
	 */
	private static String convertJsArray(Object[] array) {
		String result = "";
		for (Object element : array) {
			if (!result.isEmpty()) {
				result += ", ";
			}
			if (element instanceof Object[]) {
				result += JsfolFormatter.convertJsArray((Object[]) element);
			} else {
				result += element;
			}
		}
		return "[" + result + "]";
	}

	/**
	 * This method convert given {@link Coordinate} array into string
	 * representing definition of javascript array.
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
