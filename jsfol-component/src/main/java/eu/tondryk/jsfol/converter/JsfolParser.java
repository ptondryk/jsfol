/**
 * 
 */
package eu.tondryk.jsfol.converter;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eu.tondryk.jsfol.Feature;
import eu.tondryk.jsfol.geom.Geometry;
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
public class JsfolParser {

	/**
	 * This method converts the given geo-json string into list of
	 * {@link Feature} objects.
	 * 
	 * @param geoJson
	 * @return
	 */
	public static List<Feature> parseGeoJson(String geoJson) {
		List<Feature> result = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		try {
			Object object = jsonParser.parse(geoJson);
			JSONObject geoJsonObject = (JSONObject) object;

			if (geoJsonObject.get("features") != null) {
				JSONArray features = (JSONArray) geoJsonObject.get("features");
				for (Object feature : features) {
					result.add(JsfolParser.parseFeature((JSONObject) feature));
				}
			}

		} catch (ParseException e) {
			// TODO handle exception
		}
		return result;

	}

	/**
	 * This method converts the given feature (given in geo-json format) to
	 * {@link Feature} object.
	 * 
	 * @param feature
	 * @return
	 */
	private static Feature parseFeature(JSONObject feature) {
		Geometry geometry = null;
		Style style = null;
		if (feature.get("geometry") != null) {
			geometry = JsfolParser.parseGeometry((JSONObject) feature
					.get("geometry"));
		}
		if (feature.get("properties") != null
				&& ((JSONObject) feature.get("properties")).get("style") != null) {
			style = JsfolParser.parseStyle(((JSONObject) ((JSONObject) feature
					.get("properties")).get("style")));
		}
		return new Feature(geometry, style);
	}

	/**
	 * This method converts the given geometry (given in geo-json format) to
	 * {@link Geometry} object.
	 * 
	 * @param jsonObject
	 * @return
	 */
	private static Geometry parseGeometry(JSONObject jsonGeometry) {
		Geometry result = null;
		switch ((String) jsonGeometry.get("type")) {
		case "Polygon":
			result = JsfolParser.parsePolygon((JSONArray) jsonGeometry
					.get("coordinates"));
			break;
		case "Point":
			result = JsfolParser.parsePoint((JSONArray) jsonGeometry
					.get("coordinates"));
		default:
			// TODO implement other type
		}
		return result;
	}

	/**
	 * This method converts the given json-array of coordinates to
	 * {@link Polygon} object.
	 * 
	 * @param jsonCoordinates
	 * @return
	 */
	private static Polygon parsePolygon(JSONArray jsonCoordinates) {
		List<List<Coordinate>> linearRings = new ArrayList<List<Coordinate>>();
		for (Object linearRing : jsonCoordinates) {
			linearRings
					.add(JsfolParser.parseLinearRing((JSONArray) linearRing));
		}
		return new Polygon(linearRings);
	}

	/**
	 * This method converts the given json-array of coordinates to {@link Point}
	 * object.
	 * 
	 * @param jsonCoordinates
	 * @return
	 */
	private static Point parsePoint(JSONArray jsonCoordinates) {
		return new Point(JsfolParser.parseCoordinate(jsonCoordinates));
	}

	/**
	 * This method converts the given json-array of coordinates into list of
	 * {@link Coordinate} object.
	 * 
	 * @param jsonLinearRing
	 * @return
	 */
	private static List<Coordinate> parseLinearRing(JSONArray jsonLinearRing) {
		List<Coordinate> linearRing = new ArrayList<Coordinate>();
		for (Object coordinate : jsonLinearRing) {
			linearRing.add(JsfolParser.parseCoordinate((JSONArray) coordinate));
		}
		return linearRing;
	}

	/**
	 * This method converts the given json-array of coordinates to
	 * {@link Coordinate} object.
	 * 
	 * @param coordinate
	 * @return
	 */
	private static Coordinate parseCoordinate(JSONArray coordinate) {
		return new Coordinate((Double) coordinate.get(0),
				(Double) coordinate.get(1));
	}

	/**
	 * This method converts the given style (given in json format) to
	 * {@link Style} object.
	 * 
	 * @param jsonObject
	 * @return
	 */
	private static Style parseStyle(JSONObject jsonStyle) {
		Fill fill = null;
		Stroke stroke = null;
		Image image = null;
		Text text = null;

		if (jsonStyle.containsKey("fill")) {
			fill = JsfolParser.parseFill((JSONObject) jsonStyle.get("fill"));
		}
		if (jsonStyle.containsKey("stroke")) {
			stroke = JsfolParser.parseStroke((JSONObject) jsonStyle
					.get("stroke"));
		}
		if (jsonStyle.containsKey("image")) {
			image = JsfolParser.parseImage((JSONObject) jsonStyle.get("image"));
		}
		if (jsonStyle.containsKey("text")) {
			text = JsfolParser.parseText((JSONObject) jsonStyle.get("text"));
		}

		return new Style(fill, image, stroke, text);
	}

	/**
	 * This method converts the given <i>fill</i> (given in json format) to
	 * {@link Fill} object.
	 * 
	 * @param jsonFill
	 * @return
	 */
	private static Fill parseFill(JSONObject jsonFill) {
		return new Fill(JsfolParser.parseColor((String) jsonFill.get("color")));
	}

	/**
	 * This method converts the given <i>color</i> (given as rgba-string) to
	 * {@link Color} object.
	 * 
	 * @param colorAsString
	 * @return
	 */
	private static Color parseColor(String colorAsString) {
		Color color = null;
		if (colorAsString.startsWith("rgba")) {

			// remove brackets
			String tmp = colorAsString.substring(5);
			tmp = tmp.substring(0, tmp.length() - 1);

			// remove whitespaces
			tmp = tmp.replaceAll("\\s", "");

			// split the red/green/blue/alpha values
			String[] values = tmp.split(",");

			color = new Color(Integer.parseInt(values[0]),
					Integer.parseInt(values[1]), Integer.parseInt(values[2]),
					Float.parseFloat(values[3]));
		} else {
			color = new Color(colorAsString);
		}
		return color;
	}

	/**
	 * This method converts the given <i>stroke</i> (given in json format) to
	 * {@link Stroke} object.
	 * 
	 * @param jsonStroke
	 * @return
	 */
	private static Stroke parseStroke(JSONObject jsonStroke) {
		Color color = null;
		String lineCap = null;
		String lineJoin = null;
		Integer[] lineDash = null;
		Integer miterLimit = null;
		Integer width = null;
		if (jsonStroke.get("color") != null) {
			color = JsfolParser.parseColor((String) jsonStroke.get("color"));
		}
		if (jsonStroke.get("lineCap") != null) {
			lineCap = (String) jsonStroke.get("lineCap");
		}
		if (jsonStroke.get("lineDash") != null) {
			JSONArray lineDashArray = (JSONArray) jsonStroke.get("lineDash");
			lineDash = new Integer[lineDashArray.size()];
			int i = 0;
			for (Object dash : (JSONArray) jsonStroke.get("lineDash")) {
				lineDash[i++] = ((Number) dash).intValue();
			}
		}
		if (jsonStroke.get("lineJoin") != null) {
			lineJoin = (String) jsonStroke.get("lineJoin");
		}
		if (jsonStroke.get("miterLimit") != null) {
			miterLimit = (Integer) jsonStroke.get("miterLimit");
		}
		if (jsonStroke.get("width") != null) {
			width = ((Number) jsonStroke.get("width")).intValue();
		}
		return new Stroke(color, lineCap, lineJoin, lineDash, miterLimit, width);
	}

	/**
	 * This method converts the given <i>image</i> (given in json format) to
	 * {@link Image} object.
	 * 
	 * @param jsonImage
	 * @return
	 */
	private static Image parseImage(JSONObject jsonImage) {
		Image image = null;
		if ("Icon".equals(jsonImage.get("type"))) {
			image = JsfolParser.parseIcon(jsonImage);
		} else if ("Circle".equals(jsonImage.get("type"))) {
			image = JsfolParser.parseCircle(jsonImage);
		} else {
			// TODO implement other type of image
		}

		if (image != null) {
			if (jsonImage.get("opacity") != null) {
				image.setOpacity(((Number) jsonImage.get("opacity"))
						.doubleValue());
			}
			if (jsonImage.get("rotateWithView") != null) {
				image.setRotateWithView((Boolean) jsonImage
						.get("rotateWithView"));
			}
			if (jsonImage.get("rotation") != null) {
				image.setRotation(((Number) jsonImage.get("rotation"))
						.doubleValue());
			}
			if (jsonImage.get("scale") != null) {
				image.setScale(((Number) jsonImage.get("scale")).doubleValue());
			}
			if (jsonImage.get("snapToPixel") != null) {
				image.setSnapToPixel((Boolean) jsonImage.get("snapToPixel"));
			}
		}
		return image;
	}

	/**
	 * This method converts the given <i>circle</i> (given in json format) to
	 * {@link Circle} object.
	 * 
	 * @param jsonCircle
	 * @return
	 */
	private static Image parseCircle(JSONObject jsonCircle) {
		Circle result = new Circle();
		if (jsonCircle.get("fill") != null) {
			result.setFill(JsfolParser.parseFill((JSONObject) jsonCircle
					.get("fill")));
		}
		if (jsonCircle.get("radius") != null) {
			result.setRadius(((Number) jsonCircle.get("radius")).doubleValue());
		}
		if (jsonCircle.get("stroke") != null) {
			result.setStroke(JsfolParser.parseStroke((JSONObject) jsonCircle
					.get("stroke")));
		}
		return result;
	}

	/**
	 * This method converts the given <i>icon</i> (given in json format) to
	 * {@link Icon} object.
	 * 
	 * @param jsonIcon
	 * @return
	 */
	private static Image parseIcon(JSONObject jsonIcon) {
		Icon result = new Icon();
		if (jsonIcon.get("anchor") != null) {
			JSONArray jsonAnchor = (JSONArray) jsonIcon.get("size");
			Double[] anchor = new Double[2];
			anchor[0] = ((Number) jsonAnchor.get(0)).doubleValue();
			anchor[1] = ((Number) jsonAnchor.get(1)).doubleValue();
			result.setAnchor(anchor);
		}
		if (jsonIcon.get("size") != null) {
			JSONArray jsonSize = (JSONArray) jsonIcon.get("size");
			result.setSize(new Size(((Number) jsonSize.get(0)).intValue(),
					((Number) jsonSize.get(1)).intValue()));
		}
		if (jsonIcon.get("src") != null) {
			result.setSrc((String) jsonIcon.get("src"));
		}
		return result;
	}

	/**
	 * This method converts the given <i>text</i> (given in json format) to
	 * {@link Text} object.
	 * 
	 * @param jsonText
	 * @return
	 */
	private static Text parseText(JSONObject jsonText) {
		String font = null;
		String text = null;
		String textAlign = null;
		String textBaseLine = null;
		Fill fill = null;
		Stroke stroke = null;
		Integer offsetX = null;
		Integer offsetY = null;
		Double scale = null;
		Double rotation = null;
		if (jsonText.get("fill") != null) {
			fill = JsfolParser.parseFill((JSONObject) jsonText.get("fill"));
		}
		if (jsonText.get("font") != null) {
			font = (String) jsonText.get("font");
		}
		if (jsonText.get("offsetX") != null) {
			offsetX = ((Number) jsonText.get("offsetX")).intValue();
		}
		if (jsonText.get("offsetY") != null) {
			offsetY = ((Number) jsonText.get("offsetY")).intValue();
		}
		if (jsonText.get("rotation") != null) {
			rotation = ((Number) jsonText.get("rotation")).doubleValue();
		}
		if (jsonText.get("scale") != null) {
			scale = ((Number) jsonText.get("scale")).doubleValue();
		}
		if (jsonText.get("stroke") != null) {
			stroke = JsfolParser.parseStroke((JSONObject) jsonText
					.get("stroke"));
		}
		if (jsonText.get("text") != null) {
			text = (String) jsonText.get("text");
		}
		if (jsonText.get("textAlign") != null) {
			textAlign = (String) jsonText.get("textAlign");
		}
		if (jsonText.get("textBaseline") != null) {
			textBaseLine = (String) jsonText.get("textBaseline");
		}
		return new Text(font, text, textAlign, textBaseLine, fill, stroke,
				offsetX, offsetY, scale, rotation);
	}

}