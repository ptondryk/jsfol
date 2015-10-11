/**
 * 
 */
package eu.tondryk.jsfol.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.tondryk.jsfol.Feature;
import eu.tondryk.jsfol.geom.LineString;
import eu.tondryk.jsfol.geom.MultiLineString;
import eu.tondryk.jsfol.geom.MultiPoint;
import eu.tondryk.jsfol.geom.MultiPolygon;
import eu.tondryk.jsfol.geom.Point;
import eu.tondryk.jsfol.geom.Polygon;
import eu.tondryk.jsfol.style.Circle;
import eu.tondryk.jsfol.style.Icon;

/**
 * Tests of the class {@link JsfolParser}.
 * 
 * @author ptondryk
 *
 */
public class JsfolParserTest {

	/**
	 * simple test of the <code>parseGeoJson</code> method, focus on the image
	 * tag
	 */
	@Test
	public void testParseStyle1() {

		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[1485480.6809926806,6883620.941418062],[1521091.7860974488,6882396.987459423],[1497402.99845664,6921068.8947993545],[1485480.6809926806,6883620.941418062]]]},\"properties\":{\"style\":{\"image\":{\"fill\":{\"color\":\"rgba(255, 0, 0, 0.2)\"},\"opacity\":1,\"radius\":5,\"rotationWithView\":false,\"rotation\":0,\"scale\":1,\"snapToPixel\":true,\"stroke\":{\"color\":\"green\",\"lineDash\":[1,2,1,3],\"width\":2},\"type\":\"Circle\"}}}}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertTrue(parsedFeatures.get(0).getStyle().getImage() instanceof Circle);
		Assert.assertEquals(new Double(5.0), ((Circle) parsedFeatures.get(0)
				.getStyle().getImage()).getRadius());
		Assert.assertEquals("green", ((Circle) parsedFeatures.get(0).getStyle()
				.getImage()).getStroke().getColor().getLiteral());
	}

	/**
	 * simple test of the <code>parseGeoJson</code> method, focus on the image
	 * tag
	 */
	@Test
	public void testParseStyle2() {

		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[1485480.6809926806,6883620.941418062],[1521091.7860974488,6882396.987459423],[1497402.99845664,6921068.8947993545],[1485480.6809926806,6883620.941418062]]]},\"properties\":{\"style\":{\"image\":{\"anchor\":[16,16],\"opacity\":5,\"origin\":[0.5,0.8],\"rotateWithView\":false,\"rotation\":0,\"scale\":1,\"size\":[32,32],\"snapToPixel\":true,\"src\":\"img/test.jpg\",\"type\":\"Icon\"}}}}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertTrue(parsedFeatures.get(0).getStyle().getImage() instanceof Icon);
		Assert.assertEquals("img/test.jpg", ((Icon) parsedFeatures.get(0)
				.getStyle().getImage()).getSrc());
		Assert.assertEquals(32, ((Icon) parsedFeatures.get(0).getStyle()
				.getImage()).getSize().getHeight());
	}

	/**
	 * simple test of the <code>parseGeoJson</code> method, focus on the text
	 * tag
	 */
	@Test
	public void testParseStyle3() {

		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[1485480.6809926806,6883620.941418062],[1521091.7860974488,6882396.987459423],[1497402.99845664,6921068.8947993545],[1485480.6809926806,6883620.941418062]]]},\"properties\":{\"style\":{\"text\":{\"fill\":{\"color\":\"rgba(253, 0, 0, 0.2)\"},\"font\":\"Arial\",\"offsetX\":2,\"offsetY\":3,\"rotation\":0,\"scale\":1,\"stroke\":{\"color\":\"red\",\"width\":2},\"text\":\"test\",\"textAlign\":\"left\"}}}}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertEquals("test", parsedFeatures.get(0).getStyle().getText()
				.getText());
		Assert.assertEquals(253, parsedFeatures.get(0).getStyle().getText()
				.getFill().getColor().getGreen());
	}

	/**
	 * 
	 */
	@Test
	public void testParsePoint() {
		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{ \"type\": \"Point\", \"coordinates\": [100.0, 0.0] }}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertTrue(parsedFeatures.get(0).getGeometry() instanceof Point);
		Assert.assertEquals(0.0d, ((Point) parsedFeatures.get(0).getGeometry())
				.getCoordinate().getY(), 0.0d);
	}

	/**
	 * 
	 */
	@Test
	public void testParseLineString() {
		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{ \"type\": \"LineString\", \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ] }}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertTrue(parsedFeatures.get(0).getGeometry() instanceof LineString);
		Assert.assertEquals(1.0d, ((LineString) parsedFeatures.get(0)
				.getGeometry()).getCoordinates().get(1).getY(), 0.0d);
	}

	/**
	 * simple test of the <code>parseGeoJson</code> method
	 */
	@Test
	public void testParsePolygon() {

		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[1485480.6809926806,6883620.941418062],[1521091.7860974488,6882396.987459423],[1497402.99845664,6921068.8947993545],[1485480.6809926806,6883620.941418062]]]},\"properties\":{\"style\":{\"fill\":{\"color\":\"rgba(255, 0, 0, 0.2)\"},\"stroke\":{\"color\":\"red\",\"width\":2}}}}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertEquals("red", parsedFeatures.get(0).getStyle().getStroke()
				.getColor().getLiteral());
		Assert.assertEquals(1521091.7860974488d,
				((Polygon) parsedFeatures.get(0).getGeometry())
						.getCoordinates().get(0).get(1).getX(), 0.1d);
	}

	/**
	 * 
	 */
	@Test
	public void testParseMultiPoint() {
		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{ \"type\": \"MultiPoint\", \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ] }}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertTrue(parsedFeatures.get(0).getGeometry() instanceof MultiPoint);
		Assert.assertEquals(101.0d, ((MultiPoint) parsedFeatures.get(0)
				.getGeometry()).getPoints().get(1).getCoordinate().getX(), 0.0d);
	}

	/**
	 * 
	 */
	@Test
	public void testParseMultiLineString() {
		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{ \"type\": \"MultiLineString\", \"coordinates\": [[ [100.0, 0.0], [101.0, 1.0] ], [ [102.0, 2.0], [103.0, 3.0] ] ]}}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertTrue(parsedFeatures.get(0).getGeometry() instanceof MultiLineString);
		Assert.assertEquals(102.0d, ((MultiLineString) parsedFeatures.get(0)
				.getGeometry()).getLineStrings().get(1).getCoordinates().get(0)
				.getX(), 0.0d);
	}

	/**
	 * 
	 */
	@Test
	public void testParseMultiPolygon() {
		// prepare test geojson formatted string
		String testGeoJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{ \"type\": \"MultiPolygon\", \"coordinates\": [[[[102.0, 2.0], [103.0, 2.0], [103.0, 3.0], [102.0, 3.0], [102.0, 2.0]]], [[[100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0]], [[100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], [100.2, 0.2]]]] }}]}";

		// call the tested method
		List<Feature> parsedFeatures = JsfolParser.parseGeoJson(testGeoJson);

		// verify the result
		Assert.assertEquals(1, parsedFeatures.size());
		Assert.assertTrue(parsedFeatures.get(0).getGeometry() instanceof MultiPolygon);
		Assert.assertEquals(100.8d, ((MultiPolygon) parsedFeatures.get(0)
				.getGeometry()).getPolygons().get(1).getCoordinates().get(1)
				.get(1).getX(), 0.0d);
	}

}
