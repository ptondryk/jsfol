/**
 * 
 */
package eu.tondryk.jsfol.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.tondryk.jsfol.Feature;
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
	 * simple test of the <code>parseGeoJson</code> method
	 */
	@Test
	public void testParseGeoJson1() {

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
	 * simple test of the <code>parseGeoJson</code> method, focus on the image
	 * tag
	 */
	@Test
	public void testParseGeoJson2() {

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
	public void testParseGeoJson3() {

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
	public void testParseGeoJson4() {

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

}
